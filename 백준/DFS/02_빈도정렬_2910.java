import java.util.*;

public class Main{

	public static void main(String[] args){
		// 5 2
		// 2 1 2 1 2
		int N = 5;
		int C = 2;
		int[] nums = new int[]{2,1,2,1,2};
		System.out.println(solution(N,C,nums));
	}

	private static String solution(int N, int C, int[] nums){
		// N 은 메시지의 길이
		// C 는 메시지의 최대 수
		// int nums 는 배열

		Map<Integer, Metadata> countMap = new HashMap<>();
		for ( int i = 0; i < nums.length; i++ ){
			int x = nums[i];
			if (!countMap.containsKey(x)) {
				Metadata meta = new Metadata();
				meta.count = 1;
				meta.firstIndex = i;
				countMap.put(x, meta);
			} else {
				countMap.get(x).count++;
			}
		}

		int[] sorted = Arrays.stream(nums)
			.boxed()
			.sorted((prev, after) -> calculateSortNums(prev, after, countMap))
			.mapToInt(Integer::intValue)
			.toArray();

		StringBuilder sb = new StringBuilder();

		for ( int x = 0 ; x < sorted.length ; x++ ){
			sb.append(sorted[x]);
			if ( x != sorted.length - 1 ){
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	private static Integer calculateSortNums(int prev, int after, Map<Integer, Metadata> countMap){
		// 양수가 나오면, prev 가 뒤로 감 , 음수가 나오면 , prev 가 앞으로 감
		Metadata prevMeta = countMap.get(prev);
		Metadata afterMeta = countMap.get(after);

		// 빈도수가 다르면 빈도수 기준 (내림차순)
		if (!prevMeta.count.equals(afterMeta.count)) {
			return afterMeta.count - prevMeta.count;
		}

		// 빈도수가 같으면 먼저 나온 순서대로 (오름차순)
		return prevMeta.firstIndex - afterMeta.firstIndex;
	}

	public static class Metadata{
		public Integer count;
		public Integer firstIndex;
	}

}