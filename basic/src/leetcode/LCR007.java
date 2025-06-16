package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: LCR007
 * @Author 曦
 * @Date 2025/5/19 21:14
 * @description:
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？
 * 请找出所有和为 0 且 不重复 的三元组。
 * 示例 1：  输入：nums = [-1,0,1,2,-1,-4] 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：  输入：nums = [] 输出：[] 示例 3：  输入：nums = [0] 输出：[]
 */

public class LCR007 {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = threeSum(nums);
        for(List<Integer> list : res){
            System.out.print(list);
        }


    }
    public static List<List<Integer>> threeSum(int[] nums) {
        //对数组排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n - 2; i++){
            if( i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            //优化
            if(nums[i] +nums[i+1] + nums[i+2] > 0){
                break;
            }
            if(nums[i] +nums[n-1] + nums[n-2] < 0){
                continue;
            }
            int j = i +1;
            int k = n - 1;
            while(j < k){
                int s = nums[i] + nums[j] + nums[k];
                if(s < 0){
                    j ++ ;
                }else if(s > 0){
                    k --;
                }else{
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j ++;
                    while(j < k && nums[j] == nums[j+1]){
                        j ++;
                    }
                    k--;
                    while(j < k && nums[k] == nums[k-1]){
                        k --;
                    }
                }
            }
        }
        return res;
    }

}
