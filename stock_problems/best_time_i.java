package stock_problems;

import java.lang.management.ManagementFactory;

/**
 * @description: 121. 买卖股票的最佳时机
 * @author: Qr
 * @create: 2021-07-19 18:42
 **/
public class best_time_i {

    //思路: 最大差 = MAX{[j] - [i]}.
    //一开始思路错了，想的是去找i 之后的 最大值j, 无论怎么优化(记录i之后的最大值j, j之前的最大值都是j, j之后的最大值还需要重新遍历), 时间复杂度都是O(N^2).
    //                                    最好情况升序序列O(N), 最差情况降序序列O(n^2), 每走一步都需要遍历更新之后的最大值.
    //可以反过来记录一个当前i天之前的最小量, 这样在i每走一步的时候都可以更新最小量, 遍历与更新最小值的操作放在了一起， 优化为O(n)
    //转换 寻找之后的最高点-当天价格  为  当天价格-寻找之前的最低点
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit,prices[i] - minPrice);
            minPrice = Math.min(minPrice,prices[i]);
        }
        return maxProfit;
    }
}
