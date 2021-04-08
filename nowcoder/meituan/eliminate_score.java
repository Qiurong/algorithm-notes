package nowcoder.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: https://www.nowcoder.com/contestRoom?mutiTagIds=179
 *               美团2021校招笔试第10场第一题：淘汰分数
 * @author: Qr
 * @create: 2021-04-08 09:41
 **/
public class eliminate_score {

    /**
     * 先给数组排序, 然后选取m,遍历。根据题意计算出淘汰(<=m)的人数 和 晋级(>m)的人数 是否满足 [x,y]
     * 可以缩小m的范围
     * m >= [x-1]: 此时淘汰x个人
     * m <= [y-1]: 此时淘汰y个人
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int n = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();

            int[] scores = new int[n];
            for (int i = 0; i < scores.length; i++) {
                scores[i] = in.nextInt();
            }
            Arrays.sort(scores);

            int m = scores[x-1];
            int index = x - 1;      //<= m的最大下标

            while (m <= scores[y-1]){
                //更新index
                while(scores[index+1] <= m){
                    index++;
                }

                int eleIndex = index;
                int winIndex = index;

                //找到第一个>m的下标
                while (scores[winIndex] <= m){
                    winIndex++;
                }
                int eleNum = eleIndex + 1;         //淘汰人数
                int winNum = n - winIndex;         //晋级人数
                if (eleNum>=x && eleNum<=y && winNum>=x && winNum<=y){
                    break;
                }

                m++;
            }
            if (m == scores[y-1] + 1){
                m = -1;
            }
            System.out.println(m);
        }

    }
}
