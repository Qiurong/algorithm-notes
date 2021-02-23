package StackAndQueue;

import java.util.Stack;

/**
 * @description: 84. 柱状图中最大的矩形
 * @author: Qr
 * @create: 2021-02-23 10:33
 **/
public class largest_rectangle_in_histogram {

    //这种方法超出了时间限制：最差时间复杂度：O(n^2)
    //暴力解法: 一个个算以当前柱的高度为最大高度的最大矩形面积
    public int largestRectangleArea_brutalForce(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int length = maxLength(heights, i);
            int area = length * heights[i];
            maxArea = maxArea > area ? maxArea : area;
        }
        return maxArea;
    }
    //找到以当前柱为高的矩形的最大长
    //即找到左边和右边第一个小于当前高度
    public int maxLength(int[] heights, int i) {
        int m = i;
        int n = i;
        while (m >= 0) {
            if (heights[m] >= heights[i]) {
                m--;
            } else {
                break;
            }
        }
        while (n < heights.length) {
            if (heights[n] >= heights[i]) {
                n++;
            } else {
                break;
            }
        }
        int length = n - m - 1;
        length = length > 1 ? length : 1;
        return length;
    }

    //第一种暴力解法时间O(n^2), 空间O(1), 能否记录一些信息从而降低时间复杂度? 对应的就是空间换时间,遍历的同时记录一些信息
    //第二种方法: 用栈去存储遍历的柱形, 每次遍历到当前元素<栈顶元素(上一个元素)即依次出栈计算每个柱形的最大矩形面积。
    //时间复杂度和空间复杂度均为O(n)
    public static int largestRectangleArea_withStack(int[] heights) {
        int len = heights.length;
        //判空
        if (len == 0){
            return 0;
        }
        if(len == 1){
            return heights[0];
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++){
            //当前柱形高度 < 上一个柱形高度(栈顶柱形高度)时
            //需要从右至左计算所有大于 当前柱形高度的 柱形的最大矩形面积。
            //这样就会使得栈中的柱形高度始终为递增(栈中存储元素为下标，可以根据下标直接获取高度)
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()] ){
                //长度右标为当前下标
                int high = i;
                //获取出栈柱形的高度
                int height = heights[stack.pop()];
                //对于高度一样的柱形，一直往左
                while (!stack.isEmpty() && heights[stack.peek()] == height) {
                    stack.pop();
                }
                int low;
                //长度左标为此时的栈顶元素(下标)
                if (!stack.isEmpty()){
                    low = stack.peek();
                }
                else {
                    //栈为空的情况下, 左标为-1
                    low = -1;
                }
                int length = high - low - 1;
                int tempArea = height * length;
                maxArea = maxArea > tempArea ? maxArea : tempArea;
            }
            stack.push(i);
        }

        //遍历栈中剩余的柱形并计算其对应的最大矩形面积
        while (!stack.isEmpty()){
            //此时的右标为数组最大下标+1即len
            int high = len;
            int height = heights[stack.pop()];
            //对于高度一样的柱形，一直往左
            while (!stack.isEmpty() && heights[stack.peek()] == height) {
                stack.pop();
            }
            int Low;
            if (!stack.isEmpty()){
                Low = stack.peek();
            }else {
                Low = -1;
            }
            int tempArea = height * (high - Low - 1);
            maxArea = maxArea > tempArea ? maxArea : tempArea;
        }
        return maxArea;
    }

}