package binary_search_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 98. 验证二叉搜索树
 * @author: Qr
 * @create: 2021-04-16 09:07
 **/


class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



public class validate_binary_search_tree {

    public boolean isValidBST(TreeNode root) {
        return recursion(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    /**
     * 自己想法是递归获取leftMax和rightMin,发现没办法实现
     * recursion(TreeNode, int lower, int upper)： lower<结点值<upper
     * 左 <根 <右   ----> root.left < root.val
     *                                          recursion(root.left, lower, root.val):  root的下界 < 左子树 < root.val
     *       recursion(root, lower, upper) --->
     *                                          recursion(root.right, root.val, lower): root.val< 右子树 < root的上界
     */
    public boolean recursion(TreeNode root, Long lower, Long upper){
        if (root == null){
            return true;
        }
        if (root.val >= upper || root.val <= lower){
            return false;
        }else {
            //左子树上界是根，右子树下界是根
            return recursion(root.left,lower, (long) root.val) && recursion(root.right, (long) root.val, upper);
        }
    }


    //二叉搜索树重要性质：中序遍历是一个严格升序数组
    public boolean isValidBST_InoderTraverse(TreeNode root){
        boolean isValid = true;
        if (root == null){
            return isValid;
        }
        TreeNode curr = root;
        Long prevVAL = Long.MIN_VALUE;       //中序遍历中curr的前一个结点
        Stack<TreeNode> stack = new Stack<>();
        //用迭代来中序遍历树，且额外保留一个变量prev来随时终止遍历
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode father = stack.pop();
            if (father.val <= prevVAL){
                isValid = false;
                break;
            }
            //更新preVal
            prevVAL = Long.valueOf(father.val);
            curr = father.right;
        }
        return isValid;
    }
}
