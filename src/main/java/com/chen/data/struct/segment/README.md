# 数据结构之线段树剖析

[toc]

## 为什么要使用线段树

对于有一类问题，我们关心的是线段（或者区间）：

最经典的线段树问题：**区间染色：**
- 描述：
  - 有一面墙，长度为n，每次选择一段墙进行染色，
  - m次操作后，我们可以看见多少种颜色？
  - m次操作后，我们可以在[i,j]区间内看见多少种颜色？
- 操作：
  - 染色操作（更新区间）
  - 查询操作（查询区间）

另一类经典问题：**区间查询：**
- 描述：
  - 数据存放在一个给定长度为 n 的数组中，需要对一个区间所有的数组进行统计查询，  
  - 如：查询一个区间[i,j]的最大值、最小值、或者区间数字和等。
- 实质：基于区间的统计查询，区间中的数据是会动态更新的，日常中常见的场景：
  - 2017年注册用户中消费最高的用户？消费最少的用户？学习时间最长的用户？
  - 某个太空区间中天体问题？

如果我们使用数组来解决该问题，时间复杂度为：
- 查询区间： O(n)
- 更新区间： O(n)

实际需求中，我们需要性能更好的实现；

使用线段树实现，时间复杂度为：
- 查询区间： O(logn)
- 更新区间： O(logn)

**线段树解决的问题：** 
- 对于给定区间：
  - 更新：更新区间中一个元素或者一个区间的值
  - 查询：查询一个区间[i,j]的最大值、最小值、或者区数字和等
- 给定的区间的长度一般是固定的；
- 对于一个线段树，每一个节点存储的是一个区间中相应的统计值


**统计区间中数据的和为例：**
- 构建线段树，线段树每个节点存放该节点区间数值的和（同理也可以是最大值或最小值等）
  
