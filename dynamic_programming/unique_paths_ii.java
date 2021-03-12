package dynamic_programming;

/**
 * @description: 63. 不同路径 II
 * @author: Qr
 * @create: 2021-03-12 09:05
 **/
public class unique_paths_ii {
    //想法：还是按照上题动态规划去做.创建一个新的pathNums数组,并且从结束点开始往前迭代.
    //区别:  1. f(i, j) = 0 (grid[i][j] == 1)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //多加一行一列用于解决越界问题
        int[][] pathNums = new int[m+1][n+1];
        for (int i = m-1; i >= 0; i--){
            for (int j = n-1; j>= 0; j--){
                pathNums[i][j] = pathNums[i+1][j] + pathNums[i][j+1];
                //初始设终点为1
                if(i == m-1 && j== n-1){
                    pathNums[i][j] = 1;
                }
                //障碍物为0
                if (obstacleGrid[i][j] == 1){
                    pathNums[i][j] = 0;
                }
            }
        }
        return pathNums[0][0];
    }

    //滚动数组思想把空间复杂度降低到O(n)
    public int uniquePathsWithObstacles_optimized(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] pathNums = new int[n+1];
        for (int i = m-1; i >= 0; i--){
            for (int j = n-1; j >= 0; j--) {
                pathNums[j] = pathNums[j] + pathNums[j+1];
                if (i == m-1 && j == n-1){
                    pathNums[j] = 1;
                }
                if (obstacleGrid[i][j] == 1){
                    pathNums[j] = 0;
                }
            }
        }
        return pathNums[0];
    }
}
