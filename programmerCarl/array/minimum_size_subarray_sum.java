package programmerCarl.array;

/**
 * @description: 209. 长度最小的子数组
 * @author: Qr
 * @create: 2021-10-14 09:58
 **/
public class minimum_size_subarray_sum {
    //滑动窗口
    //窗口的写法不好,  一直各种报错, 最后经过好几次debug慢慢改代码才对
    //时间复杂度：O(n) left和right指针遍历数组元素各一遍， 最多2n次
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int winSum = nums[left];
        //增加一个变量 用于标记是否存在连续子数组 >= target
        boolean hasSubArray = false;

        //使用[left,right]代表滑动窗口
        while (left <= right && right < nums.length){
            //窗口右移至满足条件
            while (right < nums.length && winSum < target){
                right++;
                if (right < nums.length){
                    winSum += nums[right];
                }
            }
            if (winSum >= target){
                hasSubArray = true;
                min = Math.min(min, right-left+1);
            }
            //窗口左边界缩小
            winSum -= nums[left];
            left++;
        }
        return hasSubArray ? min : 0;
    }

    //确定滑动窗口为 左闭右闭写法
    public int minSubArrayLen_win(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        //1.left,right初值为0, 则winSum初值为nums[0]
        int left = 0;
        int right = 0;
        int winSum = nums[left];

        boolean hasSubArray = false;

        //2. 循环结束条件: left > right 或者 right超出边界
        while (left <= right && right < nums.length){
            //窗口右移至满足条件
            while (right < nums.length && winSum < target){
                //3.这里先++ 在求和
                right++;
                if (right < nums.length){
                    winSum += nums[right];
                }
            }
            if (winSum >= target){
                hasSubArray = true;
                min = Math.min(min, right-left+1);
            }
            //窗口左边界缩小
            winSum -= nums[left];
            left++;
        }
        return hasSubArray ? min : 0;
    }
}
