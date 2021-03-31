package nowcoder.huawei;

import java.util.Scanner;

/**
 * @description: 牛客网HJ24：合唱队
 * @author: Qr
 * @create: 2021-03-31 15:39
 **/
public class chorus_height {

    //转换问题为寻找给定的数组的最长升序序列
    //从左向右来一遍，在从右向左来一遍
    //取对应元素的dp最大值表示 以当前元素为中心，左边递增，右边递减的最大序列值 -1.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int[] heights = new int[n];
            int[] dpOne = new int[n];
            int[] dpTwo = new int[n];
            int max = 0;
            for (int i = 0; i < n; i++) {
                heights[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                dpOne[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (heights[i] > heights[j]){
                        dpOne[i] = Math.max(dpOne[j]+1,dpOne[i]);
                    }
                }
            }
            for (int i = n-1; i >= 0; i--) {
                dpTwo[i] = 1;
                for (int j = n-1; j > i; j--) {
                    if (heights[i] > heights[j]){
                        dpTwo[i] = Math.max(dpTwo[j]+1,dpTwo[i]);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                max = Math.max(dpOne[i]+dpTwo[i],max);
            }
            System.out.println(n-max+1);
        }

    }
}
