package StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 542. 01 矩阵
 * @author: Qr
 * @create: 2021-02-24 15:01
 **/
public class matrix_01 {
    //思路：把矩阵看作是一个网格, 对每个元素进行广度遍历(因为是最近的0的距离)
    public static int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0){
            return new int[0][0];
        }
        int n = matrix[0].length;
        int[][] updatedMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0){
                    updatedMatrix[i][j] = 0;
                }else {
                    updatedMatrix[i][j] = distanceToZero(matrix, i, j);
                }

            }
        }
        return updatedMatrix;
    }

    //计算矩阵中每个元素到最近的0的距离
    public static int distanceToZero(int[][] matrix, int i, int j){
        int distance = 1;
        //两个队列记录(i,j)的领近结点
        Queue<Integer> xQueue = new LinkedList<>();
        Queue<Integer> yQueue = new LinkedList<>();
        //为了防止队列为空, 先判断当前节点
        if (isNearZero(matrix, i, j)){
            return distance;
        }
        //把当前节点的邻近结点入队列
        if (checkForRange(matrix, i, j - 1)) {
            xQueue.add(i);
            yQueue.add(j - 1);
        }
        if (checkForRange(matrix, i - 1, j)) {
            xQueue.add(i - 1);
            yQueue.add(j);
        }
        if (checkForRange(matrix, i + 1, j)) {
            xQueue.add(i + 1);
            yQueue.add(j);
        }
        if (checkForRange(matrix, i, j + 1)) {
            xQueue.add(i);
            yQueue.add(j + 1);
        }
        distance++;
        //进行广度遍历
        //待解决：这个算法有个问题：会多次重复入队同一个节点。
        //(i,j)周围没0,入队左边节点, 左边节点周围没0, 会再次入队(i,j)
        while (!xQueue.isEmpty()){
            //当前节点的邻近结点个数
            int nearNodeNum = xQueue.size();
            //一层一层的去判断
            for (int k = 0; k < nearNodeNum; k++) {
                int m = xQueue.remove();
                int n = yQueue.remove();
                if (isNearZero(matrix, m, n)) {
                    return distance;
                } else {
                    if (checkForRange(matrix, m, n - 1)) {
                        xQueue.add(m);
                        yQueue.add(n - 1);
                    }
                    if (checkForRange(matrix, m - 1, n)) {
                        xQueue.add(m - 1);
                        yQueue.add(n);
                    }
                    if (checkForRange(matrix, m + 1, n)) {
                        xQueue.add(m + 1);
                        yQueue.add(n);
                    }
                    if (checkForRange(matrix, m, n + 1)) {
                        xQueue.add(m);
                        yQueue.add(n + 1);
                    }
                }
            }
            distance++;
        }
        return distance;
    }

    //判断(i,j)临近结点是否有0
    public static boolean isNearZero(int[][] matrix, int i, int j){
        if (checkForRange(matrix, i-1, j) && matrix[i-1][j] == 0){
            return true;
        }
        if (checkForRange(matrix, i, j-1) && matrix[i][j-1] == 0){
            return true;
        }
        if (checkForRange(matrix, i+1, j) && matrix[i+1][j] == 0){
            return true;
        }
        if (checkForRange(matrix, i, j+1) && matrix[i][j+1] == 0){
            return true;
        }
        return false;
    }

    //检查(i,j)是否在有效值内
    public static boolean checkForRange(int[][] matrix, int i, int j){
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }
}
