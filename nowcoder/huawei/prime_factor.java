package nowcoder.huawei;

import java.util.Scanner;

/**
 * @description: 牛客网HJ6:质数因子
 * @author: Qr
 * @create: 2021-03-29 16:57
 **/
public class prime_factor {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            Long num = in.nextLong();
            for (int i = 2; i <= Math.sqrt(num); i++) {
                while (num % i == 0) {
                    System.out.print(i + " ");
                    num /= i;
                    if (num == 1){
                        break;
                    }
                }
            }
            System.out.print(num == 1 ? "" : num + " ");
        }
    }
}
