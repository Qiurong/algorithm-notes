package dynamic_programming;

import java.util.List;

/**
 * @description: 120. 三角形最小路径和
 * @author: Qr
 * @create: 2021-03-07 11:15
 **/
public class triangle {
    /* (i, j)的相邻节点：(i+1, j)和(i+1, j+1)
       (i, j)到底部的最小路径和：f(i, j) = min{f(i+1,j), f(i+1,j+1)} +[i,j]
     */


    public int minimumTotal_Recursion(List<List<Integer>> triangle) {
        int minTotal = recursionMethod(triangle,0,0);
        return minTotal;
    }

    //根据上面的递归公式可以用递归方法来解决
    public int recursionMethod(List<List<Integer>> triangle, int i, int j){
        //递归终止条件: 到达了第i+1行
        if (i == triangle.size()){
            return 0;
        }
        //f(i, j) = min{f(i+1,j), f(i+1,j+1)} +[i,j]
        return Math.min(recursionMethod(triangle,i+1, j),recursionMethod(triangle, i+1, j+1)) + triangle.get(i).get(j);
    }

    //上述方法多次重复计算某个f(i, j), 可以用数组来保存某一状态的值，称为记忆化搜索，这也就是动态规划的思想。
    public int minimumTotal_DPBasic(List<List<Integer>> triangle){
        int m = triangle.size();
        int n = 0;
        for (int i = 0; i < m; i++) {
            n = n > triangle.get(i).size() ? n : triangle.get(i).size();
        }
        int[][] cost = new int[m][n];
        int min = DPBasicMethod(cost, triangle,0, 0);
        return min;
    }

    public int DPBasicMethod(int[][] cost,List<List<Integer>> triangle,int i, int j){
        //最后一行直接返回自己的值
        if (i == triangle.size() - 1){
            int toBottomCost = 0;
            if (triangle.get(i).get(j) != null){
                toBottomCost = triangle.get(i).get(j);
            }
            return toBottomCost;
        }
        //f(i, j) = min{f(i+1,j), f(i+1,j+1)} +[i,j]
        //相比上种方法, 用数组[i][j] 来存储了 f(i, j)
        if (cost[i+1][j] == 0){
            cost[i+1][j] = DPBasicMethod(cost,triangle,i+1,j);
        }
        if (cost[i+1][j+1] == 0){
            cost[i+1][j+1] = DPBasicMethod(cost,triangle,i+1,j+1);
        }
        cost[i][j] = Math.min(cost[i+1][j],cost[i+1][j+1]) + triangle.get(i).get(j);
        return cost[i][j];
    }
}
