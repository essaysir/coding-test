import java.util.*;

public class Main {
	public static void main(String[] args) {
		int n = 5;
		int m = 7;
		char[][] map = {
			{'W','L','L','W','W','W','L'},
			{'L','L','L','W','L','L','L'},
			{'L','W','L','W','L','W','W'},
			{'L','W','L','W','L','L','L'},
			{'W','L','L','W','L','W','W'}
		};

		int answer = solution(n, m, map);
		System.out.println(answer);
	}

	private static int[] ny = { -1,0,1,0};
	private static int[] nx = {0,1,0,-1};
	private static int[][] visited;
	private static int answer = 0;

 	private static int solution(int n, int m, char[][] map) {
		// 조건 :  L = 육지 , W = 바다
		// 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳
		// 최단 거리로 이동 해야 함.. BFS 의 기저 조건을 어떤 거로 해야 하지 ?

		for ( int i = 0; i < n; i++){
			for ( int j = 0; j < m; j++){
				if (map[i][j] == 'L'){
					int max = BFS(n,m, map, i,j);
					answer = Math.max(answer, max);
				}
			}
		}
		return answer;
	}
	private static int BFS(int n, int m, char[][] map, int startY, int startX) {
		Queue<int[]> queue = new LinkedList<>();
		int[][] visited = new int[n][m];

		queue.add(new int[]{startY, startX, 0});
		visited[startY][startX] = 1;

		int maxDistance = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int y = cur[0];
			int x = cur[1];
			int dist = cur[2];

			maxDistance = Math.max(maxDistance, dist);

			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int nextY = y + ny[i];
				int nextX = x + nx[i];

				if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= m) continue;
				if (visited[nextY][nextX] == 1) continue;
				if (map[nextY][nextX] == 'W') continue;

				visited[nextY][nextX] = 1;
				queue.add(new int[]{nextY, nextX, dist + 1});
			}
		}

		return maxDistance;
	}
}
