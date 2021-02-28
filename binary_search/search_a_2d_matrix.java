package binary_search;

/**
 * @description: 74. 搜索二维矩阵
 * @author: Qr
 * @create: 2021-02-28 17:16
 **/
public class search_a_2d_matrix {
    //思路：只是从一维变成二维，仍然用模板二
    //对于行一维度
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int mid;
        int left = 0;
        int right = m;
        //先找到哪一行, 最终夹出的left值：target在第一列一维数组中的pos。
        while(left < right){
            mid = left + ((right - left) >> 1);
            if (matrix[mid][0] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        //在行数组中找到了[left][0] == target，否则则需要去(left - 1)行寻找
        if(left != m && matrix[left][0] == target){
            return true;
        }
        //最终如果夹出0, 则需要判断[0][0]与target的大小。
        //如果matrix[0][0] > target 则 不存在target
        if (left == 0 && matrix[0][0] > target){
            return false;
        }
        //如果matrix[0][0] <= target 则需要到第0行中去寻找, 则赋值left = 1，从而在下面的rowNum = 0.
        else if (left == 0 && matrix[0][0] <= target){
            left = 1;
        }

        //这里最后的left为一维数组的插入位置, 接下来应该去(left - 1)行寻找target
        int rowNum = left - 1;
        left = 0;
        right = n;
        while(left < right){
            mid = left + ((right - left) >> 1);
            if (matrix[rowNum][mid] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        //最后此时在这一行中寻找是否存在值跟target相同
        //循环结束后夹出的那一个元素判断其是否等于target
        if (left != n && matrix[rowNum][left] == target){
            return true;
        }
        return false;
    }

    //总结：一开始一直错误。因为少考虑了很多情况, 模板二最终夹出来的那个元素可能==target 或者 为target应该在数组中插入的位置。
}
