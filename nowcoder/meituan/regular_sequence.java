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
     *  自己初始想法其实基本正确，但错在 1.删除多个属于[1..n]的元素
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
                if(num>=1 && num<= n){
                    int index = sequence.indexOf(num);
                    if (index != -1){
                        sequence.remove(index);
                    }
                }else {
                    //需要操作的数
                    nums.add(num);
                }
            }


            //遍历需要达成的状态数组
            for (int i = 0; i < sequence.size(); i++) {
                int toBeNum = sequence.get(i);  //需要达成的数字
                if (!nums.isEmpty()){
                    //在nums数组中找离这个数字最近的数
                    int beNum = nums.get(0);    //nums数组中离toBeNum最近的数字
                    int minOperaNum = Math.abs(toBeNum - beNum);       //当前数组距离toBeNum最小的操作次数
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


            }

            System.out.println(operationNum);
        }
    }
}
