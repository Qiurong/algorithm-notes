package programmerCarl.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 54. 螺旋矩阵
 * @author: Qr
 * @create: 2021-10-17 11:12
 **/
public class spiral_matrix {
    //按照从外到内每一层来进行遍历，遍历的时候采取左闭右开的原则
    //采取左闭右开的原则可以去固定遍历 每一层时的 步长
    //同时需要额外注意m为奇数的情况下最后还剩一层
    //wrong answer
    public List<Integer> spiralOrder_wrongAnswer(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m  = matrix.length;
        int n = matrix[0].length;
        //层数
        int loop = m/2;
        int currX = 0;
        int currY = 0;
        //行和列的步长
        int rowLength = m - 1;
        int columnLength = n - 1;
        //奇数行的情况下, 最后一步向右走的步数
        int finalLength = n - 2 * loop;
        while (loop > 0){
            //向右走
            for (int j = currY; j < currY + columnLength; j++) {
                res.add(matrix[currX][j]);
            }
            currY += columnLength;
            //向下走
            for (int i = currX; i < currX + rowLength; i++) {
                res.add(matrix[i][currY]);
            }
            currX += rowLength;
            //在这里需要多加一层判断 因为非n*n矩阵, 存在只有半层的情况 即一列，此时只需要向右和向下走
            //但我记录步长的方法只能增加变量 实时维护还未遍历的矩阵大小，过于冗余，所以采用官方写法来做
            if (false){

            }

            //向左走
            for (int j = currY; j > currY - columnLength ; j--) {
                res.add(matrix[currX][j]);
            }
            currY -= columnLength;
            //向上走
            for (int i = currX; i > currX - rowLength ; i--) {
                res.add(matrix[i][currY]);
            }
            currX -= rowLength;
            //更新步长
            rowLength -= 2;
            columnLength -= 2;
            //更新坐标, 进入下一层
            currY ++;
            currX ++;
            loop--;
        }

        //奇数行的情况下需要特殊处理最中心那一行
        if (m % 2 != 0){
            //向右走
            for (int j = currY; j < currY + finalLength; j++) {
                res.add(matrix[currX][j]);
            }
        }
        return res;
    }


    //按层来遍历，用四个指针来标记层的边界
    //按照左闭右开的原则, 对于一行和一列的这种特殊组合比较难处理
    //遍历时候按照左闭右闭原则来
    //对于左闭右开还是左闭右闭，需要根据实际编码情况来做
    //这里去写循环的时候，可以记录开始点 和 步长(第1种方法)，也可以记录 开始点和 结束点(第二种方法), 编码来说明显第二种更优雅
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        //外层循环：
        while (left <= right && top <= bottom){
            //向右
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            //向下
            for (int i = top + 1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            //一行或者一列的矩阵 只需要遍历两个操作就可以遍历完
            if (left < right && top < bottom){
                //向左
                for (int j = right - 1; j >= left ; j--) {
                    res.add(matrix[bottom][j]);
                }
                //向上
                for (int i = bottom - 1; i >= top + 1; i--) {
                    res.add(matrix[i][left]);
                }
            }
            //进入下层
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
}
