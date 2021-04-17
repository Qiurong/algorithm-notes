package binary_search_tree;

/**
 * @description: 450. 删除二叉搜索树中的节点
 * @author: Qr
 * @create: 2021-04-17 16:46
 **/
public class delete_node_in_a_bst {
    //迭代法略显罗嗦，需要分太多情况进行讨论
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        //对于删除结点为root需要单独讨论, 因为没法获取prev结点
        if (root.val == key){
            if (root.left == null && root.right == null){
                return null;
            }else if (root.left == null){
                return root.right;
            }else if (root.right == null){
                return root.left;
            }else {
                //用successor来代替root
                TreeNode successor = root.right;
                TreeNode successorPrev = root;
                while (successor.left != null){
                    successorPrev = successor;
                    successor = successor.left;
                }
                root.val = successor.val;
                if (successorPrev == root){
                    successorPrev.right = successor.right;
                }else {
                    successorPrev.left = successor.right;
                }
            }
            return root;
        }

        TreeNode toDelete = root;
        TreeNode prev = root;           //toDelete的父结点
        //寻找删除结点
        while (toDelete != null && toDelete.val != key){
            prev = toDelete;
            if (toDelete.val > key){
                toDelete = toDelete.left;
            }else {
                toDelete = toDelete.right;
            }
        }
        //删除结点并调整
        if (toDelete != null){
            //被删除结点为父孩子左节点
            if (prev.val > toDelete.val){
                //分情况讨论，被删除结点有0,1,2个子结点
                if (toDelete.left == null && toDelete.right == null){
                    prev.left = null;
                }else if (toDelete.right == null){
                    prev.left = toDelete.left;
                }
                else if (toDelete.left == null){
                    prev.left = toDelete.right;
                }else {
                    //用toDelete的后继结点代替 toDelete
                    //把toDelete的值改为successor.val，然后删除successor
                    TreeNode successor = toDelete.right;
                    TreeNode successorPrev = toDelete;
                    while (successor.left != null){
                        successorPrev = successor;
                        successor = successor.left;
                    }
                    toDelete.val = successor.val;
                    //根据successor是不是toDelete的右孩子来分
                    if (successorPrev == toDelete){
                        //successor是右孩子, 赋值 successorPrev的右孩子
                        successorPrev.right = successor.right;
                    }else {
                        //否则赋值 successorPrev的左孩子
                        successorPrev.left = successor.right;
                    }
                }
            }
            else {
                if (toDelete.left == null && toDelete.right == null){
                    prev.right = null;
                }else if (toDelete.right == null){
                    prev.right = toDelete.left;
                }
                else if (toDelete.left == null){
                    prev.right = toDelete.right;
                }else {
                    TreeNode successor = toDelete.right;
                    TreeNode successorPrev = toDelete;
                    while (successor.left != null){
                        successorPrev = successor;
                        successor = successor.left;
                    }
                    toDelete.val = successor.val;
                    if (successorPrev == toDelete){
                        successorPrev.right = successor.right;
                    }else {
                        successorPrev.left = successor.right;
                    }
                }
            }
        }
        return root;
    }


    //用递归法来做
    public  TreeNode deleteNode_recursion(TreeNode root, int key){
        root = delete(root,key);
        return root;
    }

    public TreeNode delete(TreeNode root, int key){
        //key对应的结点不存在
        if (root == null){
             return root;
        }
        if (root.val > key){
            root.left = delete(root.left,key);
        }
        else if (root.val < key){
            root.right = delete(root.right,key);
        }
        else {
            if (root.left == null && root.right == null){
                root = null;
            }
            else if (root.right == null){
                root = root.left;
            }
            else if (root.left == null){
                root = root.right;
            }
            //令root.val = successor.val;
            //在递归删除successor
            else {
                TreeNode successor  =root.right;
                while (successor.left != null){
                    successor = successor.left;
                }
                root.val = successor.val;
                //到右子树中去删除successor
                root.right = delete(root.right,successor.val);
            }
        }
        return root;
    }

}
