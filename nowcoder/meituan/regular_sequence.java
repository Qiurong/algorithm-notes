package nowcoder.meituan;

import java.util.ArrayList;
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
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int n  = in.nextInt();
            int operationNum = 0;   //操作次数
            ArrayList<Integer> sequence = new ArrayList<>(n);   //存放原数组需要达成的状态
            //初始值为：1...n
            for (int i = 0; i < n; i++){
                sequence.add(i+1);
            }
            ArrayList<Integer> nums = new ArrayList<>();    //原数组
            //在1..n之内的数不需要操作，在1..n之外的数存入nums数组并后续操作
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                //移除sequence数组中一些状态
                //这里需要注意的是：可能存在多个相同num, 只能移除一个
                if(num>=1 && num<= n && sequence.contains(num)){
                    sequence.remove(sequence.indexOf(num));
                }else {
                    //需要操作的数
                    nums.add(num);
                }
            }

            //遍历需要达成的状态数组
            for (int i = 0; i < sequence.size(); i++) {
                int toBeNum = sequence.get(i);  //需要达成的数字
                //在nums数组中找离这个数字最近的数
                int beNum = 0;  //nums数组中离toBeNum最近的数字
                int minOperaNum = Integer.MAX_VALUE;       //当前数组距离toBeNum最小的操作次数
                //争对sequence当前下标的数toBeNum 寻找 与之最近的 nums中的数beNum
                for (int j = 0; j < nums.size(); j++) {
                    int currNum = nums.get(j);
                    int currOpearNum = Math.abs(toBeNum - currNum);
                    if (minOperaNum > currOpearNum){
                        minOperaNum = currOpearNum;
                        beNum = currNum;
                    }
                }
                operationNum+=minOperaNum;
                int index = nums.indexOf(beNum);
                if (index != -1){
                    nums.remove(index);
                }
            }

            System.out.println(operationNum);
        }
    }
}
