package heap;

/**
 * @description: 263. 丑数
 * @author: Qr
 * @create: 2021-04-07 10:42
 **/
public class ugly_number {
    public boolean isUgly(int n) {
        //0和负数不是丑数
        if (n <= 0){
            return false;
        }
        //1是丑数
        if (n == 1){
            return true;
        }

        boolean isUgly = false;

        while (n % 2 == 0){
            n /= 2 ;
        }
        while (n % 3 == 0){
            n /= 3 ;
        }
        while (n % 5 == 0){
            n /= 5 ;
        }

        if (n == 1){
            isUgly = true;
        }else {
            isUgly = false;
        }
        return isUgly;
    }
}
