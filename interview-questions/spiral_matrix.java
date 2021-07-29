package interview_questions;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 54. 螺旋矩阵
 * @author: Qr
 * @create: 2021-07-27 17:02
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 **/
public class spiral_matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int iMin = 0;
        int iMax = m-1;
        int jMin = 0;
        int jMax = n-1;
        int i = 0;
        int j = 0;
        while (isValid(i,j,iMin,iMax,jMin,jMax)){
            //1.向右
            if (isValid(i,j,iMin,iMax,jMin,jMax)){
                //向右遍历同一行
                while (j <= jMax){
                    res.add(matrix[i][j]);
                    j++;
                }
                //循环结束后需要--
                j--;
                //更新i的值
                i++;
                iMin = i;
            }
            //2.向下
            if (isValid(i,j,iMin,iMax,jMin,jMax)){
                //向下遍历同一列
                while (i <= iMax){
                    res.add(matrix[i][j]);
                    i++;
                }
                i--;
                //更新j的值
                j--;
                jMax=j;
            }
            //3.向左
            if (isValid(i,j,iMin,iMax,jMin,jMax)){
                //向左遍历同一行
                while (j >= jMin){
                    res.add(matrix[i][j]);
                    j--;
                }
                j++;
                //更新i的值
                i--;
                iMax = i;
            }
            //4.向上
            if (isValid(i,j,iMin,iMax,jMin,jMax)){
                //向上遍历同一列
                while (i >= iMin){
                    res.add(matrix[i][j]);
                    i--;
                }
                i++;
                //更新j的值
                j++;
                jMin = j;
            }
        }
        return res;
    }

    private boolean isValid(int i, int j, int iMin, int iMax, int jMin, int jMax){
        return i >= iMin && i <= iMax && j >= jMin && j<=jMax;
    }
}
