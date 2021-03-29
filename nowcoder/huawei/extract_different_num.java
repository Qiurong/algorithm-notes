package nowcoder.huawei;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @description: 牛客网HJ9:提取不重复的整数
 * @author: Qr
 * @create: 2021-03-29 20:48
 **/
public class extract_different_num {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int num = in.nextInt();
            String numStr = String.valueOf(num);
            StringBuilder finalNum = new StringBuilder();
            HashSet<Character> hashSet = new HashSet<>();
            for (int i = numStr.length() - 1; i >= 0; i--) {
                char currBit = numStr.charAt(i);
                if (hashSet.contains(currBit)){
                    continue;
                }else {
                    hashSet.add(currBit);
                    finalNum.append(currBit);
                }
            }
            int res = Integer.parseInt(finalNum.toString());
            System.out.println(res);
        }
    }
}
