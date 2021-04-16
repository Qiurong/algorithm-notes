package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 144. 二叉树的前序遍历
 * @author: Qr
 * @create: 2021-04-16 10:00
 **/
public class binary_tree_preorder_traversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursion(root,res);
        return res;
    }

    public void recursion(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        res.add(root.val);
        recursion(root.left,res);
        recursion(root.right,res);
    }


    public List<Integer> preorderTraversal_stack(TreeNode root){
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                res.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode father = stack.pop();
            curr = father.right;
        }
        return res;
    }
}
