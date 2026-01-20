import java.util.*;

public class Main{

	public static void main(String[] args){
		// 3
		// 12 10 4

		int N = 3;
		int[] SCVS = new int[]{12,10,4};

		System.out.println(solution(N,SCVS));
	}

	private static int[][] thirdPossible = new int[][]{
		{0,1,2}, {0,2,1}, {1,0,2}, {1,2,0}, {2,0,1}, {2,1,0}
	};

	private static int[][] secondPossible = new int[][]{
		{0,1}, {1,0}
	};


	private static int solution(int N, int[] SCVS){
		// N 은 SCV 의 수(1 ~ 3) , SCVS 는 각 SCV 의 체력이다.

		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[61][61][61]; // 방문 체크 추가

		int[] defaultValue = new int[4]; // N+1 → 4로 고정 (체력3개 + 레벨1개)
		Arrays.fill(defaultValue,0);

		for ( int i = 0; i < SCVS.length; i++){
			defaultValue[i] = SCVS[i];
		}
		// { 12, 10 , 4 , 0 }
		// {             레벨 }

		// 1. 초기값 적용
		queue.offer(defaultValue);
		visited[defaultValue[0]][defaultValue[1]][defaultValue[2]] = true;

		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			if ( cur[0] <= 0 && cur[1] <= 0 && cur[2] <= 0){
				return cur[3];
			}

			List<Integer> curAlive = new ArrayList<>(); // [ 0 , 1 , 2 ]
			for ( int i = 0; i < 3 ; i++){
				if ( cur[i] > 0 ){
					curAlive.add(i);
				}
			}

			if ( curAlive.size() == 3){
				// 6개의 가능성을 모두 처리 필요
				for ( int x = 0; x < thirdPossible.length; x++){
					int[] next = cur.clone(); // 각 경우마다 새로운 배열 생성!
					int[] possible = thirdPossible[x];

					for ( int y = 0; y < possible.length; y++){
						int curIndex = curAlive.get(possible[y]);
						if ( y == 0 ){
							next[curIndex] -= 9;
						}else if ( y == 1){
							next[curIndex] -= 3;
						}else if ( y == 2){
							next[curIndex] -= 1;
						}
					}
					next[3] += 1;

					// 음수 처리 및 방문 체크
					int hp0 = Math.max(0, next[0]);
					int hp1 = Math.max(0, next[1]);
					int hp2 = Math.max(0, next[2]);

					if (!visited[hp0][hp1][hp2]) {
						visited[hp0][hp1][hp2] = true;
						next[0] = hp0;
						next[1] = hp1;
						next[2] = hp2;
						queue.offer(next);
					}
				}
			} else if ( curAlive.size() == 2) {
				// 2개의 가능성
				for ( int x = 0; x < secondPossible.length; x++){
					int[] next = cur.clone(); // 각 경우마다 새로운 배열 생성!
					int[] possible = secondPossible[x];

					for ( int y = 0; y < possible.length; y++){
						int curIndex = curAlive.get(possible[y]);
						if ( y == 0){
							next[curIndex] -= 9;
						}else if ( y == 1){
							next[curIndex] -= 3;
						}
					}
					next[3] += 1;

					// 음수 처리 및 방문 체크
					int hp0 = Math.max(0, next[0]);
					int hp1 = Math.max(0, next[1]);
					int hp2 = Math.max(0, next[2]);

					if (!visited[hp0][hp1][hp2]) {
						visited[hp0][hp1][hp2] = true;
						next[0] = hp0;
						next[1] = hp1;
						next[2] = hp2;
						queue.offer(next);
					}
				}
			}else if (curAlive.size() == 1) {
				int[] next = cur.clone(); // 새로운 배열 생성!
				int curIndex = curAlive.get(0);
				next[curIndex] -= 9;
				next[3] += 1;

				// 음수 처리 및 방문 체크
				int hp0 = Math.max(0, next[0]);
				int hp1 = Math.max(0, next[1]);
				int hp2 = Math.max(0, next[2]);

				if (!visited[hp0][hp1][hp2]) {
					visited[hp0][hp1][hp2] = true;
					next[0] = hp0;
					next[1] = hp1;
					next[2] = hp2;
					queue.offer(next);
				}
			}

		}
		return 0;
	}
}