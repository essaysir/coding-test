import java.util.*;

public class Main {
	public static void main(String[] args) {
		int N = 5;
		int K = 17;

		solution(N, K);
	}

	private static void solution(int N, int K) {
		Queue<Integer> queue = new ArrayDeque<>();

		queue.add(N);
		int[] visited = new int[100001];
		int[] prev = new int[100001];
		visited[N] = 1;
		prev[N] = -1;

		while (!queue.isEmpty()) {
			int curX = queue.poll();

			if (curX == K) {
				break;
			}

			int[] nexts = {curX - 1, curX + 1, curX * 2};
			for (int next : nexts) {
				if (next < 0 || next > 100000) continue;
				if (visited[next] == 1) continue;

				queue.add(next);
				visited[next] = 1;
				prev[next] = curX;
			}
		}

		// 경로 역추적
		List<Integer> path = new ArrayList<>();
		for (int i = K; i != -1; i = prev[i]) {
			path.add(i);
		}
		Collections.reverse(path);

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(path.size() - 1).append("\n");
		for (int i = 0; i < path.size(); i++) {
			sb.append(path.get(i));
			if (i < path.size() - 1) sb.append(" ");
		}
		System.out.println(sb);
	}
}