package dynamic_programming;

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

    //从前往后寻找
    public int jump_ForWards(int[] nums) {
        int destinationIndex = nums.length - 1;
        int jumpTimes = 0;
        return jumpTimes;
    }
}
