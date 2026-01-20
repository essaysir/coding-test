import java.util.*;

public class Main {
	public static void main(String[] args) {
		int N = 5;
		int K = 17;
		System.out.println(solution(N, K));
	}

	private static int solution(int N, int K) {
		// 시작부터 같은 위치면 바로 반환
		if (N == K) return 0;

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{N, 0});
		boolean[][] visited = new boolean[500001][2];
		visited[N][0] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int currentN = current[0];
			int level = current[1];

			int[] dx = {currentN - 1, currentN + 1, currentN * 2};
			for (int d : dx) {
				if (d < 0 || d > 500000) continue;

				int nextLevel = level + 1;
				int parity = nextLevel % 2;

				if (visited[d][parity]) continue;
				visited[d][parity] = true;

				// nextLevel 시간에 동생 위치 계산
				int sumK = K + nextLevel * (nextLevel + 1) / 2;

				// 동생이 범위를 벗어나면 잡을 수 없음
				if (sumK > 500000) return -1;

				// 수빈이와 동생이 같은 위치에 있는지 확인
				if (d == sumK) return nextLevel;

				queue.add(new int[]{d, nextLevel});
			}
		}
		return -1;
	}
}