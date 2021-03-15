package dynamic_programming;

import sun.nio.cs.ext.MacArabic;

/**
 * @description: 45. 跳跃游戏 II
 * @author: Qr
 * @create: 2021-03-14 22:22
 **/
public class jump_game_ii {

    //从后向前贪心寻找能跳到目的地 的最小下标
    //空间:0(1)  时间: O(n^2)
    public int jump_BackWards(int[] nums) {
        int destinationIndex = nums.length - 1;
        int jumpTimes = 0;
        int jumpPoint = destinationIndex - 1;
        while (destinationIndex != 0){
            int i = destinationIndex - 1;
            //从后往前每次寻找 能跳到目的地 的最小下标，不断迭代
            while (i >= 0){
                if ((i + nums[i]) >= destinationIndex){
                    jumpPoint = Math.min(jumpPoint, i);
                }
                i--;
            }
            destinationIndex = jumpPoint;
            jumpTimes++;
        }
        return jumpTimes;
    }

    //从前往后寻找以降低时间复杂度。
    //每次在当前步可到达的  区间  内，找出下一步可以达到的最远距离。
    //时间复杂度: O(n), 空间复杂度:O(1)
    public int jump_Forwards(int[] nums) {
        int destinationIndex = nums.length - 1;
        int jumpTimes = 0;
        int largestIndex = 0;
        //当前步可达的区间末端
        int end = 0;
        //这里 < destinationIndex因为destination总是可达的
        for (int i = 0; i < destinationIndex; i++) {
            //找出当前区间中可以达到的最远距离
            largestIndex = Math.max(largestIndex, i + nums[i]);
            //走到了当前步的区间末端
            if (i == end){
                //更新区间末端
                end = largestIndex;
                jumpTimes++;
            }
        }
        return jumpTimes;
    }
}
