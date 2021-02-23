package graph;

/**
 * @description: 200. 岛屿数量
 * @author: Qr
 * @create: 2021-02-23 09:22
 **/
public class number_of_islands {
    //把网格看作是一个无向图，每次遇到一个1则进行深度遍历，最后深度遍历的次数就是次数
    public int numIslands(char[][] grid) {
        int num = 0;
        //二维数组的长度。 行数: grid.length, 列数: grid[i].length
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1'){
                    traverseGrid(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }
    //对网格进行深度遍历
    public void traverseGrid(char[][] grid, int i, int j){
        if (!isValidCoordinate(grid,i,j)){
            return;
        }
        if (grid[i][j] == '0' || grid[i][j] == '2'){
            return;
        }
        //标记其为visited
        grid[i][j] = '2';
        traverseGrid(grid,i-1,j);
        traverseGrid(grid,i,j-1);
        traverseGrid(grid,i+1,j);
        traverseGrid(grid,i,j+1);
    }
    public boolean isValidCoordinate(char[][] grid, int i, int j){
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

    /*
    总结：岛屿和网格问题可以转换为图的遍历问题, 遍历中有个很重要的概念: visited。
     */
}
