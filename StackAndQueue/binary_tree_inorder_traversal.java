package StackAndQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 94. 二叉树的中序遍历
 * @author: Qr
 * @create: 2021-02-16 15:29
 **/
//用栈来做
public class binary_tree_inorder_traversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //递归解法
    List<Integer> inorderList = new ArrayList<>();

    public List<Integer> inorderTraversalWithRecursion(TreeNode root) {
        if (root == null){
            return inorderList;
        }
        inorderTraversalWithRecursion(root.left);
        inorderList.add(root.val);
        inorderTraversalWithRecursion(root.right);
        return inorderList;
    }


}
