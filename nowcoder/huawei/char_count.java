package nowcoder.huawei;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @description: 牛客网HJ10: 字符个数统计
 * @author: Qr
 * @create: 2021-03-30 10:01
 **/
public class char_count {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String str = in.nextLine();
            if (str!=null){
                int num = 0;
                int len = str.length();
                HashSet<Character> hashSet = new HashSet<>();
                for (int i = 0; i < len; i++) {
                    char c = str.charAt(i);
                    if (!hashSet.contains(c)){
                        num++;
                        hashSet.add(c);
                    }
                }
                System.out.println(num);
            }
        }
    }
}
