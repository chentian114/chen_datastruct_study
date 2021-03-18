# 数据结构之平衡二叉树剖析

[toc]

## 概述

普通的二叉搜索树有可能因为数据样本的情况，退化成一个链表，使得二叉查找树的性能下降，于是便有了相应的自平衡二叉查找树来解决二叉查找树因为数据样本情况退化成一个链表的问题。

**AVL树**
- （Adelson-Velsky and Landis Tree）是计算机科学中最早被发明的自平衡二叉查找树，简称AVL树。
- 在AVL树中，任一节点对应的两棵子树的最大高度差为1，因此它也被称为高度平衡树。
- 查找、插入和删除在平均和最坏情况下的时间复杂度都是 O(logn) 。
- 增加和删除元素的操作则可能需要借由一次或多次树旋转，以实现树的重新平衡。

AVL树得名于它的发明者G. M. Adelson-Velsky和Evgenii Landis，他们在1962年的论文《An algorithm for the organization of information》中公开了这一数据结构。

![1.AVL树](https://img-blog.csdnimg.cn/20210316043650794.png)

**节点的平衡因子：**
- 节点的平衡因子是它的左子树的高度减去它的右子树的高度（有时相反）。
- 带有平衡因子1、0或 -1的节点被认为是平衡的。
- 带有平衡因子 -2或2的节点被认为是不平衡的，并需要重新平衡这个树。
- 平衡因子可以直接存储在每个节点中，或从可能存储在节点中的子树高度计算出来。

![2.标注高度及平衡因子](https://img-blog.csdnimg.cn/20210317131051724.jpg "标注高度和计算平衡因子")

## 原理

**基本操作：**
- AVL树的基本操作一般与在不平衡的二叉查找树中运作同样的算法。
- 但是要进行预先或随后做一次或多次所谓的"AVL旋转"，
- 使得二叉查找树在添加元素或删除元素后，既要保持二叉查找树（任一节点大于其左孩子节点，小于其右孩子节点）的特性，也要符合平衡二叉树的特性（左右子树最大高度差为1）。

**情况一：LL 和 右旋转**
- 插入的元素在 不平衡的节点 的左侧的左侧， 
  - 不平衡节点 条件： ( 左子树的高度 - 右子树的高度 ) > 1
  - 插入 不平衡节点 左侧的左侧，说明 不平衡节点的左孩子节点的： 左子树的高度 >= 右子树的高度
  -  ( getBalanceFactor(node) > 1 && getBalanceFactor(node.left) >= 0 )
  -  getBalanceFactor(Node curNode) 计算当前节点的平衡因子：左子树的高度 - 右子树的高度
- 旋转处理：
  - 对 不平衡节点 进行 **右旋转**
- **右旋转：**
  - 右旋转操作，将 根节点y 的 左孩子节点x 变为新的根节点：
    - x节点 本来的右孩子T3 变成 y节点的左孩子， y.left = T3;
    - x节点 的右孩子指向原先的 根节点y， x.right = y;
  - 维护受影响元素的的节点的高度，如下图的 y节点 和 x节点
    - 先维护 y节点的高度： y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    - 再维护 x节点的高度:  x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

![3.插入情况LL和右旋转](https://img-blog.csdnimg.cn/20210316223253867.jpg)

**情况二：RR 和 左旋转**
- 插入的元素在不平衡节点的右侧的右侧
  - 不平衡节点 条件： ( 左子树的高度 - 右子树的高度 ) < -1
  - 插入 不平衡节点 右侧的右侧，说明 不平衡节点的右孩子节点的： 右子树的高度 >= 左子树的高度
  - ( getBalanceFactor(node) < -1 && getBalanceFactor(node.right) <= 0 )
- 旋转处理：
  - 对 不平衡节点 进行 **左旋转**
- 左旋转：
  - 左旋转操作，将 根节点y 的 右孩子节点x 变为新的根节点：
    - x节点 本来的左孩子T3 变成 y节点的右孩子， y.right = T3;
    - x节点 的左孩子指向原先的 根节点y， x.left = y;
  - 维护受影响元素的的节点的高度，如下图的 y节点 和 x节点
    - 先维护 y节点的高度： y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    - 再维护 x节点的高度:  x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    - getHeight(curNode)：获取当前节点的高度，如果为空返回 0，否则返回node.height；

![4.插入情况RR和左旋转](https://img-blog.csdnimg.cn/20210316224152762.jpg)

**情况三：LR 和 先左旋转再右旋转**
- 插入的元素在不平衡定节点左孩子的右侧
  - 不平衡节点 条件 ： ( 左子树的高度 - 右子树的高度 ) > 1
  - 插入 不平衡节点 左孩子的右侧，说明 不平衡节点的左孩子节点的： 左子树的高度 < 右子树的高度 
  - ( getBalanceFactor(node) > 1 && getBalanceFactor(node.left) < 0 )
- 旋转处理：
  - 先对不平衡节点的左孩子节点进行 左旋转 操作，转化为 LL 的情况
  - 再对不平衡节点进行 右旋转 操作

![5.插入情况LR和先左旋转再右旋转](https://img-blog.csdnimg.cn/20210316230026112.jpg)

**情况四：RL 和 先右旋转再左旋转**
- 插入的元素在不平衡定节点右孩子的左侧
  - 不平衡节点 条件： ( 左子树的高度 - 右子树的高度 ) < -1
  - 插入 不平衡节点 右孩子的左侧，说明 不平衡节点的右孩子节点的： 左子树的高度 > 右子树的高度
  - ( getBalanceFactor(node) < -1 && getBalanceFactor(nodr.right) > 0 )
- 旋转处理：
  - 先对不平衡节点的右孩子节点进行 右旋转 操作，转化为 RR 的情况
  - 再对不平衡节点进行 左旋转 操作

![6.插入情况RL和先右旋转再左旋转](https://img-blog.csdnimg.cn/20210316233207279.jpg)

在左左和右右的情况下，只需要进行一次旋转操作；在左右和右左的情况下，需要进行两次旋转操作。

**AVL维护平衡的四种情况：**

![7.Tree_Rebalancing](https://img-blog.csdnimg.cn/20210316053900391.png)


**平衡二叉树示例：**

![8.AVL_Tree_Example](https://img-blog.csdnimg.cn/20210316233443495.gif)

## 时间复杂度分析

|算法	|	平均	|最差 |
|:-- | :-- | :-- |
|空间|		O(n)|	O(n)|
|搜索|		O(log n)|	O(log n)|
|插入|		O(log n)|	O(log n)|
|删除|		O(log n)|	O(log n)|

## 实现

1. 实现平衡二叉树
2. 使用平衡二叉树实现映射和集合
3. Leetcode 349.Intersection of Two Arrays 两个数组的交集
4. Leetcode 350.Intersection of Two Arrays II 两个数组的交集

### 实现平衡二叉树

Tree<E> 
- int size();  树中节点数量
- boolean isEmpty();
- void add(E value); 往树中添加元素
- boolean contains(E value);  检查树中是否包含某个元素
- E minimum(); 获取树中最小的元素
- E maximum(); 获取树中最大的元素
- void remove(E e); 删除树中的元素
    
**参考代码：** [com.chen.data.struct.avl.AVLTree](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/avl)

### 使用平衡二叉树实现映射和集合

**参考代码：** [com.chen.data.struct.avl.AVLTreeMap](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/avl)


**参考代码：** [com.chen.data.struct.avl.AVLTreeSet](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/avl)

### Leetcode 349.Intersection of Two Arrays  两个数组的交集

https://leetcode-cn.com/problems/intersection-of-two-arrays/

给定两个数组，编写一个函数来计算它们的交集。

示例 1：
- 输入：nums1 = [1,2,2,1], nums2 = [2,2]
- 输出：[2]

示例 2：
- 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
- 输出：[9,4]

**参考代码：** [com.chen.data.struct.avl.Leetcode349IntersectionOfTwoArrays](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/avl)


### Leetcode 350.Intersection of Two Arrays II  两个数组的交集

https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/

给定两个数组，编写一个函数来计算它们的交集。

示例 1：
- 输入：nums1 = [1,2,2,1], nums2 = [2,2]
- 输出：[2,2]

示例 2:
- 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
- 输出：[4,9]

**参考代码：** [com.chen.data.struct.avl.Leetcode350IntersectionOfTwoArrays](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/avl)


## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)

## 参考

刘宇波《玩转数据结构》课程

https://www.wiki-wiki.top/baike-AVL树