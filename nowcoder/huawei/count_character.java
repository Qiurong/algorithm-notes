package nowcoder.huawei;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/**
 * @description: 牛客网HJ2:计算字符个数
 * @author: Qr
 * @create: 2021-03-30 10:35
 **/
public class count_character {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String str = in.nextLine();
            str = str.toUpperCase();
            String word = in.nextLine();
            word = word.toUpperCase();
            if (str!=null && word!=null){
                int len = str.length();
                HashMap<Character,Integer> hashMap = new HashMap<>();
                for (int i = 0; i < len; i++) {
                    char c = str.charAt(i);
                    if (!hashMap.containsKey(c)){
                        hashMap.put(c,1);
                    }else {
                        hashMap.put(c, hashMap.get(c)+1);
                    }
                }
                char charcter = word.charAt(0);
                System.out.println(hashMap.get(charcter)==null? 0 : hashMap.get(charcter));
            }
        }
    }
}
