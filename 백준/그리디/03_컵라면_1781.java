import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args){
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.parseInt(br.readLine()); // 동호가 받을 수 있는 최대 컵라면 수
			// 1 <= N <= 200,000
			long[][] arr = new long[N][2];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[i][0] = Long.parseLong(st.nextToken());  // deadLine
				arr[i][1] = Long.parseLong(st.nextToken());  // cupRamenCount
			}
			System.out.println(solution(N,arr));
		}catch ( IOException e){
			e.printStackTrace();
		}


	}

	private static long solution(int N, long[][] arr){
		// 데드라인 오름차순 정렬
		Arrays.sort(arr, (prev, cur) -> Long.compare(prev[0], cur[0]));

		// 최소 힙 (컵라면 수 기준) - 가장 작은 값을 빠르게 제거하기 위함
		PriorityQueue<Long> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++){
			long deadLine = arr[i][0];
			long cupRamenCount = arr[i][1];

			pq.offer(cupRamenCount);

			// 힙 크기가 데드라인보다 크면, 가장 작은 값 제거
			if (pq.size() > deadLine){
				pq.poll();
			}
		}

		long answer = 0;
		while (!pq.isEmpty()){
			answer += pq.poll();
		}
		return answer;
	}
}