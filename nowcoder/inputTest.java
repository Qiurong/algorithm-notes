package exam.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @author: Qr
 * @create: 2021-09-08 21:37
 **/
public class inputTest {

    //总结： while in.hashNextInt() 在IDEA需要最后输入^D 作为EOF, 且在debug时，需要多输入一行 enter换行从而成功debug
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //n:结点个数
        int n = in.nextInt();
        //每个结点的val
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = in.nextInt();
        }
        //结点关系
        List<Integer> list = new ArrayList<>();
        while (in.hasNextInt()){
            int num = in.nextInt();
            list.add(num);
        }
        for (int num : list) {
            System.out.println(num);
        }
        /*List<String> lines = new ArrayList<>();
        while (in.hasNextLine()){
            String str = in.nextLine();
            lines.add(str);
        }
        for(String line : lines){
            System.out.println(line);
        }*/
    }
}
