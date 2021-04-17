package binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.WeakHashMap;

/**
 * @description: 二叉树的遍历模板
 * @author: Qr
 * @create: 2021-04-16 09:20
 **/
public class traverseTemplate {
    //递归方式实现
    public void traverse_recursion_preorder(TreeNode root){
        visit(root);
        traverse_recursion_preorder(root.left);
        traverse_recursion_preorder(root.right);
    }

    public void traverse_recursion_inorder(TreeNode root){
        traverse_recursion_inorder(root.left);
        visit(root);
        traverse_recursion_inorder(root.right);
    }

    public void traverse_recursion_postorder(TreeNode root){
        traverse_recursion_postorder(root.left);
        traverse_recursion_postorder(root.right);
        visit(root);
    }


    //非递归式实现
    //非递归遇到根节点需要回溯上一个结点，栈的后进先出正好满足这一特性
    public void traverse_preorder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            //根节点-最左子结点压入栈
            while (curr != null){
                //前序遍历，所以先visit
                visit(curr);
                stack.push(curr);
                curr = curr.left;
            }
            //pop
            TreeNode father = stack.pop();
            //遍历右子树
            curr = father.right;
        }
    }

    public void traverse_inorder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode father = stack.pop();
            //中序遍历先pop在visit
            visit(father);
            curr = father.right;
        }
    }

    //核心：根节点必须在右节点弹出之后在弹出
    public void traverse_postorder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        //用lastVisit标记右子结点是否已经弹出
        TreeNode lastVisit = root;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            //这里先看看, 不弹出
            TreeNode father = stack.peek();
            //右子树为空/已访问才pop
            if (father.right == null || father.right == lastVisit){
                lastVisit = stack.pop();
                visit(lastVisit);
            }
            else {
                //迭代右子树
                curr = father.right;
            }
        }
    }

    //层序遍历要用要队列
    public void traverse_level_order(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr = root;
        queue.offer(root);
        //queue提前入了根节点，所以这里只要判断队列非空即可
        while (!queue.isEmpty()){
            curr = queue.poll();
            //queue接口对于null会抛异常
            if (curr.left != null){
                queue.offer(curr.left);
            }
            if (curr.right != null){
                queue.offer(curr.right);
            }
            visit(curr);
        }
    }


    public void visit (TreeNode root){
        System.out.println(root.val);
    }
}


