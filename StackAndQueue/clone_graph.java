package StackAndQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 133.克隆图
 * @author: Qr
 * @create: 2021-02-21 10:30
 **/
public class clone_graph {
    static class Node {

        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public static Node cloneGraph(Node node) {
        if (node == null){
            return node;
        }
        //队列存储原节点，用于广度遍历整个图
        Queue<Node> oldNodes = new LinkedList<>();
        oldNodes.offer(node);
        //用HashMap来存储所有被访问的结点和其对应的克隆结点
        //Key: 原来图中的node
        //Value: 这个node对应的克隆后的结点
        HashMap<Node,Node> visited = new HashMap<>();
        //广度优先遍历图
        while(!oldNodes.isEmpty()){
            //获取队列当前结点
            Node curr = oldNodes.remove();
            Node clonedCurrNode = null;
            if(visited.containsKey(curr)){
                clonedCurrNode = visited.get(curr);
            }else {
                clonedCurrNode = new Node(curr.val);
                visited.put(curr, clonedCurrNode);
            }
            //克隆当前结点的邻居节点
            List<Node> clonedCurrNeighbors = new ArrayList<>();
            List<Node> currNeighbors = curr.neighbors;
            for (Node neighbour: currNeighbors) {
                //如果
                if (visited.containsKey(neighbour)){
                    clonedCurrNeighbors.add(visited.get(neighbour));
                }else {
                    //1.添加当前节点到原节点队列中
                    oldNodes.offer(neighbour);
                    //2.添加当前结点到visited中
                    visited.put(neighbour, new Node(neighbour.val));
                    //3.添加克隆后的结点到邻居数组中
                    clonedCurrNeighbors.add(visited.get(neighbour));
                }
            }
            clonedCurrNode.neighbors = clonedCurrNeighbors;
        }
        return visited.get(node);
    }
    /*总结：
    1. 广度遍历用队列来做
    2. 这题想到了Hash这种特殊的思想(根据之前链表复制的类似做法), 但没想到用HashMap来建立原结点和克隆结点的映射关系。
    根据HashMap key不可重复可以解决队列中不添加重复原节点的问题，同时可以根据映射关系不去new同一原结点的多个克隆结点而是直接HashMap.get(原结点)
     */

}
