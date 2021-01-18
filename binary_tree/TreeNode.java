package binary_tree;

/**
 * @description:
 * @author: Qr
 * @create: 2021-01-13 15:17
 **/
public class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(7,null,null);
        TreeNode t4 = new TreeNode(15,null,null);
        TreeNode t3 = new TreeNode(20,t4,t5);
        TreeNode t2 = new TreeNode(9,null,null);
        TreeNode t1 = new TreeNode(3,t2,t3);
    }
}
