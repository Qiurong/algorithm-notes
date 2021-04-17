package binary_search_tree;

/**
 * @description: 701. 二叉搜索树中的插入操作
 * @author: Qr
 * @create: 2021-04-17 16:23
 **/
public class insert_into_a_binary_search_tree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        TreeNode prev = root;   //中序遍历中插入节点的前一个结点
        TreeNode curr = root;   //最后插入的结点位置
        //寻找插入位置
        while (curr != null){
            prev = curr;
            if (curr.val > val){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        //插入结点
        if (prev.val > val){
            prev.left = new TreeNode(val);
        }else {
            prev.right = new TreeNode(val);
        }
        return root;
    }
}
