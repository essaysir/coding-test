import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N 은 3 이상 100 이하
		// 1 ~ N-1 까지 , N 은 완제품

		int M =  Integer.parseInt(br.readLine()); // M 은 3 이상 100 이하
		// 그 다음 M 개 줄에서 부품 방식이 주어짐
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

		for ( int i = 0; i < M; i++){
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st1.nextToken()); // 중간 부품 번호
			int y = Integer.parseInt(st1.nextToken()); // 기본 부품 번호 또는 중간 부품 번호
			int k = Integer.parseInt(st1.nextToken()); // K개 가 필요

			map.computeIfAbsent(x, key -> new HashMap<>()).put(y, k);
		}

		// 위상 정렬용 진입 차수 계산
		int[] inDegree = new int[N + 1];
		for (Map.Entry<Integer, Map<Integer, Integer>> entry : map.entrySet()) {
			for (int y : entry.getValue().keySet()) {
				inDegree[y]++;
			}
		}

		// N 이 완제품, 위상 정렬로 각 부품을 한 번만 처리
		long[] need = new long[N + 1];
		need[N] = 1;

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(N);

		while (!queue.isEmpty()) {
			int num = queue.poll();
			if (!map.containsKey(num)) continue; // 기본 부품은 스킵

			for (Map.Entry<Integer, Integer> e : map.get(num).entrySet()) {
				int y = e.getKey();
				int k = e.getValue();
				need[y] += need[num] * k;
				inDegree[y]--;
				if (inDegree[y] == 0) {
					queue.offer(y);
				}
			}
		}

		// 기본 부품만 오름차순 출력 (map에 키가 없는 부품 = 기본 부품)
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (!map.containsKey(i) && need[i] > 0) {
				sb.append(i).append(" ").append(need[i]).append("\n");
			}
		}
		System.out.print(sb);
	}
}