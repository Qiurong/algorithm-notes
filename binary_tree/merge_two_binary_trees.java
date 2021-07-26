package binary_tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 617. 合并二叉树
 * @author: Qr
 * @create: 2021-07-22 17:27
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
 *
 **/
public class merge_two_binary_trees {
    //递归的去深度遍历, 遍历的同时进行处理值相加. 时间复杂度O(n), 空间复杂度算上栈的话O(n)
    public TreeNode mergeTrees_recursion(TreeNode root1, TreeNode root2) {
        /*if (root1 == null || root2 == null){
            return root1 == null ? root2 : root1;
        }*/
        return merge(root1,root2);
    }

    public TreeNode merge(TreeNode node1, TreeNode node2){
        /*if (node1 == null && node2 == null){
            return null;
        }*/
        if(node1 == null){
            return node2;
        }
        if (node2 == null){
            return node1;
        }
        TreeNode merged = new TreeNode();
        setVal(merged,node1,node2);
        merged.left = merge(node1.left, node2.left);
        merged.right = merge(node1.right, node2.right);
        return merged;
    }

    public void setVal(TreeNode node, TreeNode node1, TreeNode node2){
        if (node1 == null || node2 == null){
            node.val = node1 == null ? node2.val : node1.val;
        }else {
            node.val = node1.val + node2.val;
        }

    }


    //广度遍历
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val+ root2.val);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        //1.把根节点入队列
        queue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);
        //2.广度遍历且进行 合并树 的构造
        while (!queue1.isEmpty() || !queue2.isEmpty()){
            //2.1从队列取出根节点
            TreeNode currNode1 = queue1.poll();
            TreeNode currNode2 = queue2.poll();
            TreeNode currNode = queue.poll();
            //2.2 获取两棵树的左右子树
            TreeNode left1 = currNode1.left;
            TreeNode left2 = currNode2.left;
            TreeNode right1 = currNode1.right;
            TreeNode right2 = currNode2.right;

            //逻辑: 两个左子树都为null, 则合并后也为Null
            //      一个为Null,则合并后 直接=非空的那个, 且不需要入队列
            //     两个都不为null, 则进行val相加, 且各自入队列
            if (left1 !=null || left2!= null){
                if (left1 == null){
                    currNode.left = left2;
                }else if (left2 == null){
                    currNode.left = left1;
                }else {
                    currNode.left = new TreeNode(left1.val + left2.val);
                    queue.offer(currNode.left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                }
            }
            if (right1 !=null || right2!= null){
                if (right1 == null){
                    currNode.right = right2;
                }else if (right2 == null){
                    currNode.right = right1;
                }else {
                    currNode.right = new TreeNode(right1.val + right2.val);
                    queue.offer(currNode.right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                }
            }
        }
        return merged;
    }
}
