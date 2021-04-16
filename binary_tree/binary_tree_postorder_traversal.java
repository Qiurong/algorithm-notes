package binary_tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 145. 二叉树的后序遍历
 * @author: Qr
 * @create: 2021-04-16 11:00
 **/
public class binary_tree_postorder_traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recursion(root,res);
        return res;
    }

    public void recursion (TreeNode root,List<Integer> res){
        if (root == null){
            return;
        }
        recursion(root.left,res);
        recursion(root.right,res);
        res.add(root.val);
    }

    public List<Integer> postorderTraversal_stack(TreeNode root){
        List<Integer> res = new LinkedList<>();
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisit = root;

        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode father = stack.peek();
            //保证根节点在右子树之前弹出
            if (father.right == null || father.right == lastVisit){
                lastVisit = stack.pop();
                res.add(lastVisit.val);
            }else {
                //迭代右子树
                curr = father.right;
            }
        }
        return res;
    }
}
