# 数据结构之链表剖析

[toc]

## 概述

链表是一种线性数据结构，其中的每个元素实际上是一个单独的对象，而所有对象都通过每个元素中的引用字段链接在一起。

链表有两种类型：单链表和双链表
- 单链表

![5.单链表](https://img-blog.csdnimg.cn/20201027094457704.png)

- 双链表

![6.双链表](https://img-blog.csdnimg.cn/20201027094517127.png)

线性数据结构中动态数组、栈(数组实现)、队列(数组实现)，底层依托静态数组，靠resize()解决固定容量问题；链表是真正的动态数据结构；

## 原理
```java
//链表中数据存储节点(Node) 结构
//单链表
class Node{
    E e;
    Node next;
}

//双链表
class Node{
    E e;
    Node prev;
    Node next;
}
```

### 单链表

**实现方式一：使用head指向链表第一个节点；需要对第一个元素特殊处理；**

![链表添加节点](https://img-blog.csdnimg.cn/20201104163144849.png#pic_center)

**添加元素：**
- 定义：新节点为newNode，head指向链表第一个节点；
- 要点：找到待添加节点的前一个节点；
- 在头部添加元素：因为头部节点没有前一个节点，需要对头节点特殊处理，将新节点的下一个节点指向head指向的节点，head指向新节点；代码示例：(newNode.next = head; head = newNode;)

![7.单链表头部添加元素](https://img-blog.csdnimg.cn/20201027101743732.png)

- 在中间位置添加元素：从head节点开始，找到要添加的节点(newNode)的前一个节点(prevNode)，将添加的节点(newNode)的下一个节点指向前一个节点(prevNode)的下一个节点，将前一个节点(prevNode)的下一个节点指向添加的节点(newNode)；代码示例：(newNode.next = prevNode.next; prevNode.next = newNode); 

![8.单链表中间添加元素](https://img-blog.csdnimg.cn/20201027104640798.png)

- 在尾部添加元素：与在中间位置添加元素方式一致，从head节点开始，遍历到链表最后一个节点(lastNode)即为要添加的节点(newNode)的前一个节点，将最后一个节点的下一个结点指向新节点；代码示例：(lastNode.next = newNode)；

**删除元素：**
- 定义：head指向链表第一个节点；
- 要点：找到待删除节点的前一个节点；
- 在头部删除元素：因为头部节点没有前一个节点，需要对头节点特殊处理，如果待删除节点为head，将head指向原节点的下一个节点；代码示例：(head = head.next;)
- 在中间位置删除元素：找到待删除元素的前一个节点(prevNode),将其下一个节点指向待删除节点(curNode)的下一个节点;代码示例：(prevNode.next = curNode.next;curNode.next = null;)

![9.单链表中间删除元素](https://img-blog.csdnimg.cn/20201027105640646.png#pic_center)

- 在尾部删除元素：与在中间位置删除元素一致，找到待删除元素的前一个节点，从head节点开始，遍历到链表最一个节点的前一个节点(prevNode)，将其下一个节点指向null;代码示例：(prevNode.next = null);


**实现方式二：为链表设立虚拟头节点(dummyHead)，其指向的下一个节点为链表的第一个元素节点；不需要对第一个元素节点特殊处理；**

![10.单链表虚拟头节点](https://img-blog.csdnimg.cn/20201027111207427.png)

**添加元素：**
- 定义：新节点为newNode，虚拟头节点(dummyHead)为链表的第一个节点，dummyHead的下一个节点指向链表的第一个元素节点；
- 在头部添加元素：找到要添加节点位置的前一个节点即prevNode=dummyHead，将新节点的下一个节点指向prevNode的下一个节点，prevNode的下一个节点指向新节点；代码示例：(newNode.next = prevNode.next; prevNode.next = newNode;)
- 在中间位置添加元素和在尾部添加元素与在头部添加元素方式一致，从dummyHead出发找到要添加节点位置的前一个节点prevNode，将新节点的下一个节点指向prevNode的下一个节点，prevNode的下一个节点指向新节点；代码示例：(newNode.next = prevNode.next; prevNode.next = newNode;)

**删除元素：**
- 定义：虚拟头节点(dummyHead)为链表的第一个节点，dummyHead的下一个节点指向链表的第一个元素节点；
- 在头部删除元素：找到要删除节点位置的前一个节点即 prevNode=dummyHead，将 prevNode 的一下个节点指向原指向节点的下一个节点；代码示例：(prevNode.next = prevNode.next.next;)
- 在中间位置删除元素和在尾部删除元素与在头部删除元素方式一致，从dummyHead出发找到要删除节点位置的前一个节点 prevNode，将 prevNode 的一下个节点指向原指向节点的下一个节点；代码示例：(prevNode.next = prevNode.next.next;)

## 时间复杂度分析

**添加操作：**
- addFirst(E e) 在头部添加元素  O(1)
- addLast(E e)  在末尾添加元素  O(n)
  - 通过添加一个指针tail指向链表最后一个元素，addLast() 时间复杂度 O(1)
- add(index,e)  在指定索引添加元素  O(n/2) = O(n)

**删除操作：**
- removeFirst() 在头部删除元素  O(1)
- removeLast()  在末尾删除元素  O(n)
- remove(index)  删除指定位置元素  O(n/2) = O(n)

**修改操作：**
- set(index,e)  修改指定位置元素 O(n)

**查找操作：**
- getFirst()  查找指定位置元素  O(1)
- get(index)  查找指定位置元素  O(n)
- contains(e)  是否存在指定元素  O(n)

### 数组 VS 链表

**链表：**
- 优点：真正的动态，不需要处理固定容量的问题；
- 缺点：丧失了随机访问的能力，不适合用于索引有语意的情况；

**数组：**
- 数组最好用于索引有语意的情况；
- 最大的优点：支持快速查询；

## 更多链表问题

1. 双链表：每个节点指下一个节点及前一个节点；
2. 循环链表：尾节点的下一个节点指向头节点；

## 实践

1. 单链表实现 
    - 参考代码：[com.chen.data.struct.list.LinkedList.java](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/list)
2. 使用虚拟头节点单链表实现
    - 参考代码：[com.chen.data.struct.list.DummyHeadList.java](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/list)
3. 使用链表实现栈
    - 参考代码：[com.chen.data.struct.list.LinkedListStack.java](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/list)
4. 使用链表实现队列
    - 参考代码：[com.chen.data.struct.list.LinkedListQueue.java](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/list)
5. Leetcode 20. Valid Parentheses 验证链表及链表实现栈
6. Leetcode 102. Binary Tree Level Order Traversal 验证链表及链表实现队列

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)

## 参考

Leetcode

刘宇波《玩转数据结构》课程
