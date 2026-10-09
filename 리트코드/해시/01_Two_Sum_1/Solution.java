import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 무조건 하나의 정답만 존재하는 경우의 input 이 들어오겐 된다.
        int[] result = new int[2];

        // 실제 값 / 인덱스 ??
        Map<Integer, Integer> countMap = new HashMap<>();
        for ( int i = 0 ; i < nums.length; i ++){
            int needValue = target - nums[i];
            if ( countMap.containsKey(needValue)) {
                result[0] = i;
                result[1] = countMap.get(needValue);
                return result;
            }
            countMap.put(nums[i], i);

        }

        return result;
    }
}
