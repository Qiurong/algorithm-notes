package dynamic_programming;

/**
 * @description: 55. 跳跃游戏
 * @author: Qr
 * @create: 2021-03-12 20:39
 **/
public class jump_game {
    //从后往前：寻找符合条件的下标i使得 i + [i] >= num.length - 1.
    public boolean canJump(int[] nums) {
        if (nums.length == 0){
            return true;
        }
        int index = nums.length - 1;
        //第一次写这个循环结构没想出来怎么实现
        //index: 需要跳到的点
        for (int i = index - 1; i >= 0; i--){
            //将index更新为当前结点, 之后i--
            if (nums[i] + i >= index){
                index = i;
            }
        }

        return index == 0;
    }

    //贪心法: 遍历数组更新可以跳到的最远距离
    public boolean canJump_greedy(int[] nums) {
        int largestJumpIndex = 0;
        int destinationIndex = nums.length - 1;
        //这里是<=destinationIndex, 存在只有一个元素
        for (int i = 0; i <= destinationIndex; i++) {
            if (i <= largestJumpIndex){
                largestJumpIndex = Math.max(largestJumpIndex,i + nums[i]);
                if (largestJumpIndex >= destinationIndex){
                    return true;
                }
            }
        }
        return false;
    }
}
