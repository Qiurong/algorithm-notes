package binary_tree;

/**
 * @description: 543. 二叉树的直径
 * @author: Qr
 * @create: 2021-07-26 15:02
 *
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 **/
public class diameter_of_binary_tree {
    //left, right分别表示当前结点左右子树的深度, 直径长度 = MAX{left + right}
    int max;

    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        if (root == null || (root.left == null && root.right == null)){
            return max;
        }
        maxDepth(root);
        return max;
    }

    //返回以node为root的子树的最大深度
    public int maxDepth(TreeNode node){
        if (node == null){
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        //更新最大路径长
        max = Math.max(left+right,max);
        return Math.max(left,right) + 1;
    }
}
