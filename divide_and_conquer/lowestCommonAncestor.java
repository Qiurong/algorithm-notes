package divide_and_conquer;

/**
 * @description:
 * @author: Qr
 * @create: 2021-01-12 10:30
 **/

/**
 * 这题肯定是希望能够自底向上查, 用回溯就能完成这一目标。
 * 使用后序遍历(LRN)回溯的过程也就实现了自底向上查找
 *      postOrder(TreeNode root){
 *              postOrder(root.left);
 *              postOrder(root.right);
 *              visit(root);
 *      }
 * 在回溯的过程中如果发现 一个结点 的左右子树各包含一个p/q, 那么该结点就是最近公共祖先(因为是自底向上回溯, 出现的第一个符合条件的结点就是最近的)
 *
 * 递归的终止条件为: root == p/q,则返回p/q
 *                     == null, 则返回Null
 *               left 与 right 均不为null,则说明root为最近公共祖结点。
 *
 * 其实本质上: 不断把p/q往上回溯 直到第一个left与right均不为null的结点则为最近祖结点。
 *
 * 这道题用的是后序遍历来实现从下往上查找, 最近公共祖父结点被分为  到左边递归  和  到右边递归。
 *                                  conquer: 对左右结点的处理逻辑为非空则说明当前子树包含p/q, 均非空 则返回root
 *                                                                                   一个非空 则返回非空的子树(非空的子树包含p/q).
 *
 */
public class lowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //递归到最底层：则返回null或 非null的p/q
            if( root == null){
                return null;
            }
            if( root == p || root == q){
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p ,q);
            //左右结点均不为空则说明当前结点root为最近公共祖结点, 把root往上回溯
            if(left != null && right != null){
                return root;
            }

            //左/右不为空 对应左子树/右子树包含 p/q, 则把当前子树往上回溯
            if(left != null){
                return left;
            }
            if(right != null){
                return right;
            }
            return null;
    }
}
