import java.util.*;

public class Main {

	public static void main(String[] args) {
		int N = 5;
		int K = 17;
		int[] answer = solution(N, K);
		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}

	private static int[] solution(int N, int K) {
		// N 은 수빈이가 있는 위치
		// K 는 동생이 있는 위치

		Queue<Integer> queue = new ArrayDeque<>();
		int[] dist = new int[100002];  // 최소 거리 저장
		int[] cnt = new int[100002];   // 해당 거리로 도달하는 경로 수

		Arrays.fill(dist, -1);  // -1은 미방문 의미

		dist[N] = 0;
		cnt[N] = 1;
		queue.add(N);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			int[] nexts = {cur - 1, cur + 1, cur * 2};

			for (int next : nexts) {
				if (next < 0 || next > 100000) continue;

				if (dist[next] == -1) {
					// 처음 방문: 거리 기록, 경로 수 복사, 큐에 추가
					dist[next] = dist[cur] + 1;
					cnt[next] = cnt[cur];
					queue.add(next);
				}
				else if (dist[next] == dist[cur] + 1) {
					// 같은 거리로 다시 도달: 경로 수만 누적
					cnt[next] += cnt[cur];
				}
			}
		}

		return new int[]{dist[K], cnt[K]};
	}
}