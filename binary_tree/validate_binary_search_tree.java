package binary_tree;

/**
 * @description: 给定一个二叉树，判断其是否是一个有效的二叉搜索树（左<根<右）
 * @author: Qr
 * @create: 2021-01-19 16:27
 **/
//思路: 中序遍历: LNR。用一个类变量存储上一个节点值, 与当前节点比较，如果大于则中断遍历

import java.util.Stack;

/** 递归法: inOrder(root){
 *         inOder(root.left);
 *         visit(root);
 *         inOder(root.right)
 *         }
 *
 *  非递归法(用栈),遍历到树的底层左孩子时需要回溯到最近的根节点,栈的后进先出特点完美符合这个需求，另用数组保存遍历结果。
 *  inOrder(root){
 *      stack, array;
 *      while(root != null || stack!=null ){
 *          //一直向左
 *          while(root != null){
 *              stack.push(root);
 *              root = root.left;
 *          }
 *          //弹出
 *          if(stack != null){
 *             currentNode = stack.pop();
 *             array.add(currentNode);
 *             root = currentNode.right;
 *          }
 *
 *      }
 *
 *  }
 */

public class validate_binary_search_tree {
    public static boolean isValidBST(TreeNode root){
        boolean isValid = true;
        //用于存储中序遍历中上一个元素的val
        //用0作为初始值不行，用Integer.MIN_VALUE也不行（官方测试用例用[-2147483648]来恶心人,继续往下用Long.MIN_VALUE）
        long lastNodeVal = Long.MIN_VALUE;
        Stack nodeStack = new Stack();
        while(root!=null || !nodeStack.isEmpty()){
            //一直把左孩子压栈
            while(root != null){
                nodeStack.push(root);
                root = root.left;
            }
            //pop操作
            if(nodeStack != null){
                TreeNode currentNode = (TreeNode) nodeStack.pop();
                if(lastNodeVal >= currentNode.val){
                    isValid = false;
                    break;
                }
                lastNodeVal = currentNode.val;
                root = currentNode.right;
            }
        }
        return isValid;
    }
}
