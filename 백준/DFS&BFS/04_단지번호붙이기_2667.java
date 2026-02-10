import java.util.*;
import java.io.*;

public class Main {

	// 상 우 하 좌
	public static int[] dy = new int[]{-1, 0, 1, 0};
	public static int[] dx = new int[]{0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < str.length; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		solution(N, map);
	}

	private static void solution(int N, int[][] map) {
		// 5 이상 N 25 이하
		// 출력: 총 단지 수 + 각 단지내 집의 수를 오름차순 정렬

		boolean[][] visited = new boolean[N][N];
		List<Integer> homeCount = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 집이 있고 아직 방문하지 않은 칸에서만 BFS
				if (map[i][j] == 1 && !visited[i][j]) {
					int count = BFS(i, j, N, map, visited);
					homeCount.add(count);
				}
			}
		}

		Collections.sort(homeCount);

		System.out.println(homeCount.size());
		for (int count : homeCount) {
			System.out.println(count);
		}
	}

	private static int BFS(int startY, int startX, int N, int[][] map, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{startY, startX});
		visited[startY][startX] = true;
		int count = 1; // 시작점도 집 1채

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];

				// 범위 체크 + 집 존재 + 미방문
				if (ny >= 0 && ny < N && nx >= 0 && nx < N
						&& map[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					queue.offer(new int[]{ny, nx});
					count++;
				}
			}
		}

		return count;
	}
}