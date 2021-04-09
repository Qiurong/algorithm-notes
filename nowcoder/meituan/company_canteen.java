package nowcoder.meituan;

import java.util.Scanner;

/**
 * @description: https://www.nowcoder.com/contestRoom?mutiTagIds=179
 *              美团2021校招笔试第10场第三题：公司食堂
 * @author: Qr
 * @create: 2021-04-08 09:44
 **/
public class company_canteen {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int groups = Integer.parseInt(in.nextLine());       //组数
        for (int i = 0; i < groups; i++) {
            int tableNum = Integer.parseInt(in.nextLine()); //桌子数N
            String tableStr = in.nextLine();                //获取桌子状态的字符串
            int m = Integer.parseInt(in.nextLine());        //进来用餐的人数m
            String peoples = in.nextLine();                 //进来用餐的人数的具体情况
            int[] tableStatus = new int[tableNum];          //桌子状态数组，每个状态为0 1 2
            int firstZeroIndex = 0;                         //数组中第一个0的下标
            int firstOneIndex = 0;                          //数组中第一个1的下标
            boolean zeroSet = false;                        //初始化中标记0的下标是否被记录
            boolean oneSet = false;                         //初始化中标记1的下标是否被记录
            int k = 0;                                      //输出k即每个人被安排在哪个桌子

            for (int j = 0; j < tableStatus.length; j++) {
                tableStatus[j] = tableStr.charAt(j) - '0';
                if (tableStatus[j] == 0 && !zeroSet){
                    firstZeroIndex = j;
                    zeroSet = true;
                }
                if (tableStatus[j] == 1 && !oneSet){
                    firstOneIndex = j;
                    oneSet = true;
                }
            }

            //针对原数组中没有0或1的情况
            if (!zeroSet){
                firstZeroIndex = tableStatus.length;
            }
            if (!oneSet){
                firstOneIndex = tableStatus.length;
            }

            //开始遍历进来的人
            for (int j = 0; j < peoples.length(); j++) {
                if (peoples.charAt(j) == 'M'){
                    //存在1个人的桌子
                    if (firstOneIndex < tableStatus.length){
                        k = firstOneIndex + 1;
                        //更新该位置人数
                        tableStatus[firstOneIndex]++;
                        //更新firstOneIndex
                        firstOneIndex++;
                        while (firstOneIndex < tableStatus.length && tableStatus[firstOneIndex]!=1){
                            firstOneIndex++;
                        }
                    }
                    //不存在一个人的桌子 即 全是 0或者2
                    else {
                        //坐在第一个没人的桌子
                        k = firstZeroIndex + 1;
                        //更新该位置人数
                        tableStatus[firstZeroIndex]++;
                        //更新firstOneIndex
                        firstOneIndex = Math.min(firstOneIndex,firstZeroIndex);
                        //更新firstZeroIndex
                        firstZeroIndex++;
                        while (firstZeroIndex < tableStatus.length && tableStatus[firstZeroIndex]!=0){
                            firstZeroIndex++;
                        }
                    }
                }
                else if (peoples.charAt(j) == 'F'){
                    //存在0个人的桌子
                    if (firstZeroIndex < tableStatus.length){
                        k = firstZeroIndex + 1;
                        //更新该位置人数
                        tableStatus[firstZeroIndex]++;
                        //更新firstOneIndex
                        firstOneIndex = Math.min(firstOneIndex,firstZeroIndex);
                        //更新firstZeroIndex
                        firstZeroIndex++;
                        while (firstZeroIndex < tableStatus.length && tableStatus[firstZeroIndex]!=0){
                            firstZeroIndex++;
                        }
                    }
                    //不存在0个人的桌子，即全是1/2
                    else{
                        //坐在第一个1个人的桌子
                        k = firstOneIndex + 1;
                        //更新该位置人数
                        tableStatus[firstOneIndex]++;
                        //更新firstOneIndex
                        firstOneIndex++;
                        while (firstOneIndex < tableStatus.length && tableStatus[firstOneIndex]!=1){
                            firstOneIndex++;
                        }
                    }
                }
                System.out.println(k);
            }
        }
    }
}
