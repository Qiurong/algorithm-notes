package stock_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 123. 买卖股票的最佳时机 III
 * @author: Qr
 * @create: 2021-07-20 15:22
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *

 * 示例1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 *
 **/
public class best_time_iii {
    /**
     * 思路: 这是一个加了限制条件的dp, 可以存储每次卖出时的收益, 然后算出限制两次卖出时的最大收益
     * 错误解答！！！！ 遇到那种连起来的多次买卖无法处理, 比如 prices={1,2,3,4,5} everyGain={1,1,1,1}四次卖出,但其实这四次卖出完全等同于一次卖出
     */
    public int maxProfit_wrong(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1){
            return 0;
        }
        if (n == 2){
            return Math.max(0, prices[1]-prices[0]);
        }
        int[] dp0 = new int[n];                         //第i天 没有股票的最大收益
        int[] dp1 = new int[n];                         //第i天  有股票的最大收益
        List<Integer> everyGain = new ArrayList<>();    //记录下每次卖出的收益, 用于最后计算只卖两次的最大收益
        int lastSellGain = 0;                           //上次卖出之后的总收益
        //初始值
        dp0[0] = 0;
        dp1[0] = -prices[0];
        //dp过程
        for (int i = 1; i < n; i++) {
            //1.计算dp0[i] = Math.max(dp0[i-1], dp1[i-1]+prices[i]);
            int currGain = dp1[i-1] + prices[i];
            if (currGain > dp0[i-1]){
                dp0[i] = currGain;
                everyGain.add(currGain - lastSellGain);
                //更新上次卖出之后的总收益
                lastSellGain = currGain;
            }else {
                dp0[i] = dp0[i-1];
            }
            //2.计算dp1[i] = Math.max(dp1[i-1], dp0[i-1]-prices[i]]);
            dp1[i] = Math.max(dp1[i-1], dp0[i-1]-prices[i]);
        }
        int maxTwoSellGain = getLimitedGain(everyGain);
        return maxTwoSellGain;
    }


    /**
     *  获取 限制两次卖出 的最大收益
     * @param everyGain : 每次交易单次获取的利益, 按照时间顺序排序
     * @return
     */
    public int getLimitedGain(List<Integer> everyGain){
        int maxGain = 0;
        //交易次数<2, 则直接累加获得总收益
        if (everyGain.size() <= 2){
            for (int i = 0; i < everyGain.size(); i++) {
                maxGain += everyGain.get(i);
            }
        }else{
            //算出两次交易最大的gain
            for (int i = 0; i < everyGain.size() - 1; i++) {
                for (int j = i+1; j < everyGain.size(); j++) {
                    maxGain = Math.max(maxGain,everyGain.get(i) + everyGain.get(j));
                }
            }
        }
        return maxGain;
    }

    /**
     * 正确解答：加状态。 把每天的状态从 是否持有股票 变成
     *                  0. 没股票 且 没买过
     *                  1. 有股票 且 买过一次
     *                  2. 没股票 且 卖过一次
     *                  3. 有股票 且 买过两次
     *                  4. 没股票 且 卖过两次
     *  状态：f(i,j,k): 第i天   j:是否有股票(只能取0/1)   k:交易几次(只能取值0/1/2)  的最大收益
     *  没必要用一个三维数组来，可以直接  f(i,j): 第i天剩余的钱  j取值0-4, 分别代表上面四种状态
     *  初始值: f(i,0) = f(i-1,0)
     *         f(i,1) = MAX{f(i-1,1), f(i-1,0) - prices[i]} 没动 或 当天买入
     *         f(i,2) = MAX{f(i-1,2), f(i-1,1) + prices[i]} 没动 或 当天卖出
     *         f(i,3) = MAX{f(i-1,3), f(i-1,2) - prices[i]} 没动 或 当天买入
     *         f(i,4) = MAX{f(i-1,4), f(i-1,3) + prices[i]} 没动 或 当天卖出
     *  初始值: f(i,0) = 0,
     *         f(0,1) = -prices[0]
     *         f(0,2) = 0           即当天买入卖出
     *         f(0,3) = -prices[0]  即当天买入卖出再卖出
     *         f(0,4) = 0           即当天买入卖出两次
     */
    public int maxProfit_correct(int[] prices){
        int n = prices.length;
        if (n == 0 || n == 1){
            return 0;
        }
        int [][] dp = new int[n][5];
        //初始化
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
        }
        return dp[n-1][4];
    }
}
