package LeetCode.Q1_Sum;


import java.util.HashMap;


/**
 * @author bockey
 */
public class Sum1 {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer k = map.put(nums[i], i);
            if (k != null) {
                if (nums[i] == target - nums[k]) {
                    int[] ret = {i, k};
                    return ret;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                if (target - nums[i] == nums[i]) {
                    continue;
                }
                int[] ret = {i, map.get(target - nums[i])};
                return ret;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = {-3, 4, 3, 90};
        int[] a2 = twoSum(a, 0);
        for (int i : a2) {
            System.out.println(i);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer put = map.put(1, 1);
        Integer put2 = map.put(1, 2);
//        System.out.println(put);
//        System.out.println(put2);
    }
}
