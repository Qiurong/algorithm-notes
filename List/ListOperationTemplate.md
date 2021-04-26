## 链表基础模板

### 遍历

```java
//遍历链表+获取链表长度
public void getLength(ListNode head){
    int length = 0;
    ListNode curr = head;
    while (curr != null){
        length++;
        curr = curr.next;
    }
}
```

### 反转链表

```java
//用两个指针prev和curr来进行逆转，同时在逆转的过程中需要保存暂时变量下一个结点next
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

### 快慢指针

#### 获取中点

```java
//快慢指针获取链表中点。奇数为中点，偶数为中点偏左。
public void getHalf(ListNode head){
    //快慢指针问题初始化为head
    ListNode fast = head;
    ListNode slow = head;
    while (fast !=null && fast.next!=null){
        fast = fast.next.next;
        //加一个判断条件可以设为偶数时, slow指向偏左
        //不加就指向偏右
        if (fast != null){
            slow = slow.next;
        }
    }
}
```

#### 环问题

```java
//判断有环。快慢指针,无环则fast最终为null/表尾。有环则最终fast与slow相遇
public boolean hasCycle(ListNode head){
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null){
        fast = fast.next.next;
        slow = slow.next;

        if (fast == slow){
            return true;
        }
    }
    return false;
}
```

#### 获取倒数第k个

```java
//获取倒数第k个
public void getTheLastK(ListNode head, int k){
    ListNode fast = head;
    ListNode slow = head;
    //1.让fast先走k步
    while (k > 0){
        fast = fast.next;
        k--;
    }
    //2.fast和slow同时走。fast走到Null(length-k)步, slow走到倒数第k个元素。
    while (fast != null){
        fast = fast.next;
        slow = slow.next;
    }
}
```

### 删除重复元素

#### 保留重复元素

基本思想：两个指针`prev`和`curr`。

- `prev`为上一个合法结点，`curr`为下一个合法节点。
- `prev`初始化为dummyNode，`curr`初始化为head。
- 遍历链表，对于重复结点，`curr`迭代到重复结点的最后一个。
- 完成`prev`到`curr`的链接，并继续往后迭代。

```java
public ListNode deleteDuplicates(ListNode head){
    if (head == null || head.next == null){
        return head;
    }
    ListNode dummyNode = new ListNode(0);
    dummyNode.next = head;
    //上一个合法节点
    ListNode prev = dummyNode;
    //下一个合法结点
    ListNode curr = head;

    //遍历链表
    while (curr != null) {
        int currVal = curr.val;
        //curr迭代到重复结点到最后一个
        while (curr.next != null && curr.next.val == currVal){
            curr = curr.next;
        }
        //prev与curr链接
        prev.next = curr;
        prev = prev.next;
        curr = curr.next;
    }
    return dummyNode.next;
}
```

#### 不保留重复元素

基本思想与保留重复元素一样，不一样的点在于：

- `curr`迭代的终点：在上题中`curr`迭代到重复结点的末端，这题需要**迭代出**重复结点列表。即1111222，`curr`经过一轮变为2。

- 链接的条件：

  - 在上题中，`prev`可以直接与迭代过的`curr`链接（非重复结点或重复节点的最后一个结点）。

  - 在此题，每次都需要判断，确定`curr`不是重复结点，才可以链接。

```java
public ListNode deleteDuplicates_ii(ListNode head) {
    ListNode dummyNode = new ListNode(0);
    dummyNode.next = head;
    //上一个合法节点
    ListNode prev = dummyNode;
    //下一个合法结点
    ListNode curr = head;

    while(curr != null){
        int currval = curr.val;
        //判断curr是否为重复结点
        if (curr.next != null && curr.next.val  == currval){
             //对于重复结点就一直跳到下一个非重复结点，然后再去重新迭代
            while (curr != null && curr.val == currval) {
                curr = curr.next;
            }
            //遍历到表尾
            if (curr == null){
                prev.next = curr;
            }
        }else{  //只有curr不是重复结点才能进行连接
            prev.next = curr;
            prev = curr;
            curr = curr.next;
        }
    }
    return dummyNode.next;
}
```

