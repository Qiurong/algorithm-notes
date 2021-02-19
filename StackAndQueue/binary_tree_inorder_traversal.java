package StackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

    //迭代解法:用栈
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> inorderRes = new LinkedList<>();
        if (root == null){
            return inorderRes;
        }
        Stack<TreeNode> stack = new Stack<>();
        /**
         *         stack.push(root);
         *         TreeNode curr = root;
         *         //此处为了满足循环条件, 让栈先入了root
         *         while(!stack.isEmpty()){
         *             while (curr.left != null){
         *                 stack.push(curr.left);
         *                 curr = curr.left;
         *             }
         *             curr = stack.pop();
         *             inorderRes.add(curr.val);
         *             if (curr.right != null){
         *                 stack.push(curr.right);
         *                 curr = curr.right;
         *             }
         *         }
         */
        //正确写法
        //1.用root!=null 来解决了刚开始栈为空的问题
        while(root != null || !stack.isEmpty()){
            //一直入栈到最左结点
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            //pop出栈顶元素
            root = stack.pop();
            inorderRes.add(root.val);
            //以右子结点再继续迭代
            root = root.right;
        }

        return inorderRes;
    }

}
