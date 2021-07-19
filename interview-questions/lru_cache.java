package interview_questions;

import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 146. LRU 缓存机制
 * @author: Qr
 * @create: 2021-04-02 17:03
 **/
public class lru_cache {

    //双向链表：存储键值对, 越靠近头部表示最近被使用过, 越靠近尾部就被淘汰。
    //        用双向链表是因为删除和移动操作时需要获取当前结点得上一个和下一个结点，双向链表可以O(1)完成这个操作。
    //        每次get和put操作, 都要把对应操作的元素移动至头部.
    class DoubleLinkedNode{
        int key;
        int val;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
        DoubleLinkedNode(int key , int val){
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }

        public DoubleLinkedNode getPrev() {
            return prev;
        }

        public void setPrev(DoubleLinkedNode prev) {
            this.prev = prev;
        }

        public DoubleLinkedNode getNext() {
            return next;
        }

        public void setNext(DoubleLinkedNode next) {
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }

    private DoubleLinkedNode dummyHead;

    private DoubleLinkedNode dummyTail;
    //map 用于映射 key 到 存有键值对的node. O(1)时间找到存有键值对的结点
    private Map<Integer, DoubleLinkedNode> map;

    private int capacity;

    private int size;

    public lru_cache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        //双向链表增加dummyHead和dummyTail
        dummyHead = new DoubleLinkedNode(-1,-1);
        dummyTail = new DoubleLinkedNode(-1,-1);
        dummyHead.setPrev(dummyTail);
        dummyHead.setNext(dummyTail);
        dummyTail.setPrev(dummyHead);
        dummyTail.setNext(dummyHead);
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        //1.获取双向链表结点
        DoubleLinkedNode kvNode = map.get(key);
        //2.把该结点移动到链表头部
        moveToHead(kvNode);
        //3.返回val
        return kvNode.getVal();
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            DoubleLinkedNode node = map.get(key);
            node.setVal(value);
            moveToHead(node);
        }else {
            //创造新结点
            DoubleLinkedNode node = new DoubleLinkedNode(key,value);
            //新节点放入map和链表中
            map.put(key,node);
            addToHead(node);
            size++;
            //根据要求进行删除表尾元素
            if (size > capacity) {
                DoubleLinkedNode tail = deleteTail();
                map.remove(tail.getKey());
                size--;
            }
        }
    }

    //把链表中存在的某一结点移动到双向链表的头部
    public void moveToHead(DoubleLinkedNode node){
        //1.在链表中删除该结点
        delNode(node);
        //2.把该结点插入到头部
        addToHead(node);
    }

    //把一个链表中不存在的元素添加到表头
    public void addToHead(DoubleLinkedNode node){
        dummyHead.next.prev = node;
        node.next = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
    }

    //删除链表中存在的某个元素
    public DoubleLinkedNode delNode(DoubleLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    //删除链表表尾元素
    public DoubleLinkedNode deleteTail(){
        return delNode(dummyTail.prev);
    }
}
