package bitOperation;

/**
 * @description: 461. 汉明距离
 * @author: Qr
 * @create: 2021-07-22 16:20
 *
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * 示例 1：
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 *上面的箭头指出了对应二进制位不同的位置。
 *
 * 示例 2：
 * 输入：x = 3, y = 1
 * 输出：1
 **/
public class hamming_distance {
    //异或之后求二进制中1的个数
    public int hammingDistance(int x, int y) {
        int distanceNum = 0;
        int z = x ^ y;
        //Brian Kernighan 算法 : 对于整数x, x = x & (x-1), 可以消去二进制位最后一个1, 直至x=0, 操作的次数就是二进制中1的个数
        while (z != 0){
            distanceNum++;
            z = z &(z-1);
        }
        return distanceNum;
    }
}
