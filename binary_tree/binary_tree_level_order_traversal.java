package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @description: 102. 二叉树的层序遍历
 * @author: Qr
 * @create: 2021-04-16 14:07
 **/
public class binary_tree_level_order_traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr = root;

        queue.offer(root);
        while (!queue.isEmpty()){
            int currLevelNum = queue.size();            //记录一下当前层的个数
            List<Integer> currLevel = new ArrayList<>();    //当前层的元素列表
            for (int i = 0; i < currLevelNum; i++) {
                curr = queue.poll();
                currLevel.add(curr.val);
                if (curr.left != null){
                    queue.offer(curr.left);
                }
                if (curr.right != null){
                    queue.offer(curr.right);
                }
            }
            res.add(currLevel);
        }
        return res;
    }

}
