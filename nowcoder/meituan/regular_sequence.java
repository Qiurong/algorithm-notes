package nowcoder.meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: https://www.nowcoder.com/contestRoom?mutiTagIds=179
 *               美团2021校招笔试第10场第二题：正则序列
 * @author: Qr
 * @create: 2021-04-08 09:43
 **/
public class regular_sequence {

    /**
     *  知识点：排序+贪心
     *  自己做时的想法：基本策略是贪心，对于原数组中属于正则序列的不动，剩余的元素每个元素寻找与之对应的最近的正则序列元素。
     *  自己初始想法其实基本正确，但错在 1.删除多个属于[1..n]的元素  需要解决的问题：原数组中多个属于[1..n]相同的值只删除一个
     *                             2.在剩余的nums数组中，剩余元素贪心错误
     *                               原数组：3 0 3 1 3, 移除后
     *                               剩余序列：2 4 5
     *                               剩余数组：3 0 3. 那么3-2:1, 3-4:1 0-5:5 操作次数：7
     *  正确应该是：0-2:2 3-4:1 3-5:2 操作次数：5 即先升序原数组，然后计算原数组和剩余序列的相同下标的差
     *  更进一步：一开始不用移除原数组中属于正则序列的元素，直接原数组升序，然后计算原数组和剩余序列的相同下标的差。
     *  3 0 3 1 3 ---> 0 1 3 3 3
     *                 1 2 3 4 5
     *                 1 1 0 1 2，操作次数5.
     *  总结：这题还是没发现题中的规律，对于贪心策略的运用和实现不熟。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int n  = in.nextInt();
            int operationNum = 0;   //操作次数
            ArrayList<Integer> sequence = new ArrayList<>(n);   //存放原数组需要达成的状态
            int[]nums = new int[n];    //原数组

            //sequence初始值为：1...n
            for (int i = 0; i < n; i++){
                sequence.add(i+1);
            }

            //nums数组赋值
            for (int i = 0; i < n; i++){
                nums[i] = in.nextInt();
            }
            Arrays.sort(nums);

            //遍历计算操作次数
            for (int i = 0; i < n; i++) {
                operationNum+= Math.abs(nums[i] - sequence.get(i));
            }

            System.out.println(operationNum);
        }
    }
}
