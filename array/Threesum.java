package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 15. 三数之和
 * @author: Qr
 * @create: 2021-06-18 14:06
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 输入：【-1,0,1,2,-1,-4】
 *      【【-1，-1，2】，【-1，0，1】】
 *
 **/
public class Threesum {

    /**
     * 思路：在two_sum的基础上变成three_sum，那么可以把three_sum看作是 两个数字 + 第三个数字，也就是two_sum。
     * two_sum做法：1. 最原始，o(n^2)暴搜，
     *             2. 优化后可以先排序，然后i从左向右，j从右向左，这样可以剪枝，但还是O(n^2)
     *             3. 进行进一步抽象之后发现其实是要寻找 target-x，那么可以把 寻找的操作 通过空间换时间的方法 即用map先存再找。
     *                这样就可以实现 寻找操作的时间复杂度变成O(1).
     *  three_sum: 相似的也可以 x+y = z, 需要寻找target-z, 但无法保证从map中取出的 target-z 是不是 x或y，所以这种方法不行。
     *             那么就相应的仿照第二种做法，
     *             x:i从左向右,
     *             y:j=i+1从左向右,
     *             z:从右想左
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 2){
            return res;
        }
        //1.先排序
        Arrays.sort(nums);
        //全正数的特殊情况
        if (nums[0] > 0){
            return res;
        }
        //i之后至少两个元素
        for (int i = 0; i < nums.length - 2; i++) {
            //过滤重复解
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            //k从右向左遍历
            int k = nums.length - 1;
            for (int j = i + 1; j < k; j++) {
                //过滤重复解
                if (j > i+ 1 && nums[j] == nums[j-1]){
                    continue;
                }
                int twoSum = nums[i] + nums[j];
                //x+y+z > 0: z需要变小，k左移
                while (j < k && nums[i] + nums [j] + nums[k] > 0){
                    k--;
                }
                //j == k需要排除
                if (j == k){
                    continue;
                }
                //x+y+z = 0 : y右移，寻找下一个解
                if (twoSum + nums[k] == 0){
                    List<Integer> currRes = new ArrayList<>();
                    currRes.add(nums[i]);
                    currRes.add(nums[j]);
                    currRes.add(nums[k]);
                    res.add(currRes);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums= {-1,0,1,2,-1,-4,2,3,4,-2,3};
        List<List<Integer>> res = threeSum(nums);
        System.out.println(res);


    }

}
