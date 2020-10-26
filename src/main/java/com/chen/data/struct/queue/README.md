# 栈和队列

在 数组 中，可以通过索引访问 随机 元素。 但是，某些情况下，可能需要限制处理的顺序。

栈：是一个 后入先出（LIFO）数据结构。通常，插入操作在栈中被称作入栈 push ，总是在堆栈的末尾添加一个新元素。删除操作，退栈 pop ，将始终删除最后一个元素。

![2.栈](https://img-blog.csdnimg.cn/20201021165401480.png)

队列：是一个 先入先出（FIFO） 的数据结构。插入（insert）操作也称作入队（enqueue），新元素始终被添加在队列的末尾。 删除（delete）操作也被称为出队（dequeue)，只能移除第一个元素。

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
- 可使用多种底层数据结构实现，如使用数组实现栈；
- 添加元素时，将元素放置在栈顶，栈的元素数量+1；
- 删除元素时，将栈顶的元素取出，栈的元素数量-1；

### 时间复杂度分析

ArrayStack<E>
- void push(E)  入栈  O(1)  均摊
- E pop()  出栈   O(1) 均摊
- E peek()  查看栈顶元素   O(1)
- int getSize()  获取栈元素数量  O(1)
- boolean isEmpty()  是否为空   O(1)

## 队列

队列是一种线性结构，相比数组，队列对应的操作是数组的子集，只能从一端（队尾）添加元素，只能从另一端（队首）取出元素；

队列是一种先进先出的数据结构，First In First Out(FIFO)。

### 原理

**数组队列：**
- 入队操作，不断往数组尾部添加元素
- 出队操作，将数组array[0]位置元素取出，将剩余元素全部往前移动一位；array[i] = array[i+1];

**固定容量循环队列：**
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

**动态容量循环队列：**

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
- （参见《数组》一文中时间复杂度分析）

## 实践

1. 数组栈  ArrayStack
2. Leetcode练习-20 Valid Parentheses 匹配括号 LC20ValidParentheses
3. 数组队列     ArrayQueue
4. 固定容量循环队列 LoopQueue
5. 动态容量循环队列 DynamicLoopQueue
6. Leetcode 102. Binary Tree Level Order Traversal  LC102LevelOrder

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/data-struct-and-algorithm](https://github.com/chentian114/data-struct-and-algorithm)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=Mzk0NTE0MjQ2MQ==&mid=2247483654&idx=1&sn=b3b49309eca19c2fa5bfe333b7e7bdfb&send_time=)

## 参考

Leetcode

刘宇波《玩转数据结构》课程