package exam.alibaba;

/**
 * @description: 3.26笔试第一题
 * @author: Qr
 * @create: 2021-04-02 09:07
 **/

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 有n张卡牌，每张值为0或1。
 * 第一行输入整数T，接下来输入T组数据，每组数据的第一行输入n，第二行输入n个0/1，代表n张卡牌；
 * 输出从每组卡牌中随机抽走一个数后，连续为1的卡牌数量的最大值。
 * 示例：
 * 输入：
 * 2
 * 3
 * 1 1 1
 * 6
 * 1 0 1 0 1 1
 * 输出：
 * 2
 * 3
 */
public class card {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int groups = in.nextInt();    //组数
        ArrayList<Integer> groupMaxOnes = new ArrayList<>();    //每组最大连续1

        //每一组遍历
        for (int i = 0; i < groups; i++) {
            int maxOne = 0;         //连续为1的卡牌的最大数量
            int n = in.nextInt(); //每组里输入多少个0/1
            int[] array = new int[n];   //存放每组0/1原始数据的数组
            int j = 0;                  //原始数组的下标
            ArrayList<Integer> indexOfZeroes = new ArrayList<>(); //原数组中0的下标

            for (int k = 0; k < array.length; k++) {
                array[k] = in.nextInt();
            }

            //加一个变量用于看有没有0
            boolean hasZero = false;
            //在前面加上-1用于计算第一段
            indexOfZeroes.add(-1);
            for (int k = 0; k < array.length; k++) {
                if (array[k] == 0){
                    hasZero = true;
                    indexOfZeroes.add(k);
                }
            }
            //在最后加上n用于计算最后一段
            indexOfZeroes.add(n);

            //原数组中没有0
            if (!hasZero){
                groupMaxOnes.add(n-1);
                continue;
            }

            //从下标1开始遍历
            for (int k = 1; k < indexOfZeroes.size() - 1; k++) {
                maxOne = Math.max(maxOne,indexOfZeroes.get(k+1) - indexOfZeroes.get(k-1) - 2);
            }
            groupMaxOnes.add(maxOne);
        }
        for (int i = 0; i < groupMaxOnes.size(); i++) {
            System.out.println(groupMaxOnes.get(i));
        }
    }
}
