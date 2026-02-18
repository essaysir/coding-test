import java.util.*;

class Solution {
	// https://school.programmers.co.kr/learn/courses/30/lessons/42862
	public int solution(int n, int[] lost, int[] reserve) {
		Arrays.sort(lost);
		Arrays.sort(reserve);
		// lost -> 잃어버린 학생 학생들의 집합
		// reverse -> 여분의 가져온 학생들의 집합
		// 전체 N 명 ( 2 이상 30 이하 )

		// 가져온 사람들이 줄 수 있는 경우 ??
		// 어떻게 해야 최적의 해지?

		// 1. 가져온 애들 중에서, 자기가 도둑 맞은 애들의 경우를 빼야 함.
		// 두 배열에서 동일한 수를 어떻게 뽑을까 ?
		for ( int i = 0; i < reserve.length; i ++){
			for ( int j = 0; j < lost.length; j++){
				if ( reserve[i] == lost[j]){
					reserve[i] = -1;
					lost[j] = -1;
					break;
				}
			}
		}

		for ( int i = 0; i < lost.length; i++){
			if ( lost[i] == -1 ) continue;
			int currentLost = lost[i];
			for ( int j = 0; j < reserve.length; j++){
				if ( currentLost - 1 == reserve[j] || currentLost + 1 == reserve[j]){
					// 빌려줄 수 있는 경우
					lost[i] = -1;
					reserve[j] = -1;
					break;
				}

				if ( reserve[j] > currentLost + 1) {
					break;
				}
			}
		}

		int count = 0;
		for ( int i = 0; i < lost.length; i++){
			if ( lost[i]  != -1){
				count ++;
			}
		}

		return n - count;
	}
}