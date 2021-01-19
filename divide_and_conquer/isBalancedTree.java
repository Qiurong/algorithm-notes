package divide_and_conquer;

/**
 * @description:给定一个二叉树，判断它是否是高度平衡的二叉树。
 * @author: Qr
 * @create: 2021-01-08 10:34
 **/

/**
 * 平衡二叉树：分治法。将判断一棵树的高度差转化为判断其  左右子树的高度差
 *          1. 结束条件:
 */
public class isBalancedTree {


    public static boolean isBalanced(TreeNode root){
        if (root == null){
            return true;
        }
        //计算当前结点是否为平衡二叉树
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        int heightDiff = Math.abs(leftHeight - rightHeight);
        boolean rootisBalanced = heightDiff > 1 ? false : true;

        boolean leftIsBalanced = isBalanced(root.left);
        boolean rightIsBalanced = isBalanced(root.right);

        return leftIsBalanced && rightIsBalanced && rootisBalanced;
    }

    /**
     * 这种方法更好的地方在于：把递归调用的过程放在了maxDepth函数中，节省了内存调用。
     *                    内存减少在：1.减少了maxDepth的调用
     *                              原来把 当前结点的平衡比较放在了主函数中，多调用了两次maxDepth的计算
     *                              2.maxDpeth自己更少递归调用了自己
     *                              在算到当前左右子树不平衡的情况下直接返回-1，不再需要继续递归计算当前左右子树的高度。
     *                    但同时带来了变量二义性的问题, maxDepth返回值=-1(结点不平衡),
     *                                                     返回值=高度(结点平衡)
     */

    //计算给定结点的高度：如果不平衡返回-1，如果平衡则返回高度
    public static int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int heightDiffer = Math.abs(leftDepth - rightDepth);
        //左子树不平衡，右子树不平衡，当前子树不平衡
        //剪枝操作，遇到这种情况不需要再递归往下调用直接
        if (leftDepth == -1 || rightDepth == -1 || heightDiffer > 1){
            return -1;
        }
        //走到这说明当前是一个平衡子树
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }


    //改进版解法
    //平衡二叉树： 左边平衡&&右边平衡&&左右两边高度<=1
    public static boolean betterIsBalanced(TreeNode root){
        if(root == null){
            return true;
        }

        int differ = maxDepth(root);
        if(differ == -1){
            return false;
        }else{
            return true;
        }
    }

}
