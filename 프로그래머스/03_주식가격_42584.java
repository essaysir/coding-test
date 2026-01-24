// 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때,
// 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

// prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
// prices의 길이는 2 이상 100,000 이하입니다.
import java.util.*;

public class Main{

	public static void main(String[] args){
		int[] prices = {1,2,3,2,3};
	// 	[1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
		int[] answer = solution(prices);
		System.out.println(Arrays.toString(answer));
	}

	public static int[] solution(int[] prices){
		// 주식이 떨어지지 않은 경우에 대해서 계산
		int[] answer = new int[prices.length];

		Stack<int[]> stack = new Stack<>();

		for ( int i = 0; i < prices.length; i++){
			// 1. 현재 가격과 이전 가격을 비교해서
			int currentPrice = prices[i];
			// [1,0]
			while (!stack.isEmpty() && currentPrice < stack.peek()[0]){
				// 뒤의 들어온 값이 더 크다면, 증가
				int[] prev = stack.pop();
				int prevIndex = prev[1];
				answer[prevIndex] = i - prevIndex;
			}
			stack.push(new int[]{currentPrice, i});
		}


		while (!stack.isEmpty()){
			int[] current = stack.pop();
			answer[current[1]] = (prices.length - 1) - current[1];
		}

		return answer;
	}
}