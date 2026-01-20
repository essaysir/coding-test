import java.util.*;

public class ChickenDelivery{

	public static void main(String[] args){
		int[][] map = {
			{0, 0, 1, 0, 0},
			{0, 0, 2, 0, 1},
			{0, 1, 2, 0, 0},
			{0, 0, 1, 0, 0},
			{0, 0, 0, 0, 2}
		};

		System.out.println(Solution(5, 3, map));
	}

	private static int Solution(int N, int M, int[][] Map){
		// N 은 맵의 정사각형 길이
		// M 은 치킨 집을 고를 수 있는 최대 정의

		// 치킨 집이 몇개 인지 와 어디에 속하는 지 담을 저장소
		List<int[]> chickens = new ArrayList<>();
		// 집의 위치를 담을 요소
		List<int[]> homes = new ArrayList<>();

		// 1. 치킨 집이 몇 개 인 지 파악 하기
		for ( int y = 0; y < N; y++ ){
			for ( int x = 0; x < N; x++ ){
				if (Map[y][x] == 2 ) chickens.add(new int[]{x,y});
				if (Map[y][x] == 1 ) homes.add(new int[]{x,y});
			}
		}

		// 2. 치킨 집이 나올 수 있는 경우의 수 (Combination 으로 정의하기)
		int chickensCount = chickens.size();
		List<List<Integer>> possibles = combination(chickensCount,M);


		// 3. 각 경우의 수에서, 도시의 치킨 거리 구하기
		int answer = Integer.MAX_VALUE;
		for ( List<Integer> possible : possibles ){
			List<int[]> currentChickens = new ArrayList<>();

			int cityChickenLength = 0;
			for ( Integer y : possible ){
				currentChickens.add(chickens.get(y));
			}

			int chickenLength = Integer.MAX_VALUE;
			for ( int [] homeLocation : homes ){
				chickenLength = Integer.MAX_VALUE;
				int homeX = homeLocation[0];
				int homeY = homeLocation[1];

				for ( int[] chickenLocation : currentChickens ){
					int chickenX = chickenLocation[0];
					int chickenY = chickenLocation[1];

					int cur = Math.abs(chickenX - homeX) + Math.abs(chickenY - homeY);
					if ( chickenLength > cur ){
						chickenLength = cur;
					}
				}
				cityChickenLength += chickenLength;
			}
			if ( cityChickenLength < answer ){
				answer = cityChickenLength;
			}
		}

		return answer;
	}

	private static List<List<Integer>> combination(int n, int r){
		List<List<Integer>> result = new ArrayList<>();
		Queue<List<Integer>> queue = new ArrayDeque<>();

		// 초기: 각 시작 인덱스로 시작하는 조합들 추가
		for(int i = 0; i < n; i++){
			List<Integer> initial = new ArrayList<>();
			initial.add(i);
			queue.offer(initial);
		}

		while(!queue.isEmpty()){
			List<Integer> current = queue.poll();

			// m개를 선택했으면 결과에 추가
			if(current.size() == r){
				result.add(current);
				continue;
			}

			// 마지막 원소 다음부터 선택 (중복 방지)
			int lastIdx = current.get(current.size() - 1);
			for(int i = lastIdx + 1; i < n; i++){
				List<Integer> next = new ArrayList<>(current);
				next.add(i);
				queue.offer(next);
			}
		}
		return result;
	}
}