package bitOperation;

/**
 * @description: 338. 比特位计数
 * @author: Qr
 * @create: 2021-07-22 09:51
 *
 * 给定一个非负整数num。对于0 ≤ i ≤ num 范围中的每个数字i，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 **/
public class counting_bits {

    /**
     * 通过观察可以发现 0 = 0 * 2^0
     *               1 = 1 * 2^0
     *               2 = 1 * 2^1
     *               3 = 2+1  ---> 3%2=1
     *               4 = 1 * 2^2
     *               5 = 4+1  ---> 5%4 = 1
     *               6 = 4+2  ---> 6%4 = 2
     *               7 = 4+3  ---> 7%4 = 3
     *               8 = 1 * 2^3
     * 发现n的2进制中 有几位1 取决于 n % 小于等于n的最大二次方数字， 最后1的位数 = 1 + 取余之后的数的位数
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] bitsCount = new int[n+1];
        if (n == 0){
            return bitsCount;
        }
        bitsCount[1] = 1;
        //小于等于i的最大二次方数
        int x = 1;
        for (int i = 2; i < n+1; i++) {
            //i为二次方数则需要更新x
            if (i == (x*2)){
                bitsCount[i] = 1;
                x *= 2;
            }else {
                bitsCount[i] = 1 + bitsCount[i % x];
            }
        }
        return bitsCount;
    }

    //看了官方题解, 对于我的方法中的 x 给出了一个定义叫最高有效位 highBit, 最高有效位就是 <= i的 最大二进制数
    // 其中可以通过  i&(i-1) 来快速检验 i是不是二次方数， 再次感慨：位操作的骚技巧太多了
    public int[] countBits_highBit(int n){
        int[] bitCounts = new int[n+1];
        int highBit = 0;
        for (int i = 1; i < n+1; i++) {
            //通过 i&(i-1) 来快速检验 i是不是 二次方数
            if ((i & (i-1)) == 0){
                highBit = i;
                bitCounts[i] = 1;
            }else {
                bitCounts[i] = bitCounts[i-highBit] + 1;
            }
        }
        return bitCounts;
    }
}
