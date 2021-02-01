package List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 138.复制带随机指针的链表
 * @author: Qr
 * @create: 2021-01-31 19:28
 **/
//思路: 深拷贝: 为已经存在的每个结点new一个相同val的结点再将next和random赋值上去，
//           next:循环遍历，存储每个node, 对应的完成next指针的赋值
//难点: 如何把random指针指向的元素给复制到新链表上,想到了要用HashMap来实现，但并没有想通该如何实现。
//解决方法：建立一个HashMap<Node,Node>, key也为Node,这样就能根据Node找到对应的Node
public class copy_list_with_random_pointer {
    //用于存储所有结点的map
    //key为链表中的结点oldNode,value为深拷贝后的结点newNode
    HashMap<Node,Node> nodesMap = new HashMap<>();

    public Node copyRandomList(Node head, int randomIndex){
        if(head == null){
            return null;
        }
        Node oldNode = head;
        //先把第一个结点放入
        Node newNode = new Node(oldNode.val);
        nodesMap.put(oldNode,newNode);
        //开始循环放入每个结点的next结点和random结点
        while (oldNode != null){
            newNode.next = cloneToMap(oldNode.next);
            newNode.random = cloneToMap(oldNode.random);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return nodesMap.get(head);
    }
    //用于把链表中一个结点深拷贝到map中,将深拷贝后的结点返回
    public Node cloneToMap(Node oldNode){
        if(oldNode != null){
            //map中没有则放入并返回newNode
            if (!nodesMap.containsKey(oldNode)){
                Node newNode = null;
                newNode = new Node(oldNode.val);
                nodesMap.put(oldNode,newNode);
                return newNode;
            }
            //map中存在则直接返回map中的结点
            else {
                return nodesMap.get(oldNode);
            }
        }
        return null;
    }

}
