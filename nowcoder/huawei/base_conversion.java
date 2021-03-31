package nowcoder.huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @description: 牛客网HJ5:进制转换 https://www.nowcoder.com/ta/huawei
 * @author: Qr
 * @create: 2021-03-29 16:14
 **/
public class base_conversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String str = in.next();
            char[] chars = str.toCharArray();
            int result = 0;
            for (int i = 2; i < chars.length; i++) {
                result += convert(chars[i]);
                result *= 16;
            }
            result = result / 16;
            System.out.println(result);
        }
    }

    public static int convert(char c){
        switch (c){
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default: return 0;
        }
    }
}
