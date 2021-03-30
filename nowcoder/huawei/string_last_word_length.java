package nowcoder.huawei;

import java.util.Scanner;

/**
 * @description: 牛客网HJ1:字符串最后一个单词长度
 * @author: Qr
 * @create: 2021-03-30 10:22
 **/
public class string_last_word_length {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String str = in.nextLine();
            if (str!=null){
                int len = str.length();
                int num = 0;
                for (int i = 0; i < len; i++) {
                    char c = str.charAt(i);
                    if (c==' '){
                        num = 0;
                    }else {
                        num++;
                    }
                }
                System.out.println(num);
            }
        }
    }
}
