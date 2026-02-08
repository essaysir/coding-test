import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args){
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			// D 일 안에만 와서 해주면 됨,

			// 0 <=  N <= 10000 , 강연 횟수
			int N = Integer.parseInt(br.readLine().trim());
			int[][] arr = new int[N][2];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int P = Integer.parseInt(st.nextToken()); // 강연료
				int D = Integer.parseInt(st.nextToken()); // 날짜

				arr[i][0] = P;
				arr[i][1] = D;
			}

			System.out.println(solution(N, arr));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static int solution(int N, int[][] arr) {
		// 마감일 오름차순 정렬 (같으면 강연료 오름차순)
		Arrays.sort(arr, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);

		// 최소 힙 (강연료 기준)
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int P = arr[i][0];
			int D = arr[i][1];

			pq.offer(P);

			// 힙 크기가 마감일보다 크면, 가장 작은 강연료를 버림
			if (pq.size() > D) {
				pq.poll();
			}
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}
		return answer;
	}
}