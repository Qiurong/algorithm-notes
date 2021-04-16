package binary_tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 94. 二叉树的中序遍历
 * @author: Qr
 * @create: 2021-04-16 10:50
 **/
public class binary_tree_inorder_traversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursion(root,res);
        return res;
    }
    public void recursion(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        recursion(root.left,res);
        res.add(root.val);
        recursion(root.right,res);
    }

    public List<Integer>  inorderTraversal_Stack(TreeNode root){
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode father = stack.pop();
            res.add(father.val);
            curr = father.right;
        }
        return res;
    }
}
