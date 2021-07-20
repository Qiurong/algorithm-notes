package stock_problems;

/**
 * @description: 122. 买卖股票的最佳时机 II
 * @author: Qr
 * @create: 2021-07-19 19:05
 *
 * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 **/
public class best_time_ii {
    /**
     * 动态规划问题最重要的是找到一个切入口去想动态方程，这题而言总共有两个变量，天数和当天结束之后手里有无股票。根据此可以规划出状态的转移.
     * f(i,j)：第i天结束之后最大利润，其中j代表手里有无股票, j只能取值0/1.
     * f(i,0) = MAX { f(i-1,0), f(i-1,1)+[i] } 对应为前一天没股票, 前一天有股票但今天卖了
     * f(i,1) = MAX { f(i-1,1), f(i-1,0)-[i] } 对应为前一天有股票, 前一天没股票但今天买了
     * 初始值：f(0,0) = 0, f(0,1) = -[0]
     */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //由于下一状态只跟上一状态有关, 可以把二维数组优化为两个一维数组
        int[] dp0 = new int[n];     //dp0[i]: f(i,0) 第i天之后手里没有股票的最大收益
        int[] dp1 = new int[n];     //dp1[i]: f(i,1) 第i天之后手里有股票的最大收益
        //1.dp初始值
        dp0[0] = 0;
        dp1[0] = -prices[0];
        //2.dp
        for (int i = 1; i < n; i++) {
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]);
            dp1[i] = Math.max(dp1[i-1], dp0[i-1] - prices[i]);
        }
        return dp0[n-1];
    }

    //还可以继续优化空间复杂度. 在两个dp数组中, 下一状态只与上一状态有关, 可以不用存整个数组, 而是不断滚动更新数值
    public int maxProfit_lessSpace(int[] prices) {
        int n = prices.length;
        //dp初始值
        int dp0 = 0;
        int dp1 = -prices[0];
        //dp的下一状态
        int nextdp0;
        int nextdp1;
        for (int i = 1; i < n; i++) {
            nextdp0 = Math.max(dp0, dp1+prices[i]);
            nextdp1 = Math.max(dp1, dp0-prices[i]);
            //滚动更新数组中的值
            dp0 = nextdp0;
            dp1 = nextdp1;
        }
        return dp0;
    }
}
