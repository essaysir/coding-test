import java.util.*;

public class Main{

	public static void main(String[] args) {
		int R = 4, C = 4;
		char[][] map = {
			{'#', '#', '#', '#'},
			{'#', 'J', 'F', '#'},
			{'#', '.', '.', '#'},
			{'#', '.', '.', '#'}
		};

		System.out.println(solution(R, C, map));
	}

	private static int[] dy = new int[]{-1, 0, 1, 0};
	private static int[] dx = new int[]{0, -1, 0, 1};

	private static String solution(int R, int C, char[][] map) {
		// R -> 행의 수 (세로 길이) ,   C -> 열의 수 (가로 길이)
		// # : 벽, . : 지나갈 수 있는 공간 , J : 지훈 , F : 불이난 공간

		int[] jihoonLocation = null;

		// 1. BFS로 돌아다녀야 함.
		// 지훈이가 움직이는 거에 대한 처리는 생각보다 쉬움.
		// 그렇다면, 해당 불의 처리는 어떻게 해야 하는가?
		// 불 또한 queue를 통해서 계속해서, 늘어나면 된다.

		// 불의 확산 시간을 먼저 계산 (BFS)
		Queue<int[]> fireQueue = new ArrayDeque<>();
		int[][] fireTime = new int[R][C];

		// fireTime 초기화 및 지훈이와 불의 초기 위치 찾기
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				fireTime[i][j] = -1; // 아직 불이 도달하지 않음

				if (map[i][j] == 'J'){
					jihoonLocation = new int[]{i, j};
				}

				if (map[i][j] == 'F'){
					fireQueue.add(new int[]{i, j});
					fireTime[i][j] = 0;
				}
			}
		}

		// 불의 BFS - 각 칸에 불이 언제 도착하는지 계산
		while (!fireQueue.isEmpty()){
			int[] curLocation = fireQueue.poll();
			int curY = curLocation[0];
			int curX = curLocation[1];

			for (int x = 0; x < 4; x++){
				int ny = curY + dy[x];
				int nx = curX + dx[x];

				// 1. Map의 범위 조건 체크
				if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;

				// 2. 벽 체크
				if (map[ny][nx] == '#') continue;

				// 3. 이미 불이 방문한 곳 체크
				if (fireTime[ny][nx] != -1) continue;

				// 불 확산
				fireTime[ny][nx] = fireTime[curY][curX] + 1;
				fireQueue.add(new int[]{ny, nx});
			}
		}

		// 지훈이의 이동 BFS
		Queue<int[]> jihoonQueue = new ArrayDeque<>();
		jihoonQueue.add(jihoonLocation);

		int[][] jihoonTime = new int[R][C];

		// jihoonTime 초기화
		for (int i = 0; i < R; i++){
			for (int j = 0; j < C; j++){
				jihoonTime[i][j] = -1; // 아직 방문하지 않음
			}
		}

		jihoonTime[jihoonLocation[0]][jihoonLocation[1]] = 0;

		while (!jihoonQueue.isEmpty()){
			int[] curLocation = jihoonQueue.poll();
			int curY = curLocation[0];
			int curX = curLocation[1];

			// 가장자리에 도달했는지 체크 (탈출 가능)
			if (curY == 0 || curY == R - 1 || curX == 0 || curX == C - 1){
				return String.valueOf(jihoonTime[curY][curX] + 1);
			}

			for (int x = 0; x < 4; x++){
				int ny = curY + dy[x];
				int nx = curX + dx[x];

				// 1. Map의 범위 조건 체크
				if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;

				// 2. 벽 체크
				if (map[ny][nx] == '#') continue;

				// 3. 방문 여부 체크
				if (jihoonTime[ny][nx] != -1) continue;

				// 4. 불이 난 공간 체크 - 불보다 늦게 도착하면 안됨
				if (fireTime[ny][nx] != -1 && fireTime[ny][nx] <= jihoonTime[curY][curX] + 1) continue;

				// 지훈이 이동
				jihoonTime[ny][nx] = jihoonTime[curY][curX] + 1;
				jihoonQueue.add(new int[]{ny, nx});
			}
		}

		return "IMPOSSIBLE";
	}
}