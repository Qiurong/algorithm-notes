package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * @author: Qr
 * @create: 2021-01-19 14:00
 **/
//思路: 可以直接层序遍历,间隔使用reverse函数
public class binary_tree_zigzag_level_order_traversal {

    public static void reverseList(List list){
        int length = list.size();
        int n = length >>> 1;
        for (int i = 0; i < n; i++) {
            Integer tempInteger = (Integer) list.get(i);
            list.set(i, list.get(length-1-i));
            list.set(length-1-i, tempInteger);
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        //类变量，用于标记是否reverse
        boolean reversed = true;

        List<List<Integer>> zzLevelValList = new ArrayList<>();

        List<TreeNode> nodeList = new LinkedList<>();
        if (root != null){
            nodeList.add(root);
        }
        while(!nodeList.isEmpty()){
            List<Integer> nodeValList = new ArrayList<>();
            int currentSize = nodeList.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode currentNode = nodeList.get(0);
                nodeValList.add(currentNode.val);
                if(currentNode.left != null){
                    nodeList.add(currentNode.left);
                }
                if(currentNode.right != null){
                    nodeList.add(currentNode.right);
                }
                nodeList.remove(0);
            }
            reversed = !reversed;
            if(reversed){
                reverseList(nodeValList);
                zzLevelValList.add(new ArrayList<>(nodeValList));
            }else{
                zzLevelValList.add(new ArrayList<>(nodeValList));
            }
        }
        return zzLevelValList;
    }

}
