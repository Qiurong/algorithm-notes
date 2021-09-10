package array;

/**
 * @description: 45. 跳跃游戏 II
 * @author: Qr
 * @create: 2021-09-10 10:15
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。

 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *     从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2

 **/
public class jumpGameii {
    //思路： 每步都贪心算出下一次可到的最大范围, 当最大范围包括了重点就结束
    public int jump(int[] nums) {
        //特判, 不需要跳
        if (nums.length == 1){
            return 0;
        }
        int des = nums.length - 1;
        int jump = 0;
        //当前子数组中可跳最远距离
        int maxPos = 0;
        //每一步的边界
        int end = 0;
        //从左到右遍历数组
        for (int i = 0; i < nums.length; i++) {
            //更新当前下标可达最远距离
            maxPos = Math.max(maxPos,i + nums[i]);
            //跳到终点, 需要加一次jump
            if (maxPos >= des){
                jump++;
                break;
            }
            //到达边界, 更新边界为当前可达最远距离
            if (i == end){
                end = maxPos;
                jump++;
            }
        }
        return jump;
    }
}
