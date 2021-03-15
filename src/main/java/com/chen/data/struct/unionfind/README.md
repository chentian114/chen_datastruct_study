# 数据结构之并查集 Union Find 剖析

[toc]

## 概述

**并查集（英文：Disjoint-set data structure，直译为不交集数据结构）：**
- 是一种数据结构，
- 用于处理一些不交集（Disjoint sets，一系列没有重复元素的集合）的合并及查询问题。
- 并查集支持如下操作：
  - **查询：** 查询某个元素属于哪个集合，通常是返回集合内的一个“代表元素”。这个操作是为了判断两个元素是否在同一个集合之中。
  - **合并：** 将两个集合合并为一个。
  - 添加：添加一个新集合，其中有一个新元素。添加操作不如查询和合并操作重要，常常被忽略。

由于支持查询和合并这两种操作，并查集在英文中也被称为**联合-查找数据结构**（Union-find data structure）或者**合并-查找集合**（Merge-find set）。

一般来说，并查集”特指其中最常见的一种实现：**不交集森林（Disjoint-set forest）**：
- 经过优化的不交集森林有线性的空间复杂度， O(n)；
- 以及接近常数的单次操作平均时间复杂度，O(1)；
- 是效率最高的常见数据结构之一。

**并查集是一种很不一样的树形结构，由多棵树组成，孩子节点指向父亲节点，可以非常高效的回答连接问题；**

