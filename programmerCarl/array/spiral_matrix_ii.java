package programmerCarl.array;

/**
 * @description: 59. 螺旋矩阵 II
 * @author: Qr
 * @create: 2021-10-15 11:22
 **/
public class spiral_matrix_ii {
    //数组问题，就容易出现在各种边界问题上, 一定要坚持一个原则, 就是左闭右闭.
    //用辅助数组visited来做
    public int[][] generateMatrix(int n) {
        int[][] nums = new int[n][n];
        //辅助数组: 用于标记原数组哪些已经填入数字
        //更新： 不需要额外的辅助数组, 直接判断当前位置是否 == 0就可以判断是否填入值
        //int[][] visited = new int[n][n];
        //坐标数组: 存放当前的数组元素下标
        int[] positions = new int[2];
        int num = 1;
        int end = n * n;
        //每一轮操作 从pos开始，然后置pos为下一操作的开始值
        while (num <= end){
            num = moveRight(nums, positions, num, end);
            num = moveDown(nums, positions, num, end);
            num = moveLeft(nums, positions, num, end);
            num = moveUp(nums, positions, num, end);
        }
        return nums;
    }


    /**
     *
     * @param nums 生成的数组
     * @param pos  当前操作的开始下标, 每次操作结束之后更新为下一步的开始下标
     * @param num  当前生成的数字
     * @return     当前操作之后 num的值
     */
    public int moveRight(int[][]nums, int[] pos, int num, int end){
        if (num > end){
            return num;
        }
        int x = pos[0];
        int startY = pos[1];
        //结束坐标
        int endY = startY;
        for (int j = startY; j < nums.length; j++) {
            //走到了已经有值的位置
            if (nums[x][j] != 0){
                //这里需要回退一步
                endY = j - 1;
                break;
            }
            nums[x][j] = num++;
            endY = j;
        }
        //更新坐标, 下移一格
        pos[0] = x + 1;
        pos[1] = endY;
        return num;
    }

    public int moveDown(int[][]nums, int[] pos, int num, int end){
        if (num > end){
            return num;
        }
        //开始坐标
        int startX = pos[0];
        int y = pos[1];
        //结束坐标
        int endX = startX;
        for (int i = startX; i < nums.length; i++) {
            //走到了已经有值的位置
            if (nums[i][y] != 0){
                endX = i - 1;
                break;
            }
            nums[i][y] = num++;
            endX = i;
        }
        //更新坐标 左移一格
        pos[0] = endX;
        pos[1] = y - 1;
        return num;
    }

    public int moveLeft(int[][]nums, int[] pos, int num, int end){
        if (num > end){
            return num;
        }
        //开始坐标
        int x = pos[0];
        int startY = pos[1];
        //结束坐标
        int endY = startY;
        for (int j = startY; j >= 0; j--) {
            //走到了已经有值的位置
            if (nums[x][j] != 0){
                endY = j + 1;
                break;
            }
            nums[x][j] = num++;
            endY = j;
        }
        //更新坐标 上移一格
        pos[1] = endY;
        pos[0] = x - 1;
        return num;
    }



    public int moveUp(int[][]nums, int[] pos, int num, int end){
        if (num > end){
            return num;
        }
        //开始坐标
        int startX = pos[0];
        int y = pos[1];
        //结束坐标
        int endX = startX;
        for (int i = startX; i >= 0; i--) {
            //走到了已经有值的位置
            if (nums[i][y] != 0){
                //回退一步
                endX = i + 1;
                break;
            }
            nums[i][y] = num++;
            endX = i;
        }
        //更新坐标 右移一格
        pos[0] = endX;
        pos[1] = y + 1;
        return num;
    }
}