![1.线段树](https://img-blog.csdnimg.cn/20200510142117635.png)

- 查询区间[4,7]的和，只需要在线段树中查询到A[4,7]的节点即可获得结果
- 查询区间[2,5]的和，只需要在线段树中查询到A[2,3] 和 A[4,5]两个节点，再对两个节点的值求和即可获得结果 

![2.线段树区间求和示例](https://img-blog.csdnimg.cn/2021030907441290.jpg?)

## 概述

**线段树（Segment Tree）** 是一种二叉树形数据结构，1977 年由 Jon Louis Bentley 发明，用以存储区间或线段，并且允许快速查询结构内包含某一点的所有区间。

一个包含 n 个区间的线段树，空间复杂度为 O(n)，查询的时间复杂度则为O(logn + k)，其中 k 是符合条件的区间数量。

线段树是一个平衡的二叉树，所有叶子到根的距离最多只相差1。令整个区间的长度为N，则其有N个叶节点，每个叶节点代表一个单位区间，每个内部结点代表的区间为其两个儿子代表区间的联集。

线段树不是完全二叉树；线段树是平衡二叉树；堆也是平衡二叉树。

## 原理

**创建线段树：** 
- 根节点代表整个区间，再从根节点开始进行平分，直到节点的区间长度为1结束；

![3.线段树](https://img-blog.csdnimg.cn/20200510145633171.png)

**线段树存储结构：**
- 方式一：参照二叉搜索树，使用动态的二叉树存储结构
- 方式二：使用固定数组存放：
  - 将线段树看做满二叉树，用数组来存放；
  - 但线段树并不一定是满二叉树，因此有一个问题就是：如果有n个元素的区间，使用数组存放需要多少节点？

**满二叉树的特性：**
- 对 h 层的满二叉树，共有节点数 2^h - 1 个节点
- 从 0 层 到 倒数第二层 的所有节点数，相当于为 高度为 (h-1) 的满二叉树，其共有节点数 2^(h-1) - 1 个节点；
- 最后一层即 (h-1) 层，节点数 2^(h-1) 个节点
- 得出最后一层的节点数大致等于前面所有层节点数之和

**如果有n个元素的线段树，使用数组存储所需的空间：**
- 若 n = 2^k ，例 n=8 ，即其最后一层节点数为n，得出使用数组存放元素为 n 的线段树所需的空间为 2 * n；
- 若 n = 2^k + 1 ，为最坏情况，例 n=9 ，所有叶子节点并未在同一层，而是需要再新增一层存放，因为新增的一层的节点数大致等于前面所有层节点数之和，即需要 (2 * n) * 2 = 4 * n 的数组空间来存放所有元素。
- 总体评估：如果元素有n个，使用4*n大小的数组存储线段树；

**创建线段树实现：**
- 父节点存储的信息是其左、右两个孩子信息的综合，需要以具体业务场景来决定，以求和为例：
- 通过建立线段树来查询区间中元素的和，相应的线段树每个节点存储的是区间中所有元素的和；
- 要创建线段树的根，先要创建根的左、右孩子，根节点存储的值是左、右孩子存储值的求和；
- 而要创建根节点的左、右孩子，又需要创建以根的左、右孩子为父节点的左、右孩子，以此类推，递归直到左、右孩子节点所存储的区间不能再划分为止，即区间长度为1；

**线段树的查询：**
- 区间[l...r]的线段树，其左、右孩子是对其区间的对半切分的，int mid = l + (r - l) / 2; 其左孩子的区间为[l,mid]、右孩子的区间为[mid+1,r]
- 若要查询区间[queryL...queryR]的值，
  - 从根节点开始遍历线段树，找到匹配的区间值进行求和，
  - 如从根节点的左、右孩子开始找区间[queryL,mid]、[mid+1, queryR]，
  - 以此类推，递归直接找到区间相匹配的节点并返回节点值；
- 例：在以treeIndex为根的线段树中，在其区间为[l...r]的范围里，搜索区间[queryL...queryR]的值，如果：
    - 查询区间正好匹配，直接返回 ；
      - ( if(l == queryL && r == queryR) return tree[treeIndex]; )  
    - 查询区间只在右子树，只往右子树查询；
      - if(queryL >= mid + 1) return query(rightTreeIndex, mid + 1, r, queryL, queryR); 
    - 查询区间只在左子树，只往左子树查询；
      - else if(queryR <= mid) return query(leftTreeIndex, l, mid, queryL, queryR); 
    - 查询区间部分在左子树、部分在右子树，分别往左、右子树查询，并求和
      - else{  
      - E leftResult = query(leftTreeIndex, l, mid, queryL, mid); 
      - E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR); 
      - return merger.merge(leftResult, rightResult);
      - } 

![线段树](https://img-blog.csdnimg.cn/20200511071419959.png)

**线段树更新一个位置：**
- 例：将index位置的值，更新为e
- 从根节点开始，计算存放index所在的子树，并依此类推，递归直到叶子节点为止，并更新该叶子节点的值，并返回新的值，递归更新其父节点的值；
- 例：在以treeIndex为根的线段树中更新index的值为e
    - 递归到叶节点，更新叶子节点的值；
      - if(l == r){  tree[treeIndex] = e; return;  }
    - index所在节点在右子树，更新右子树；
      - if(index >= mid + 1) set(rightTreeIndex, mid + 1, r, index, e);
    - index所在节点在左子树，更新左子树；
      - else if( index <= mid ) set(leftTreeIndex, l, mid, index, e);
    - 更新完孩子节点，返回值后更新父节点的值；
      - tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

**线段树更新一个区间：**
- 对于一个区间进行更新，例将[2,5]区间中所有元素+3
- 采用区间查询相同的逻辑找到要更新的区间，再更新区间中所有叶子节点的值，同时更新区间的值，再返回，递归更新父节点的值；
  - 采用该方式对叶子节点的更新所需的时间复杂度为O(n)级别点
- 懒惰更新：为提高性能，采用lazy数组记录未更新的内容，到下次查询区间时，查询到具体叶子节点时，先检查lazy数组中是否存在未更新的叶子节点，如果存在，同新更新叶子节点；

**其它线段树相关问题：**
- 二维线段树
- 动态线段树
- 树状数组

## 时间复杂度分析

**使用线段树：**
- 更新操作，时间复杂度 O(logn)
- 查询操作，时间复杂度 O(logn)

## 实现

1. 使用数组实现线段树
2. 使用二叉树实现线段树
3. Leetcode 303. Range Sum Query - Immutable
4. Leetcode 307. Range Sum Query - Mutable

###  使用数组实现线段树

SegmentTree
- public SegmentTree(E[] arr, Merger<E> merger) 构建线段树
    -  private void buildSegmentTree(int treeIndex, int l, int r) 在treeIndex的位置创建表示区间[l...r]的线段树
- public int getSize() 获取线段树元素数量
- public E get(int index) 获取线段树某一位置的元素
- public E query(int queryL, int queryR) 返回区间[queryL, queryR]的值
    - 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    - private int leftChild(int index) 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    - private int rightChild(int index) 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
- public void set(int index, E e) 将index位置的值，更新为e
    - private void set(int treeIndex, int l, int r, int index, E e)  在以treeIndex为根的线段树中更新index的值为e

**参考代码：** [com.chen.data.struct.segment.SegmentTree](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/segment)

### 使用二叉树实现线段树


**参考代码：** [com.chen.data.struct.segment.SegmentTree2](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/segment)

### Leetcode 303. Range Sum Query - Immutable 区域和检索 - 数组不可变

https://leetcode-cn.com/problems/range-sum-query-immutable/

给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j  两点。

实现 NumArray 类：
- NumArray(int[] nums) 使用数组 nums 初始化对象
- int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）

**参考代码：** [com.chen.data.struct.segment.Leetcode303NumArray](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/segment)

### Leetcode 307. Range Sum Query - Mutable

https://leetcode-cn.com/problems/range-sum-query-mutable/

给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。

实现 NumArray 类：
- NumArray(int[] nums) 用整数数组 nums 初始化对象
- void update(int index, int val) 将 nums[index] 的值更新为 val
- int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）

**参考代码：** [com.chen.data.struct.segment.Leetcode307NumArray](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/segment)

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)


## 参考

Wiki 百科 ： 线段树 & 线段树 (区间查询)

刘宇波《玩转数据结构》课程