package leetcode;

/**
 * @Title: leetcode167
 * @Author 曦
 * @Date 2025/5/19 20:32
 * @description:
 * 给你一个下标从 1 开始的整数数组 numbers ,该数组已按非递减顺序排列  ，
 * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 */

public class leetcode167 {
    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;
        int[] res = twoSum(numbers,target);
        for (int i : res) {
            System.out.println(i);
        }
    }
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while(left < right){
            if( numbers[left] + numbers[right] == target){
                break;
            }else if( numbers[left] + numbers[right] > target){
                right --;
            }else{
                left ++;
            }
        }
        return new int[]{left+1,right+1};
    }

}
