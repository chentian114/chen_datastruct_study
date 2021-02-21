# 数据结构之栈和队列剖析

[toc]

## 概述

在 数组 中，可以通过索引访问 随机 元素。 但是，某些情况下，可能需要限制处理的顺序。

**栈**：是一个 **后入先出（LIFO）** 数据结构。通常，插入操作在栈中被称作入栈 push ，总是在栈的末尾添加一个新元素。删除操作被称作退栈 pop ，将始终删除栈末尾的一个元素。

![2.栈](https://img-blog.csdnimg.cn/20201021165401480.png)

**队列**：是一个 **先入先出（FIFO）** 的数据结构。插入操作也称作入队（enqueue），新元素始终被添加在队列的末尾。 删除操作也被称为出队（dequeue)，只能移除队列头部的第一个元素。

![3.队列](https://img-blog.csdnimg.cn/20201021170141702.png)

## 栈

栈是一种线性结构，相比数组，栈对应的操作是数组的子集，只能从一端添加元素，也只能从一端取出元素，这一端称为栈顶；

栈是一种后进先出的数据结构 LAST IN FIRST OUT(LIFO)。

在计算机的世界里，栈拥有着不可思议的作用，栈的应用：
- 无处不在的Undo操作（撤销）
- 程序调用使用的系统栈
- 括号匹配-编译器

### 原理

只允许在栈顶进行操作
- 可使用多种底层数据结构实现，
  - 如使用数组实现栈，将数组的最后一个元素作为栈顶；栈的动态扩缩容可使用动态数组实现栈；
  - 也可使用链表实现栈，将链表头作为栈顶；
- 添加元素时，将元素放置在栈顶，栈的元素数量+1；
- 删除元素时，将栈顶的元素取出，栈的元素数量-1；

Stack<E>
- void push(E)  入栈
- E pop()  出栈
- E peek()  查看栈顶元素
- int getSize()  获取栈元素数量
- boolean isEmpty()  判断是否为空

**参考代码：** [com.chen.data.struct.stack.ArrayStack](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

### 时间复杂度分析

ArrayStack<E>
- void push(E)  入栈   均摊复杂度 O(1)
- E pop()  出栈   均摊复杂度 O(1)
- E peek()  查看栈顶元素   O(1)
- int getSize()  获取栈元素数量  O(1)
- boolean isEmpty()  是否为空   O(1)

## 队列

队列是一种线性结构，相比数组，队列对应的操作是数组的子集，只能从一端（队尾）添加元素，只能从另一端（队首）取出元素；

队列是一种先进先出的数据结构，First In First Out(FIFO)。

### 原理

Queue<E> 队列
- void enqueue(E) 往队尾添加元素
- E dequeue()    取出队首元素
- E getFront()   获取队首元素
- int getSize()  数组队列元素数量
- boolean isEmpty()  判断是否为空

**实现方式1.数组队列：**
- 入队操作，不断往数组尾部添加元素
- 出队操作，将数组array[0]位置元素取出，将剩余元素全部往前移动一位；array[i] = array[i+1];

**参考代码：** [com.chen.data.struct.queue.ArrayQueue](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

**实现方式2.固定容量循环队列：**
- 定义：
  - 存放元素数组为：data,数组长度为：data.length
  - 声明3个变量：front指向要出队位置的元素，tail-指向要添加的位置；size-数组中存放元素数量；
- 结论：
  - 当size == 0时，队列为空；
  - 当size == data.length，队列已满；
- 队列初始化: front = tail = 0; size= 0; 队列为空；
- 入队：
  - 如果size < data.length（否则返回队列已满），当需要入队时，将元素放到tail位置，tail向后移一位；
  - 如果当tail已经在数组最后一个位置时，tail重置到0位置；tail = tail == data.length-1 ? 0 : tail + 1;
- 出队：
  - 如果size != 0（否则返回队列为空），当需要出队时，将front位置的元素出队，front向后移一位；
  - 如果当front已经在数组最后一个位置时，front重置到0位置；front = front == data.length-1 ? 0 : front + 1;

**参考代码：** [com.chen.data.struct.queue.LoopQueue](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

**实现方式3.动态容量循环队列：**

![4.队列为空和为满](https://img-blog.csdnimg.cn/20201021174724353.png)

- 定义
  - 存放元素数组为data，数组长度为 data.length，
  - 队列容量capacity=data.length-1（浪费1个空间，用于区分队列为空和队列已满两种情况）,队列中元素数量size；
  - 声明两个变量：front,tail分别指向数组中队首和队尾的位置；
  - 当front == tail 时，队列为空；
  - 当(tail + 1)%data.length == front时，队列为满；（浪费1个空间，用于区分队列为空和队列已满两种情况；如果不浪费一个空间的情况下，队列为空和为满的情况下front == tail）
- 数组初始设置front=tail=0位置;
- 入队操作，
  - 检查队列是否已满，是则对数组进行扩容为原来的2位;resize(2*capacity);
  - 将元素放到tail指向的位置，tail = (tail + 1) % data.length;
- 出队操作，
  - 将front指向的元素从数组中取出，front = (front + 1) % data.length;
  - 检查数组中元素数量是否为数组容量的1/4且数组容量的除2不等0，对数组进行缩容； if(size == capacity/4 && capacity/2 != 0) resize(capacity/2);
- resize数组扩缩容操作，
  - 将原数组中元素从 front 开始，i=0,取(front+i)%data.length位置元素，i加1，直到取出size个元素，将旧数组中元素全部放入新数组中；
  - for(int i = 0; i < size; i++)newData[i] = data[(i + front) % data.length];

**参考代码：** [com.chen.data.struct.queue.DynamicLoopQueue](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

### 时间复杂度分析

ArrayQueue<E>  数组队列
- void enqueue(E) 往队尾添加元素  O(1) 均摊
  - resize()通过均摊复杂度分析为 O(1) 
- E dequeue()    取出队首元素   O(n)
- E getFront()   获取队首元素   O(1)
- int getSize()  数组队列元素数量  O(1)
- boolean isEmpty()  判断是否为空  O(1)

LoopQueue<E> 固定容量循环队列
- void enqueue(E) 往队尾添加元素  O(1)
- E dequeue()    取出队首元素   O(1)
- E getFront()   获取队首元素   O(1)
- int getSize()  数组队列元素数量  O(1)
- boolean isEmpty()  判断是否为空  O(1)

LoopQueue<E> 动态容量循环队列
- void enqueue(E) 往队尾添加元素  O(1) 均摊
  - resize()通过均摊复杂度分析为 O(1)
- E dequeue()    取出队首元素   O(1) 均摊
  - resize()通过均摊复杂度分析为 O(1)
- E getFront()   获取队首元素   O(1)
- int getSize()  数组队列元素数量  O(1)
- boolean isEmpty()  判断是否为空  O(1)

resize() 动态扩缩容
- 时间复杂度为 O(n)
- 通过均摊复杂度分析，将resize()操作均摊到每一个出队或入队的操作中，即相当于每一个出队或入队操作执行两次出队或入队的基本操作，得到resize()操作的均摊时间复杂度为O(1)
- （参见《数据结构之数组》一文中时间复杂度分析）

## 实践

1. [Leetcode 20. Valid Parentheses 匹配括号](https://leetcode-cn.com/problems/valid-parentheses/)
2. [Leetcode 102. Binary Tree Level Order Traversal 二叉树层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal)
3. [牛客网-在线编程-程序员代码面试指南-CD5-设计getMin功能的栈](https://www.nowcoder.com/ta/programmer-code-interview-guide)
4. [牛客网-在线编程-程序员代码面试指南-CD100-猫狗队列](https://www.nowcoder.com/ta/programmer-code-interview-guide)
5. 如何仅用队列结构实现栈结构
6. 如何仅用栈结构实现队列结构

### Leetcode 20. Valid Parentheses 匹配括号

https://leetcode-cn.com/problems/valid-parentheses/

**题目：**

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：
- 左括号必须用相同类型的右括号闭合。
- 左括号必须以正确的顺序闭合。
- 注意空字符串可被认为是有效字符串。
```
示例 1:
输入: "()"
输出: true
```

**解题思路：**
- 利用栈结构，遍历字符串的每一个字符；
- 当遍历的字符为'(','{','['这三种时，将字符入栈；
- 当遍历的字符为')','}',']'这三种时，弹出栈顶元素，如果不为对应的符号，返回false；
- 当遍历完全后，检查栈是否为空，如果不为空，返回false。

**参考代码：** [com.chen.data.struct.stack.Leetcode20ValidParentheses](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

### Leetcode 102. Binary Tree Level Order Traversal 二叉树层序遍历

https://leetcode-cn.com/problems/binary-tree-level-order-traversal

**题目：**

给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
```
示例：
二叉树：[3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：
[
  [3],
  [9,20],
  [15,7]
]
```
**解题思路1：**
- 利用队列结构，从头节点开始遍历二叉树，将头节点放入队列中，标记该节点高度为0（通过创建新的Node类，成员为TreeNode节点和该节点的高度depth）；
- 当队列不为空时，循环进行队列的出队操作，对出队元素，检查其左右孩子是否为空，如果不为空，将左孩子、右孩子先后放入队列中，同时标注左右孩子的高度为其自身值+1；
- 循环过程中，通过检查高度值是否变化，如果变化，使用一个新的数组保存出队的元素；

**参考代码：** [com.chen.data.struct.queue.Leetcode102BinaryTreeLevelOrderTraversal2](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

**解题思路2：**
- 利用两个队列，第一个队列存放当前遍历层级的节点，第二个队列存放下一层级的节点；
- 将第一个队列中所有节点逐个出队，并将节点的左、右子节点放入第二个队列中，当第一个队列为空且第二个队列不为空，说明当前层级已全部完成遍历，开始下一层级遍历；

**参考代码：** [com.chen.data.struct.queue.Leetcode102BinaryTreeLevelOrderTraversal](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)


### 牛客网-在线编程-程序员代码面试指南-CD5-设计getMin功能的栈

https://www.nowcoder.com/ta/programmer-code-interview-guide

**题目：**

实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。

对于每个getMin操作，输出一行表示当前栈中的最小元素是多少。

要求：pop、 push、 getMin操作的时间复杂度都是O(1)

**解题思路：**
- 使用数组实现栈，通过一个索引index，指向下一个入栈元素的位置，实现栈结构；
  - 当数据入栈时，将元素放入index指向的位置，index++；
  - 当数据出栈时，index--，返回index指向的位置的元素；
  - 在出栈和入栈过程中，增加扩缩容操作，实现动态栈；
  - （或者直接使用现有库中栈结构；）
- 使用两个栈一个存放数据的栈、一个存放当前数据栈中数据最小值的栈；
  - 当数据入栈时，将数据放入数据栈中；同时与最小栈的栈顶元素比较，如果当前数小，放入最小栈中；否则将最小栈的栈顶元素再放一次到最小栈中；（该步骤可以优化，最小栈空间优化） 
  - 当数据出栈时，对数据栈和最小栈同时执行一次出栈操作；
  - 当获取当前栈中元素最小值时，返回最小栈的栈顶元素；
- 最小栈空间优化：
  - 数据入栈时，比较最小栈栈顶元素与当前数，如果当前数小于等于栈顶元素，将其放入最小栈，否则不放入；
  - 数据出栈时，比较数据栈出栈元素是否与最小栈的栈顶元素相等，如果相等，最小栈同时也执行出栈操作，否则不执行。

**参考代码：** [com.chen.data.struct.stack.GetMinStack](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

### 牛客网-在线编程-程序员代码面试指南-CD100-猫狗队列

https://www.nowcoder.com/ta/programmer-code-interview-guide

**题目：**

实现一种猫狗队列的结构，要求如下：
1. 用户可以调用 add 方法将 cat 或者 dog 放入队列中
2. 用户可以调用 pollAll 方法将队列中的 cat 和 dog 按照进队列的先后顺序依次弹出
3. 用户可以调用 pollDog 方法将队列中的 dog 按照进队列的先后顺序依次弹出
4. 用户可以调用 pollCat 方法将队列中的 cat 按照进队列的先后顺序依次弹出
5. 用户可以调用 isEmpty 方法检查队列中是否还有 dog 或 cat
6. 用户可以调用 isDogEmpty 方法检查队列中是否还有 dog
7. 用户可以调用 isCatEmpty 方法检查队列中是否还有 cat

**解题思路：**
- 使用两个队列，一个队列存放cat，一个队列存放dog，放入各队列的同时，给cat和dog附加一个时间戳属性；
- pollDog时，使用dog队列出队
- pollCat时，使用cat队列出队
- pollAll时，比较dog队列和cat队列队首元素，哪一个先进来，让先进的出队；

**参考代码：** [com.chen.data.struct.queue.CatDogQueue](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

### 如何仅用队列结构实现栈结构

**解题思路：**
- 使用两个队列来实现一个栈；
- 当数据入栈时：如果两个队列都为空，往其中一个队列入队；否则往不为空的队列入队；
- 当数据出栈时：如果两个队列都为空，报错；否则将不为空的队列中除最后一个元素外全部放到为空的队列中，并将最后一个元素出队作为返回结果。

**参考代码：** [com.chen.data.struct.stack.QueueToStack](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)

### 如何仅用栈结构实现队列结构

**解题思路：**
- 使用两个栈实现一个队列，一个为数据写入栈，一个为数据弹出栈；
- 当数据入队时：只往数据写入栈进行入栈；
- 当数据出队时：
  - 如果数据弹出栈为空，将数据写入栈的所有元素出栈并放入数据弹出栈，再对数据弹出栈执行出栈操作，出栈元素作为返回结果；
  - 否则对数据弹出栈执行出栈操作，出栈元素作为返回结果。

**参考代码：** [com.chen.data.struct.queue.StackToQueue](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct)


## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)

## 参考

Leetcode

刘宇波《玩转数据结构》课程

左程云 牛客网 算法初级班课程