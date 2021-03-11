package dynamic_programming;

/**
 * @description: 62. 不同路径
 * @author: Qr
 * @create: 2021-03-11 09:16
 **/
public class unique_paths {
    //动态规划来做, 从终点开始往前遍历.f(i,j): 从[i,j]走到右下脚的路线数量
    //f(i, j) = f(i+1, j) + f(i, j+1).
    //初始条件: 最后一行和最后一列均为1
    public int uniquePaths(int m, int n) {
        int[][] pathNums = new int[m][n];
        //初始化最后一行和最后一列初始值均为1
        for (int i = 0; i < m; i++) {
            pathNums[i][n-1] = 1;
        }
        for (int j = 0; j < n; j++) {
            pathNums[m-1][j] = 1;
        }
        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                pathNums[i][j] = pathNums[i+1][j] + pathNums[i][j+1];
            }
        }
        return pathNums[0][0];
    }
}
