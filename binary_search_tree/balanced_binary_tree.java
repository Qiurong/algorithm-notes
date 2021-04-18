package binary_search_tree;

/**
 * @description: 110. 平衡二叉树
 * @author: Qr
 * @create: 2021-04-18 10:22
 **/
public class balanced_binary_tree {
    //知道要用递归，但没想到用了两个递归函数，一开始一直在纠结递归函数的返回值是int还是Boolean，但最后才知道需要两个递归函数
    //自顶向下, 时间复杂度O(n^2). 在过程中多次重复计算高度
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        if (Math.abs(depth(root.left) - depth(root.right)) <= 1){
            return isBalanced(root.left) && isBalanced(root.right);
        }else {
            return false;
        }
    }

    public int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(depth(root.left),depth(root.right)) + 1;
    }

    //自底向上
    public boolean isBalanced_bottomToUp(TreeNode root){
        return depth_bottomToUp(root) != -1;
    }

    //返回-1: 不平衡 / root的高度
    public int depth_bottomToUp(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftDepth = depth_bottomToUp(root.left);
        int rightDepth = depth_bottomToUp(root.right);
        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1){
            return -1;
        }else {
            return Math.max(leftDepth,rightDepth) + 1;
        }
    }

}
