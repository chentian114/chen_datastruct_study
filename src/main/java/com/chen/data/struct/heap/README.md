# 堆和优先队列

[toc]

## 概述

在计算机的世界里，很多的应用场景只需要取得当前数据集中最大或者最小的元素，而对于数据集中其它数据，并不需要他们一定是有序的。

那么，我们如何高效快速地取得当前数据集中最大或者最小的元素呢？此时，新的数据结构「**堆**」就诞生了。

**堆（Heap）**
- 是一个可以被看成近似完全二叉树的数组，树上的每一个结点对应数组的一个元素。除了最底层外，该树是完全充满的，而且是从左到右填充。—— 来自：《算法导论》
- **堆包括最大堆和最小堆：**
  - 最大堆的每一个节点（除了根结点）的值不大于其父节点；
  - 最小堆的每一个节点（除了根结点）的值不小于其父节点。

![5.最大堆和最小堆](https://img-blog.csdnimg.cn/20210307095330409.png)

**二叉堆的性质：**
- 堆中某个节点的值总是不大于其父节点的值（最大堆，相应的可以定义最小堆）；
- 二叉堆是一棵完全二叉树；

**堆常见的操作：**
- heapify 建堆：把一个乱序的数组变成堆结构的数组，时间复杂度为 O(n)。
- heapPush：把一个数值放进已经是堆结构的数组中，并保持堆结构，时间复杂度为 O(log n)。
- heapPop：从最大堆中取出最大值或从最小堆中取出最小值，并将剩余的数组保持堆结构，时间复杂度为 O(log n)。
- heapSort：借由 heapify 建堆和 heapPop 对数组进行排序，时间复杂度为 O(nlogn)，空间复杂度为 O(1)。

**优先队列：**
- 维基百科：优先队列是计算机科学中的一类抽象数据类型。优先队列中的每个元素都有各自的优先级，优先级最高的元素最先得到服务；优先级相同的元素按照其在优先队列中的顺序得到服务。
- 出队顺序和入队顺序无关；和优先级相关；
- 在生活中，我们处理任务的时候总是会给任务区分优先级。首先我们会处理优先级高的任务，接着我们会处理下一个优先级高的任务。这其实就是一个 优先队列 。
- 堆结构的一个常见应用是建立优先队列（Priority Queue）。
- 优先队列 是一种抽象的数据类型，而 堆 是一种数据结构。所以 堆 并不是 优先队列 ， 堆 是实现 优先队列 的一种方式。

## 原理

**因为二叉堆是一棵完全二叉树，可以用数组存储二叉堆：**
- **如果数组下标从0开始，存在的特性有：**
  - 某个节点i的父节点索引为： parent(i) = (i - 1) / 2;
  - 某个节点i的左孩子节点索引为： left child(i) = 2 * i + 1;
  - 某个节点i的右孩子节点索引为： right child(i) = 2 * i + 2;
- **如果数组下标从1开始，存在的特性有：**
  - 某个节点i的父节点索引为： parent(i) = i / 2;
  - 某个节点i的左孩子节点索引为： left child(i) = 2 * i;
  - 某个节点i的右孩子节点索引为： right child(i) = 2 * i + 1;

![1.用数组存储堆](https://img-blog.csdnimg.cn/2021030614153187.png)

**heapPush（add-向堆中添加元素 和 Sift Up-上浮）:**
- 为满足二叉堆是完全二叉树的特性，在二叉树层序遍历最后一个元素之后添加新元素；
- 在数组实现中即数组的最末尾添加元素；
- 为满足堆左右孩子都小于父节点的特性，对最末尾元素进行Sift Up（上浮）:根据新元素的索引i，找到其父节点parent(i)，将两个元素进行比较：
  - 如果不满足堆的特性就将父节点与新元素节点位置进行交换；递归向上，直到到达根节点或者满足堆的特性才结束；
  - 否则添加元素完成；

![2.向堆中添加元素和SiftUp](https://img-blog.csdnimg.cn/20210306150214352.png)

**heapPop（extractMax-取出最大堆中的最大元素 和 Sift Down-下沉）：**
- 在最大堆中，可知堆顶元素是堆中最大的元素；
- 为满足二叉堆是完全二叉树的特性，将二叉堆层序遍历的最后一个元素与堆顶元素进行交换；
  - 在数组实现中，将数组首位元素与末位元素交换，要取出堆中最大的元素即删除数组最末尾的元素；
- 为满足堆左右孩子都小于父节点的特性，对堆顶元素（数组首位元素）进行Sift Down(下沉)：根据堆顶元素的索引i，找到其左孩子left child(i) 和 右孩子right child(i)，将堆顶元素与其左右孩子中元素值最大的孩子进行比较：
  - 如果不满足堆的特性，就将该节点与其元素值最大的孩子节点交换，递归向下，直到到达叶子节点或者满足堆的特性才结束；
  - 否则取出堆中的最大元素完成；

![3.取出堆中的最大元素和SiftDown](https://img-blog.csdnimg.cn/20210306150308764.png)

**replace（取出堆中最大元素后，再放入一个新元素）**
- 实现1：可以先heapPop()取出最大堆中最大元素，再heapPush()添加新元素，两次O(logn)的操作
- 实现2：可以直接将堆顶元素替换以后Sift Down，一次O(logn)的操作

**heapify（将任意数组整理成堆的形状）：**
- 实现1：遍历数组元素，将其逐个添加到堆中；算法复杂度O(nlogn)
- 实现2：先将数组看成一棵完全二叉树，从最后一个非叶子节点（最后一个元素的父节点）开始进行Sift Down，不断向前直到根节点为止；算法复杂度为O(n)

**在N个元素中选出前M个最大的元素（M远小于N）：**
- 案例：在1000000个元素中选出前100名？
- 实现1：排序 NlogN，将所有元素从大到小排序，取出前M个元素
- 实现2：使用堆 NlogM，使用最小堆，维护当前看到的前M个元素，遍历剩余的所有元素，让其于最小堆中的最小值比较：
  - 如果比最小堆中的最小值还小，就可以直接忽略 ；否则将最小值替换成该元素；
  - 最小堆中始终维持着当前最大的前M个元素，直到遍历完所有元素，得到所有元素中的前M个最大的元素。

## 时间复杂度分析

**Heap（堆）**
- heapPush（add - 向堆中添加元素 和 Sift Up-上浮 ）: O(log(n)) 
- heapPop(extractMax-取出堆中的最大元素 和 Sift Down-下沉）：O(log(n)) 
- replace（取出堆中最大元素后，再放入一个新元素）: O(log(n))
- heapify（将任意数组整理成堆的形状）： O(n)

## 堆相关的更多话题：

**d叉堆 d-ary heap ：**
- 每个节点有3个孩子或更多；
- 对于这种堆，它的层数是更低的；
- 添加和删除元素操作， 其时间复杂度 logn 将不是以2为底，而是以d为底；
- 但相应的代价是，对于每一个节点，执行下沉操作时，需要考虑的节点数变多了。

![4.d叉堆](https://img-blog.csdnimg.cn/20210306154150498.png)

**索引堆：**
- 对于我们实现的二叉堆是只能看到堆顶元素的；
- 当我们需要对堆中间的元素进行查看、修改等操作时，便可以用到索引堆；
- 索引堆除了保存关注的元素外还有一个对应的索引，可以通过索引非常方便的检索到该元素存在堆中的什么位置；
- 在图论中的最短路径算法Dijkstra算法等都可以用索引堆进行优化。

**二项堆**

**斐波那契堆**


## 实现

1. 基于数组实现最大堆
2. 基于数组实现最小堆
3. 使用堆实现优先队列
4. LeetCode 347. Top K Frequent Elements 前 K 个高频元素
5. 牛客网-CD80-随时找到数据流的中位数

### 基于数组实现最大堆

**Heap<E>:**
- int size() // 返回堆中的元素个数
- boolean isEmpty() // 返回一个布尔值, 表示堆中是否为空
- void heapPush(E e); //向堆中添加元素
- E heapPop();  //取出最大堆中最大元素或最小堆中最小元素
- void heapify(E[] data);  //将一个数组整理成堆

**MaxHeap<E extends Comparable<E>>**
- public MaxHeap(E[] arr)
- public int size() // 返回堆中的元素个数
- public boolean isEmpty() // 返回一个布尔值, 表示堆中是否为空
- public void heapify(E[] data); //将一个数组整理成堆
- private int parent(int index) // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
- private int leftChild(int index) // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
- private int rightChild(int index) // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
- public void heapPush(E e) // 向堆中添加元素
- private void siftUp(int k) // 看堆中的最大元素
- public E findMax()
- public E heapPop() // 取出最大堆中最大元素
- private void siftDown(int k)
- public E replace(E e) // 取出堆中的最大元素，并且替换成元素e

**参考代码：** [com.chen.data.struct.heap.MaxHeap](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/heap)


### 基于数组实现最小堆

**参考代码：** [com.chen.data.struct.heap.MinHeap](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/heap)

### 使用堆实现优先队列

**PriorityQueue<E> implements Queue<E>**
- public void enqueue(E) 入队
- public E dequeue() 出队
- public E getFront() 获取队首
- public int getSize() 获取队列元素数量
- public boolean isEmpty() 队列是否为空

**参考代码：** [com.chen.data.struct.heap.PriorityQueue](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/heap)

###  LeetCode 347. Top K Frequent Elements 前K个高频元素

https://leetcode-cn.com/problems/top-k-frequent-elements/

给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

**参考代码：** [com.chen.data.struct.heap.LeetCode347TopKFrequentElements](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/heap)

### 牛客网-CD80-随时找到数据流的中位数

https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/

牛客网-题库-在线编程-程序员代码面试指南-CD80-随时找到数据流的中位数

https://www.nowcoder.com/ta/programmer-code-interview-guide

有一个源源不断的吐出整数的数据流，假设你有足够的空间来保存吐出的数。请设计一个名叫MedianHolder的结构，MedianHolder可以随时取得之前吐出所有数的中位数。

[要求]
1. 如果MedianHolder已经保存了吐出的N个数，那么将一个新数加入到MedianHolder的过程，其时间复杂度是O(logN)。
2. 取得已经吐出的N个数整体的中位数的过程，时间复杂度为O(1)

**解题方法一：**
- 使用一个数组收集所有数据流输出的数，当需要获取中位数时，对数组进行排序，取出中间的数； 
- 时间复杂度：O(n * logn)

**解题方法二：**
- 利用堆的特性，分别建立一个大根堆和一个小根堆，如果已经输出的数的总数为N，每个堆中存放N/2个数，同时维持小根堆中的数都是不小于大根堆中的数；当数据流中有数据输出时:
- 第一步：如果两个堆都为空将其放入大根堆；
- 第二步：输出的元素与大根堆中的堆顶元素比较，如果输出的元素较大，放入小根堆，同时执行Sift Up操作，维护小根堆的特性；否则放入大根堆中，执行Sift Up操作，维持大根堆的特性；
- 第三步：比较大根堆与小根堆的元素数量，两个堆的元素数量差值不超过1；否则：
  - 将数量多的堆，取出堆的根节点放入另一堆中，
  - 取出根节点的堆，根节点使用最后一个元素替换，堆的元素数量减1，执行Sift Down操作，维护该堆的特性；
  - 将取出的元素放到另一个堆的最后一个位置中，执行Sift Up操作，维护该堆的特性。
- 第四步：当需要获取已输出数据的中位数时，根据已输出数据总数N，以及大根堆和小根堆元素数量，来确定中位数是：大根堆的根节点（大根堆中的最大值）或 小根堆的根节点（小根堆中的最小值）。
- 时间复杂度：O(logn)
 

**参考代码：** [com.chen.data.struct.heap.MedianFinder](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/heap)


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