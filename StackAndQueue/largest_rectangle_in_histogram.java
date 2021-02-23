package StackAndQueue;

/**
 * @description: 84. 柱状图中最大的矩形
 * @author: Qr
 * @create: 2021-02-23 10:33
 **/
public class largest_rectangle_in_histogram {

    //这种方法超出了时间限制：最差时间复杂度：O(n^2)
    //暴力解法: 一个个算以当前柱的高度为最大高度的最大矩形面积
    public int largestRectangleArea_brutalForce(int[] heights) {
        int maxArea = 0 ;
        for (int i = 0 ; i < heights.length ; i++){
            int length = maxLength(heights, i);
            int area = length * heights[i];
            maxArea = maxArea > area ? maxArea : area;
        }
        return maxArea;
    }

    //找到以当前柱为高的矩形的最大长
    //即找到左边和右边第一个小于当前高度
    public int maxLength(int [] heights, int i){
        int m = i;
        int n = i;
        while(m >= 0){
            if (heights[m] >= heights[i]){
                m--;
            }else {
                break;
            }
        }
        while(n < heights.length){
            if (heights[n] >= heights[i]){
                n++;
            }else {
                break;
            }
        }

        int length = n-m-1;
        length = length > 1 ? length : 1;
        return length;
    }

}
