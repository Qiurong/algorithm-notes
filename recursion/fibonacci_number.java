package recursion;

/**
 * @description: 509. 斐波那契数
 * @author: Qr
 * @create: 2021-04-14 09:53
 **/
public class fibonacci_number {
    public int fib_basicRecursion(int n) {
        if(n == 0 || n == 1){
            return n;
        } else {
            return fib_basicRecursion(n-1) + fib_basicRecursion(n-2);
        }
    }

    public int fib_traverse(int n){
        if (n == 0 || n == 1){
            return n;
        }
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[n];
    }

    //用滚动数组思维优化空间复杂度
    public int fib_roratingArray(int n){
        if (n == 0 || n == 1){
            return n;
        }
        int p1 = 0;
        int p2 = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = p1 + p2;
            p1 = p2;
            p2 = res;
        }
        return res;
    }
}
