package interview_questions;

/**
 * @description: 42. 接雨水
 * @author: Qr
 * @create: 2021-07-27 16:28
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 *
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 **/
public class trapping_rain_water {
    //按列求, 求出每一列的左右最高高度, 那么这一列的最高高度就= min(左右最高高度), 这一列的雨水= 最高高度- 高度
    //时间:O(N) 空间:O(N)
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        if (n == 0){
            return res;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        //初始化leftMax
        leftMax[0] = 0;
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1],height[i-1]);
        }
        //初始化rightMax
        rightMax[n-1] = 0;
        for (int i = n-2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i+1],height[i+1]);
        }
        //计算雨水大小
        for (int i = 1; i < n-1; i++) {
            int maxHeight = Math.min(leftMax[i],rightMax[i]);
            if (maxHeight > height[i]){
                maxHeight -= height[i];
                res += maxHeight;
            }
        }
        return res;
    }
}
