package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 95. 不同的二叉搜索树 II
 * @author: Qr
 * @create: 2021-04-13 16:16
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


public class unique_binary_search_trees_ii {
    public List<TreeNode> generateTrees(int n) {

        List<TreeNode> trees = recursion(1,n);
        return trees;
    }

    //枚举[start,end]种每个i作为根节点，[start,i-1]左子树列表， [i+1,end]右子树列表
    //从左右子树列表种选取任意左子树，任意右子树进行拼接，
    //返回的是以[start,end]构成的二叉搜索树列表
    public List<TreeNode> recursion(int start, int end){
        List<TreeNode> res = new ArrayList<>();
        if (start > end){
            res.add(null);
            return res;
        }else if(start == end){
            TreeNode root = new TreeNode(start);
            res.add(root);
            return res;
        }
        else {
            for (int i = start; i <= end; i++) {
                //左子树列表
                List<TreeNode> leftTrees = recursion(start,i-1);
                //右子树列表
                List<TreeNode> rightTrees = recursion(i+1,end);
                //从左右子树列表中选取任意一颗构造树
                for(TreeNode leftTree : leftTrees){
                    for (TreeNode rightTree: rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        res.add(root);
                    }
                }
            }

            return res;
        }
    }

}
