package binary_tree;

/**
 * @description: 226. 翻转二叉树
 * @author: Qr
 * @create: 2021-07-21 16:18
 **/
public class invert_Tree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        recusion(root);
        return root;
    }
    public void recusion(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null){
            recusion(root.left);
        }
        if (root.right != null){
            recusion(root.right);
        }
    }
}