![1.并查集](https://img-blog.csdnimg.cn/20210312150852354.png "并查集")

**应用：**
- 连接问题 Connectivity Problem
  - 一个图中任意两个点是否有连接
  - 网络中节点间的连接状态，网络是个抽象的概念，如用户之间形成的网络，社交网络中两个用户是否有连接状态
- 连接问题和路径问题比较
  - 路径问题：查询网络中两个节点的连接路径
  - 连接问题：比路径问题要回答的问题少

## 原理

**不交集森林：**
- 表示：
  - 不交集森林把每一个集合以一棵树表示，每一个节点即是一个元素。节点保存着到它的父节点的引用，树的根节点则保存一个空引用或者到自身的引用或者其他无效值，以表示自身为根节点。
- 添加：
  - 添加一个元素x，这个元素单独属于一个仅有它自己的集合。
  - 在不交集森林中，添加操作仅需将元素标记为根节点。
- 查询：
  - 查询操作Find(x)从x开始，根据节点到父节点的引用向根行进，直到找到根节点。
  - 路径压缩优化：
    - 在集合很大或者树很不平衡时，上述查询操作的效率很差，最坏情况下（树退化成一条链时）；
    - 常见的优化是路径压缩：在查询时，把被查询的节点到根节点的路径上的所有节点的父节点设置为根结点，从而减小树的高度。
    - 也就是说，在向上查询的同时，把在路径上的每个节点都直接连接到根上，以后查询时就能直接查询到根节点。
- 合并：
  - 合并操作Union(x, y)把元素x所在的集合与元素y所在的集合合并为一个。
  - 合并操作首先找出节点x与节点y对应的两个根节点，如果两个根节点其实是同一个，则说明元素x与元素y已经位于同一个集合中，否则，则使其中一个根节点成为另一个的父节点。

**并查集 Union Find : 对于一组数据，主要支持两个动作：**
- 合并： 
  - unionElements(p , q) 并集，将p,q两个数据及所在的集合进行合并，属于同一个集合中的所有节点，其指向的根节点都是相同的；
- 查询是否在同一个集合：
  - isConnected(p , q) ，查询p,q两个数据是否属于同一个集合 -> find(p) == find(q)，p,q两个节点指向相同的根节点，表示它们属于同一个集合

**实现方式一：Quick Find 使用数组模拟实现并查集：**
- 使用数组的index索引存储元素，数组存储的值代表元素所在的集合的编号；
- unionElements(p,q)：
  - 合并p,q两个元素所在的集合，qID = find(q), pID = find(p),遍历数组中所有元素，将元素值等于pID的值改为qID，
  - 时间复杂度O(n)；
- isConnected(p,q)：
  - 检查数组中索引p,q的数据是否相同即可； find(p) == find(q)，
  - 时间复杂度 O(1)；

![2.QuickFind并查集实现](https://img-blog.csdnimg.cn/20210313131148119.jpg)

**Quick Union 实现并查集：**
- 原理：
  - 将每一个元素，看做是一个节点，构建一棵棵由子节点指向父节点的树
  - 使用一个数组parent[]存放集合中元素，数组的索引代表元素，数组存放的值代表元素指向的父节点元素，当元素在数组中存放的值指向自身时，表示该元素为该集合的根节点元素(parent[i] = i)
- 初始化：
  - 初始时，每一个节点指向自己，即每一个节点都是一个独立的集合
- 合并两个元素的集合时：
  - 先找到其中一个元素指向的根节点元素节点(parnt[i] = i )，
  - 并将该根节点元素改为指向另一个元素指向的根节点元素，
  - 即两个元素所在集合中所有元素指向的根节点都是相同的，
  - 时间复杂度O(h) h是树的高度
- 查找两个元素是否在同一个集合：
  - 只需检查两个元素是否指向相同的根元素，
  - 时间复杂度O(h) h是树的高度

![Quick Union](https://img-blog.csdnimg.cn/20210313131106737.jpg)

**并查集优化-基于size,sz[i]表示以i为根的集合中元素的个数：**
- 问题：
  - 合并两个元素时，不考虑两个元素所在集合的情况，直接将其中一个元素指向的根节点元素指向另一个元素的根节点元素，有可能会让多棵树退化成链表，
  - 如：不断的让元素多的集合的根节点元素指向只有一个节点的集合的根节点元素；
- 优化方式：基于集合元素数量优化合并：
  - 合并两个集合时，将节点数少的集合的根节点指向指向节点数多的集合的根节点
  - 使用一个数组sz[]存放以元素i为根的集合的元素个数，sz数组初始化为1
  - 合并两个元素时，
    - 将其中一个元素指向的根节点元素pRoot的sz[pRoot] 与 另一个元素指向的根节点元素qRoot的sz[qRoot]进行比较，
    - 将元素数量小的根节点元素指向元素数量多的根节点元素，
    - 同时元素数量多根节点的sz[]数组要加上元素数量少根节点的sz[]值，
    - 如if(sz[pRoot] > sz[qRoot] ) sz[pRoot] += sz[qRoot]

![Quick Union](https://img-blog.csdnimg.cn/20210313131229780.jpg)

**并查集优化-基于rank，rank[i]表示根节点为i的树的高度：**
- 优化的目的：
  - 合并两棵树的时候，得到的新树它的整体高度尽量不要每次都增加
- 优化方式：更直接的一种方式就是基于rank的优化
  - 在每一个节点上记录以这个节点为根的树，它的最大的深度是多少
  - 在合并两个集合时，应将深度比度低的集合的根节点元素指向深度比较高的集合的根节点元素，
  - 如果两个集合高度相关，两个集合合并后，深度加1；否则，合并后的集合深度不变；
- 实现：
  - 使用一个rank数组存放以元素i为根节点的树的高度
  - 初始化时，rank数组初始化为1
  - 合并两个元素时，
    - 如果一个元素指向的根节点元素的rank小于或大于另一个元素指向的根节点元素，只需将rank小的根节点元素指向rank高的根节点元素即可
    - 如果两个元素指向的根节点元素的rank相等时，将其中一个元素指向的根节点元素pRoot指向另一个元素指向的根节点元素qRoot，同时rank[qRoot] = rank[qRoot]+1高度加1

![Quick Union](https://img-blog.csdnimg.cn/20210313131015779.jpg)

**路径压缩 Path Compression：**
- 目的：
  - 将一棵深度比较高的树压缩成为一个深度比较低的树，
  - 因为每一棵树的孩子节点是没有限制的，最理想的情况下每一棵树都只有2层
- 优化：
  - 在find操作时进行路径压缩，
  - 在find(p)元素时，查找元素p的根节占时同时执行parent[p] = parent[parent[p]] ，
  - 即将某一个元素p指向的父亲节点指向其父亲的父亲节点；

![路径压缩](https://img-blog.csdnimg.cn/2021031313160984.jpg)


## 时间复杂度分析

**Quick Union**
- 查询和合并： 时间复杂度 O(h)，经过路径压缩后近乎O(1)

## 实现
```java
interface UF{
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
    int getSize();
}
```
1. 实现Quick Find并查集（基于数组）
2. 实现Quick Union并查集（基于多棵树结构）
3. 考虑size优化并查集
4. 基于rank优化并查集
5. 路径压缩优化并查集 + 比较路径压缩和非路径压缩的效率
7. Leetcode 547. Friend Circles

### 实现Quick Find并查集

**参考代码：** [com.chen.data.struct.unionfind.UnionFind1](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/unionfind)

### 实现Quick Union并查集

**参考代码：** [com.chen.data.struct.unionfind.UnionFind2](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/unionfind)

### 考虑size优化并查集

**参考代码：** [com.chen.data.struct.unionfind.UnionFind3](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/unionfind)

### 基于rank优化并查集

**参考代码：** [com.chen.data.struct.unionfind.UnionFind4](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/unionfind)

### 路径压缩优化并查集 

比较路径压缩和非路径压缩的效率

**参考代码：** [com.chen.data.struct.unionfind.UnionFind5](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/unionfind)

### Leetcode 547. Friend Circles

有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。

https://leetcode-cn.com/problems/number-of-provinces

**参考代码：** [com.chen.data.struct.unionfind.Leetcode547NumberOfProvinces](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/unionfind)

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)

## 参考

刘宇波《玩转数据结构》课程

https://www.wiki-wiki.top/wiki/并查集

Leetcode