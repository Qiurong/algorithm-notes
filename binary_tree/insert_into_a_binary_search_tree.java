package binary_tree;

/**
 * @description:
 * @author: Qr
 * @create: 2021-01-21 09:43
 **/
public class insert_into_a_binary_search_tree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode insertNode = new TreeNode();
        TreeNode insertNodePar = new TreeNode();
        insertNode = root;
        if(root == null){
            root = new TreeNode(val);
            return root;
        }
        while(insertNode != null){
            if(insertNode.val < val){
                insertNodePar = insertNode;
                insertNode = insertNode.right;
            }else{
                insertNodePar = insertNode;
                insertNode = insertNode.left;
            }

        }
        insertNode = new TreeNode(val);
        if(insertNodePar.val > val){
            insertNodePar.left = insertNode;
        }else{
            insertNodePar.right = insertNode;
        }
        return root;
    }
}
