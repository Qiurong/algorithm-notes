package interview_questions;


import java.util.Stack;

/**
 * @description: 456. 132 模式
 * @author: Qr
 * @create: 2021-07-26 16:27
 *
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 *
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 *
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 **/
public class pattern_132 {
    //min从左向右, 然后找到min之后大于 nums[min]的数 max, 之后再找到 max之后小于 max并且大于min 的数mid4\
    //时间：O(n^3)  空间:0(1)
    public boolean find132pattern(int[] nums) {
        boolean isPattern = false;
        if (nums.length <= 2){
            return isPattern;
        }
        int min = 0;
        for (; min < nums.length; min++) {
            for (int max = min+1; max < nums.length; max++) {
                //找到大于min的max
                if (nums[max] > nums[min]){
                    for (int mid = max+1; mid < nums.length; mid++) {
                        if (nums[mid] > nums[min] && nums[mid] < nums[max]){
                            isPattern = true;
                            return isPattern;
                        }
                    }
                }
            }
        }
        return isPattern;
    }

    //用currMax 来记录 到目前下标最大的数组元素
    //可以把时间复杂度降为O(n^2), 空间:O(1)
    public boolean find132pattern_optimize(int[] nums){
        boolean isPattern = false;
        if (nums.length <= 2){
            return isPattern;
        }
        int currMax = 0;
        for(int min = 0;min < nums.length-2;min++){
            currMax = nums[min];
            for (int mid = min+1; mid < nums.length; mid++) {
                currMax = Math.max(currMax,nums[mid]);
                if (nums[mid] > nums[min] && nums[mid] < currMax){
                    isPattern = true;
                    return isPattern;
                }
            }
        }
        return isPattern;
    }

    //需要优化到O(n). 具体做法：把132转换为  1+32, 用一个数据结构去维护2的可能值, 同时保证2<3, 那么 1<2的话，就能得到1<2<3
    //单调递减栈:栈顶为最小元素, 用于维护所有可能的2的取值. 同时用一个变量maxK去保存 当前遍历数 右边的第二大数字, 即最大的mid
    //         在从右向左遍历的过程中, 如果 nums[i] < maxK,那么存在
    //                            否则需要更新栈和maxK:
    //        更新栈之前, 根据单调栈性质需要pop出所有 小于 num[i]的元素, 在这个过程中pop的同时更新maxK, 那么就能得到 小于nums[i]的最大元素
    //                  然后把 nums[i]入栈.
    //        所以才能  nums[i] < maxK ===> maxK<栈中元素, 得到存在132模式
    public boolean find132pattern_stack(int[] nums){
        boolean isPattern = false;
        int n = nums.length;
        if (n <= 2){
            return isPattern;
        }
        //单调递减栈: 栈顶为最小元素
        Stack<Integer> downStack = new Stack<>();
        downStack.add(nums[nums.length-1]);
        //maxK开始赋初值
        int maxK = Integer.MIN_VALUE;
        //枚举min
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < maxK){
                isPattern = true;
                break;
            }
            //否则更新栈和maxK
            //1.更新maxK, 获取到栈中 < nums[i]的最大数。  单调递减栈, 所以越往下pop, 获取的值越大
            while (!downStack.isEmpty() && downStack.peek() < nums[i]){
                maxK = downStack.pop();
            }
            //2. push nums[i]
            if (nums[i] > maxK){
                downStack.push(nums[i]);
            }
        }
        return isPattern;
    }
}
