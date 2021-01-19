package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @description:二叉树层序遍历
 * @author: Qr
 * @create: 2021-01-12 15:26
 **/



/** 广度优先遍历：队列实现
 *  伪代码:      BFS(TreeNode root){
 *                  Queue q;
 *                  //先把根节点加入到队列中
 *                  In_Queue(q, root);
 *                  while(q != null){
 *                      //取出队首元素
 *                      front = Out_Queue(q);
 *                      //把取出的队首元素的左右孩子入队
 *                      In_Queue(q, front.left);
 *                      In_Queue(q, front.right);
 *                  }
 *
 *
 *  }
 */
public class binaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root){

        //每一层内结点的List
        List<TreeNode> nodeList = new LinkedList<>();

        List<Integer> nodeValList = new ArrayList<>();
        List<List<Integer>> levelNodeValList = new ArrayList<>();

        //先把根节点加入到结点list中
        nodeList.add(root);

        while(! nodeList.isEmpty()){
            //当前层的结点个数
            int currentLevelSize = nodeList.size();

            //把下层元素加入到nodeList中
            for(int i = 0; i < currentLevelSize; i++){
                TreeNode currentNode = nodeList.get(i);
                nodeValList.add(currentNode.val);

                if(currentNode.left != null){
                    nodeList.add(currentNode.left);
                }
                if(currentNode.right != null){
                    nodeList.add(currentNode.right);
                }
            }
            //把当前层的结点val加入
            //错误写法: 在这里传参默认是引用传递, nodeValList置空之后levelNodeValList中的值也会置空
            //levelNodeValList.add(nodeValList);
            levelNodeValList.add(new ArrayList<Integer>(nodeValList));
            //清空缓存, 存储下一层结点val
            nodeValList.clear();

            //移除当层元素
            for (int i = 0; i < currentLevelSize; i++){
                //频繁在队列首部移除元素, 故用LinkedList较好
                nodeList.remove(0);
            }
        }
        return levelNodeValList;
    }
}
