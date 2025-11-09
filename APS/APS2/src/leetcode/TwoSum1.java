package leetcode;

import java.util.Scanner;

public class TwoSum1 {
    public static int[] twoSum(int[] nums, int target){
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    return new int[] {i, j};
                }
            }
        }
        return new int[]{};
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(twoSum(new int[]{3,2,4},6));
    }
}
