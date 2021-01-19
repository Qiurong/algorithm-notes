package binary_tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @description: 二叉树的自底上下的层序遍历（从叶子节点到根节点层序从左向右遍历）
 * @author: Qr
 * @create: 2021-01-19 09:31
 **/

//思路：先层序遍历，将每层作为一个元素存到List中，在List翻转(对于数组就是swap(i,n-i))
/** 总结：这题中首先是一个层序遍历(用栈来实现,栈的具体实现形式采用LinkedList)
 *       难点: 需要以每层元素为一个list,而不是所有元素为一个list(采用循环的方法来处理这个需求，如何设计只用一个循环来满足需求是难点)
 */


public class binary_tree_level_order_traversal_ii {
    public static List<List<Integer>> levelOrderBottom (TreeNode root) {
        //存储二叉树每层结点val的List 的List
        List<List<Integer>> levelValList = new ArrayList<List<Integer>>();
        //存储树上所有结点的栈
        //此处用linkedList好于arrayList, 因为后续大量在队列头部删除元素
        List<TreeNode> nodeList = new LinkedList<>();
        if (root != null) {
            nodeList.add(root);
        }

        while (!nodeList.isEmpty()) {
            //该变量用于记录当前层的元素个数
            int currentLevelSize = nodeList.size();
            //记录当前层的结点val集合
            ArrayList valList = new ArrayList();

            //用于把1.存储当前层的结点val
            //     2.下层结点放入List中
            //     3.移除当前层的元素
            for(int i = 0; i < currentLevelSize; i++){
                //1.存储当前层的结点val. get同remove的index始终为0,因为你在循环的过程中改变了List的size
                TreeNode currentNode = nodeList.get(0);
                valList.add(currentNode.val);
                //2.下层结点放入List中
                if (currentNode.left != null) {
                    nodeList.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeList.add(currentNode.right);
                }
                //3.移除当前层的元素: index始终为0，因为每次循环都删除了队首元素
                nodeList.remove(0);
            }
            levelValList.add(new ArrayList<>(valList));
        }

        //列表元素顺序置反
        int length = levelValList.size();
        int n = length >>> 1;
        for(int i = 0; i < n; i++){
            List<Integer> tempList= new ArrayList<>();
            tempList = levelValList.get(i);
            levelValList.set(i, levelValList.get(length-1-i));
            levelValList.set(length-1-i, tempList);
        }

        return levelValList;
    }
}
