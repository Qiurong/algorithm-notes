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
}
