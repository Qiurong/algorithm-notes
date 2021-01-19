package divide_and_conquer;

/**
 * @description:
 * @author: Qr
 * @create: 2021-01-08 09:53
 **/

/**
 * 二叉树最大深度：MAXDepth(结点) = MAX( maxDepth(左子结点), maxDepth(右子结点) );
 *              递归结束：结点为叶子节点
 */
public class maxDepthOfBinaryTree {
    public static int maxDepth(TreeNode root){
        //1.本身为空则深度为0（递归终止条件）
        if (root == null){
            return 0;
        }

        //divide: 计算左右子树的最大深度
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        //merge:
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }


}
