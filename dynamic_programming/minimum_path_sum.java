package dynamic_programming;


/**
 * @description: 64. 最小路径和
 * @author: Qr
 * @create: 2021-03-10 09:14
 **/
public class minimum_path_sum {
    //on-place自顶向下的动态规划. 时间复杂度: O(n^2). 空间复杂度: O(1)
    //f(i,j)/gird[i,j]：从[0,0]到[i,j]的最小路径和
    //最后返回grid[rows-1][columns-1]
    //f(i, j) = min{f(i-1,j), f(i, j-1)} + a[i,j]
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        //预处理第一行和第一列
        for (int j = 1; j < columns; j++){
            grid[0][j] = grid[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            grid[i][0] = grid[i-1][0] + grid[i][0];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
            }
        }
        return grid[rows-1][columns-1];
    }
}
