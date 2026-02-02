import java.util.*;
public class Main{

	public static void main(String[] args){
		int[] queue1 = {3,2,7,2};
		int[] queue2 = {4,6,5,1};

		System.out.println(solution(queue1,queue2));
	}

	private static int solution(int[] queue1, int[] queue2){
		int len = queue1.length;
		int totalLen = len * 2;
		int[] arr = new int[totalLen];
		// 1 ≤ queue1의 원소, queue2의 원소 ≤ 109
		long sum = 0; // 항상 숫자인 경우 범위 조심하기
		long currentSum = 0;

		for ( int i = 0; i < len; i++ ){
			arr[i] = queue1[i];
			arr[i+len] = queue2[i];
			sum += queue1[i] + queue2[i];
			currentSum += arr[i];
		}

		// 항상 안되는 경우, 미리 제한 할 수 있는 경우 고려하기
		if ( sum % 2 != 0) return -1;

		long target = sum / 2;
		int lt = 0;
		int rt = len;
		int count = 0;
		int maxCount = len * 4;

		while ( count < maxCount){
			if (currentSum == target){
				return count;
			}

			if (currentSum > target) {
				// lt가 rt를 넘어가면 불가능
				if (lt >= rt) return -1;
				currentSum -= arr[lt];
				lt++;
			} else {
				// rt가 배열 끝을 넘어가면 불가능
				if (rt >= totalLen) return -1;
				currentSum += arr[rt];
				rt++;
			}
			count++;
		}
		return -1;
	}
}