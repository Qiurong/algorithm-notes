package nowcoder.huawei;

import java.util.Scanner;

/**
 * @description: 牛客网HJ3:明明的随机数
 * @author: Qr
 * @create: 2021-03-30 11:14
 **/
public class remove_duplicates_and_sort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int n = in.nextInt();
            int [] sorted = new int[n+1];
            //预处理
            for (int i = 0; i < sorted.length; i++) {
                sorted[i] = 1001;
            }
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                insertSort(sorted,num);
            }
            for (int i = 0; sorted[i] != 1001; i++) {
                System.out.println(sorted[i]);
            }
        }
    }
    //插入排序, 如果数组中包含e则不做任何操作
    public static void insertSort(int[] sorted, int num){
        int index = 0;
        while (index < sorted.length && sorted[index] < num){
            index++;
        }
        //num需要插入sorted有效数组后面
        if (sorted[index] == 1001){
            sorted[index] = num;
        }
        //sorted中有num什么都不做
        else if (sorted[index] == num){
            return;
        }
        else {
            int endIndex = index;
            while (sorted[endIndex] != 1001){
                endIndex++;
            }
            for (int i = endIndex; i > index; i--) {
                sorted[i] = sorted[i - 1];
            }
            sorted[index]  = num;
        }
    }
}
