# 数据结构之二叉搜索树剖析

[toc]

## 树

树 是一种经常用到的数据结构，用来模拟具有树状结构性质的数据集合。

二叉树 是每个节点最多有两个子树的树结构，通常子树被称作“左子树”和“右子树”。

树结构本身是一种天然的组织结构；将数据使用树结构存储后，出奇的高效。

**常见的树结构：**
- 二叉搜索树(Binary Search Tree)
- 平衡二叉树：AVL；
- 红黑树
- 堆
- 并查集
- 线段树
- Trie(字典树/前缀树)

树是一种动态数据结构。

当我们的算法需要使用一些特殊的操作时，将数据组装成树结构，针对某一类特殊的操作，会产生非常高效的结果；如并查集、堆都是为了满足对数据的某一类特殊操作进行高效的处理；

对于某些特殊的数据，将其以某种形式存储为树结构，结果是对这些特殊的数据，在它们所在领域的问题，相应的解决方案提供极其高效的结果，如线段树（处理线段）、Trie （处理字符串这类数据）；

**为什么需要数据结构：**
- 数据结构虽然解决的是数据存储的问题；
- 但是在使用的层面上，我们不仅仅是要存储数据所以使用数据结构；
- 更重要的的是，当我们使用某些特殊的数据结构存储数据后，将可以帮助我们、辅助我们更加高效的解决某些算法问题；甚至对于某些问题来说，若不使用数据结构根本无从解决。

## 二叉树

![11.二叉树](https://img-blog.csdnimg.cn/2020110216384712.png)

二叉树是一种动态数据结构；

二叉树具有唯一根节点，每个节点最多有两个孩子，每个节点最多有一个父亲；

一个孩子都没有的节点称为叶子节点；只有根节点没有父节点；

```java
class Node{
    E e;
    Node left;
    Node right;
}
```
二叉树具有天然递归结构，每个节点的左、右子树也是二叉树；

**二叉树类型：**
- **满二叉树**：除叶子节点外，每个节点都有两个子节点；如果一个二叉树的层数为k，且节点总数是(2^k) -1，则它就是满二叉树。
- **完全二叉树**：若设二叉树的深度为k，除第 k 层外，其它各层 (1～k-1) 的节点数都达到最大个数，第k 层所有的结点都连续集中在最左边，这就是完全二叉树。
- **二叉搜索树**：树的每个节点的值大于其左子树的所有节点的值、小于其右子树的所有节点的值、每一棵子树也是二分搜索树；相同值的节点只会存在一个、存储的元素必须有可比较性。
- **平衡二叉树**：它或者是一颗空树，或它的左子树和右子树的深度之差(平衡因子)的绝对值不超过1，且它的左子树和右子树都是一颗平衡二叉树。
- **最优二叉树（哈夫曼树）**：树的带权路径长度达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree)。哈夫曼树是带权路径长度最短的树，权值较大的结点离根较近。

**二叉树特性：**
- 满二叉树节点总数与层数k的关系：(2^k) -1；
- 满二叉树第k层一共多少个节点：2^(k-1)。
- 使用数组实现完全二叉树，其父子节点下标k的关系：一个节点的下标为k，其左孩子节点的下标为2k+1，右孩子节点的下标为2k+2；一个节点的下标为k，其父节点的下标(k-1)/2

**二叉树的遍历：**
- 前序遍历：先打印当前节点，然后打印整棵左子树，最后打印整棵右子树。
- 中序遍历：先打印整棵左子树，然后打印当前节点，最后打印整棵右子树。通常来说，对于二分搜索树，可以通过中序遍历得到一个递增的有序序列。
- 后序遍历：先打印整棵左子树，然后打印整棵右子树，最后打印当前节点。（应用场景：如释放内存、分治算法求结果等问题，先处理完左右孩子，再处理当前节点）
- 层序遍历：逐层地，从左到右访问所有节点。

## 二叉搜索树原理

二叉搜索树（Binary Search Tree）是一种特殊的二叉树：每个节点中的值必须大于其左侧子树中的任何值，但小于其右侧子树中的任何值。

![12.二叉搜索树](https://img-blog.csdnimg.cn/20201102165018322.png)

**添加新元素：**
- 递归实现：
  - 如果二叉树为空，添加的第一个元素成为根节点； 
  - 添加一个新元素，与根节点比较，如果小，就往其左子树添加；如果大就往其右子树添加；当往左(或右)子树添加且左(或右)子树为空时，新元素即为其左(或右)子树的根节点；否则依此继续递归；
  - 递归实现：
    - 递归终止条件：递归到当前节点为空时，用新元素创建新节点，返回插入新节点后二分搜索树的根；
    - 递归函数处理操作：如果新元素小于当前节点，往左子树节点递归添加该元素，当前节点左子树等于将元素插入左子树返回的结果；
    - 递归函数处理操作：如果新元素大于当前节点，往右子树节点递归添加该元素，当前节点右子树等于将元素插入右子树返回的结果；
    - 递归函数处理操作：返回当前节点，作为当前子树的根；
- 二分搜索树添加元素的非递归写法：和链表很像；
- 二分搜索树不包含重复元素，如果想包含重复元素的话，只需要定义：左子树小于等于节点；或者右子树大于等于节点；

**查询元素：**
- 从根节点递归遍历二叉树是否包含查询的元素，当节点为空时，返回false；与节点比较，相等返回true；小于往左子树寻找；大于往右子树中寻找；

**二叉树的遍历：把所有节点都访问一遍**
- 使用递归遍历二叉搜索树时，每个节点都会被访问三次；
  - 前序遍历是第一次访问该节点时打印；

![13.前序遍历](https://img-blog.csdnimg.cn/20210301071242581.jpg)

  - 中序遍历是第二次访问该节点时打印；
  
![14.中序遍历](https://img-blog.csdnimg.cn/20210301071330857.jpg)

  - 后序遍历是第三次访问该节点时打印；

![15.后序遍历](https://img-blog.csdnimg.cn/20210301071404905.jpg)

- **前序遍历递归实现** 
  - 从根节点开始递归遍历二叉树，递归终止条件：如果节点为空直接返回；
  - 访问当前节点元素；
  - 递归访问当前节点左子树；
  - 递归访问当前节点右子树；
- **前序遍历非递归实现：**
  - 利用栈存放访问的节点，初始将根节点压入栈；
  - 将栈顶元素弹出，访问该节点元素，并将该节点的右孩子压入栈、再将该节点左孩子压入栈；如此循环下去，直到栈为空；  
  - 左或右孩子为空时不需要压入栈；
  - 核心：利用栈结构，先访问当前节点，再把右孩子放入栈，再把左孩子放入栈，根据栈先进后出的特性打印的结果便为：当前节点、左子树、右子树。
- **前序遍历非递归实现2：**
  - 模拟系统栈，对每一个节点访问三次；
  - 创建一个新的结构，存放节点及访问该节点的次数；
  - 利用栈存放访问的节点，初始将“根节点-第一次访问”放入栈；
  - 将栈顶元素弹出，直到栈为空；
  - 如果该节点“第一次访问”，打印该元素，将“该节点-第二次访问”放入栈；如果该节点左孩子不为空，将“该节点左孩子-第一次访问”放入栈中；
  - 如果该节点“第二次访问”，将“该节点-第三次访问”放入栈；如果该节点右孩子不为空，将“该节点右孩子-第一次访问”放入栈中；
  - 如果该节点“第三次访问”，不做处理。
- **中序遍历递归实现：**
  - 从根节点开始递归遍历二叉树，递归终止条件：如果节点为空直接返回；
  - 递归访问当前节点左子树；
  - 访问当前节点元素；
  - 递归访问当前节点右子树；
- **中序遍历非递归实现：**
  - 准备一个栈
  - 当栈不为空，或当前节点不为空( while(!stack.isEmpty() || curNode != null) )，执行如下循环操作即可实现中序遍历：
    - 1)如果当前节点不为空，当前节点压入栈( stack.push(curNode) )，当前节点往左(curNode = curNode.left)；
    - 2)当前节点为空，从栈中弹出一个节点打印，当前节点往右(curNode = curNode.right)；
  - 核心：
    - 打印当前节点前，检查是否有左孩子节点，如果当前节点有左孩子节点，那就把当前节点压入栈，先打印其左孩子节点，其左孩子节点便变成了当前节点，如此循环下去；直到当前节点没有左孩子节点，便从栈中弹出元素打印，然后将其右孩子节点变成当前节点，继续上述循环；最终打印的节点的顺序便是： 先打印左子树、当前节点、右子树；
    - 其底层思想是：把一棵二叉树划分成了一条条包括当前节点在内的左边界；一次循环便会把当前节点及其整条左边界压入栈，打印顺序便是先左边界、再当前节点；当打印到当前节点后，当前节点便移动到其右孩子，这时又会将包括当前节点在内的整条左边界压入栈中，这样总体上就实现打印：左子树、当前节点、右子树；
- **中序遍历非递归实现2：**
  - 模拟系统栈，对每一个节点访问三次；
  - 创建一个新的结构，存放节点及访问该节点的次数；
  - 利用栈存放访问的节点，初始将“根节点-第一次访问”放入栈；
  - 将栈顶元素弹出，直到栈为空；
  - 如果该节点“第一次访问”，将“该节点-第二次访问”放入栈；如果该节点左孩子不为空，将“该节点左孩子-第一次访问”放入栈中；
  - 如果该节点“第二次访问”，打印该元素，将“该节点-第三次访问”放入栈；如果该节点右孩子不为空，将“该节点右孩子-第一次访问”放入栈中；
  - 如果该节点“第三次访问”，不做处理。
- **后序遍历递归实现：**
  - 从根节点开始递归遍历二叉树，递归终止条件：如果节点为空直接返回；
  - 递归访问当前节点左子树；
  - 递归访问当前节点右子树；
  - 访问当前节点元素；
- **后序遍历非递归实现：**
  - 后序遍历打印节点的顺序为： 左子树、右子树、当前节点； 
  - 利用先序遍历非递归实现完成后序遍历：
    - 我们已经实现先序遍历非递归实现打印节点的顺序为： 当前节点、左子树、右子树；
    - 我们可以将其稍微改动一下，实现打印顺序为：当前节点、右子树、左子树；
    - 然后再将结果全部放入一个新的栈中，最后一次性将栈中元素打印，得到的打印顺序便为：左子树、右子树、当前节点；便完成了后序遍历。
  - 实现：
    - 利用栈存放访问的节点，初始将根节点压入遍历栈；
    - 将栈顶元素弹出，将访问该节点元素的值再放入一个新的打印栈中，并将该节点的左孩子压入遍历栈、再将该节点右孩子压入遍历栈；如此循环下去，直到遍历栈为空；左或右孩子为空时不需要压入栈；
    - 最后将打印栈中的元素全部弹出即可；
    - 核心：利用栈结构，先访问当前节点，再把左孩子放入栈，再把右孩子放入栈，根据栈先进后出的特性访问的顺序便为：当前节点、右子树、左子树。再将这访问结果利用栈倒序打印即最终打印顺序为：左子树、右子树、当前节点。 
- **后序遍历非递归实现2：**
  - 模拟系统栈，对每一个节点访问三次；
  - 创建一个新的结构，存放节点及访问该节点的次数；
  - 利用栈存放访问的节点，初始将“根节点-第一次访问”放入栈；
  - 将栈顶元素弹出，直到栈为空；
  - 如果该节点“第一次访问”，将“该节点-第二次访问”放入栈；如果该节点左孩子不为空，将“该节点左孩子-第一次访问”放入栈中；
  - 如果该节点“第二次访问”，将“该节点-第三次访问”放入栈；如果该节点右孩子不为空，将“该节点右孩子-第一次访问”放入栈中；
  - 如果该节点“第三次访问”，打印该元素。
- **层序遍历：**
  - 逐层访问二叉树节点元素，利用队列来存放元素；初始将根节点放入队列；
  - 将队首元素取出，访问该节点元素，并将该节点的左孩子放入队列、再将该节点右孩子放入队列；如此循环下去，直到队列为空；
  - 左或右孩子为空时不需要入队；

**找最小值：**
- 从根节点开始，一直往左找左子树，直到左子树为空为止的节点；

**找最大值：**
- 从根节点开始，一直往右找右子树，直到右子树为空为止的节点；

**删除最小值：**
- 找到最小值节点，保存该节点的右子树，并返回作为删除节点子树的根；

**删除最大值：**
- 找到最大值节点，保存该节点的左子树，并返回作为删除节点子树的根；

**删除任意节点：**
- 删除只有左孩子的节点：保存删除节点的左子树，放在要删除节点的位置；
- 删除只有右孩子的节点：保存删除节点的右子树，放在要删除节点的位置；
- 删除左右都有孩子的节点(1962年，Hibbard提出-Hibbard Deletion)：
  - 前驱和后继：某个节点在中序遍历结果中的前后节点分别为其前驱和后继；
  - 删除左右都有孩子的节点通过找到该节点的前驱或后继来替换它；
- 设删除左右都有孩子的节点d：
  - 从节点d右子树找其后继节点s(即节点d右子树中最小值)，保存后继，并用其来替换节点d；s = min(d->right)，s是d的后继; 
  - 删除节点d的后继(即节点d右子树中最小值)，并将返回的右子树作为s的右子树；s->right = delMin(d->right)；
  - 将节点d的左子树作为节点s的左子树；s->left = d->left；
  - 删除节点d，并将节点s作为新的子树的根返回。

![16.删除二分搜索树的任意元素](https://img-blog.csdnimg.cn/2021030205201792.jpg)

**更多二叉搜索树问题：**
- 二叉搜索树的顺序性，
  - 可以用于找前驱和后继、
  - 也可以找二叉树中某个元素的floor(),ceil()；找某个数在二叉树中大于它的最小的数((ceil)和小于它的最大数(floor)；

![17.二分搜索树floor和ceil](https://img-blog.csdnimg.cn/2021030205455098.jpg)

  - 也可找排行rank-某元素的排行或select-找排行第k的元素：可通过每个节点可维护一个该节点为根的树的size；

![18.二分搜索树rank和select](https://img-blog.csdnimg.cn/20210302054502874.jpg)

![19.二分搜索树size](https://img-blog.csdnimg.cn/20210302054635292.jpg)

- 维护depth的二分搜索树

![20.二分搜索树depth](https://img-blog.csdnimg.cn/20210302054720293.jpg)

- 支持重复元素的二分搜索树，为每个元素添加数量count

![21.二分搜索树支持重复元素](https://img-blog.csdnimg.cn/20210302054801483.jpg)

## 时间复杂度分析

n为树中节点个数，h为树的深度, 在满二叉树的情况下，h = log(n+1)，以2为底的对数
- 添加add:      O(h)  -> 平均为：O(logn) ，最差情况：O(n)
- 查找contains：O(h)  -> 平均为：O(logn) ，最差情况：O(n)
- 删除remove：  O(h)  -> 平均为：O(logn) ，最差情况：O(n)

**二叉搜索树性能问题：**
- 二分搜索树中数据以满二叉树存放时，在二叉搜索树中进行增、删、查操作达到最理想的情况，时间复杂度为 O(logn); 
- 但当数据以顺序的方式添加进二分搜索树时，二分搜索树会退化成链表，增、删、查操作达到最差情况，时间复杂度为 O(n)。
- 解决方法：平衡二叉树，避免二叉树退化成链表。

## 集合与映射

二叉搜索树不通盛放重复元素，非常好的实现“集合”的底层数据结构。

**集合：**
- 集合中不能盛放重复元素；
- 典型应用：
  - 客户统计
  - 词汇量统计
- 使用二叉搜索树实现集合。

**Set<E>:**
- void add(E e); 添加元素
- void remove(E e);  删除元素
- boolean contains(E e); 是否存在某元素
- int getSize();  获取集合中元素数量
- boolean isEmpty(); 是否为空


**映射**：
- 存储（键，值）数据对的数据结构(key, value)
- 根据键(key)，寻找值 (value)
- 非常容易使用二叉搜索树实现
- 应用：
  - 字典： 单词 - 解释
  - 名册： 身份证 - 人
  - 车辆管理： 车牌号 - 车
  - 词频统计： 单词 - 频率

**使用二叉搜索树实现映射节点结构：**
```
calss Node{
    K key;
    V value;
    Node left;
    Node right;
}
```

**MAP<K,V>:**
- void add(K k, V v);    添加元素
- V remove(K k);         删除元素
- boolean contains(K k); 是否包含元素
- V get(K k);            根据键获取元素
- void set(K k, V v);    根据键设置元素
- int getSize();         获取映射键值对数量
- boolean isEmpty();     是否为空


## 实现

1. 二叉搜索树实现
2. Leetcode 804.Unique Morse Code Words 唯一摩尔斯密码词
3. Leetcode 144.Binary Tree Preorder Traversal 二叉树前序遍历
4. Leetcode 94.binary-tree-inorder-traversal 二叉树中序遍历
5. Leetcode 145.binary-tree-postorder-traversal 二叉树后序遍历
6. 基于二叉搜索树实现集合
7. 基于二叉搜索树实现映射
7. Leetcode 349.Intersection of Two Arrays  两个数组的交集
7. Leetcode 350.Intersection of Two Arrays II  两个数组的交集

### 二叉搜索树实现

**BSTree:**
- public void add(E e);     向二分搜索树中添加新的元素e
- private Node add(Node node, E e);  向以node为根的二分搜索树中插入元素e，递归算法；返回插入新节点后二分搜索树的根；
- public boolean contains(E e); 看二分搜索树中是否包含元素e
- private boolean contains(Node node, E e); 看以node为根的二分搜索树中是否包含元素e, 递归算法
- public void preOrder();   二分搜索树的前序遍历
- private void preOrder(Node node);   前序遍历以node为根的二分搜索树，递归算法
- public void inOrder();    二分搜索树的中序遍历
- private void inOrder(Node node);    中序遍历以node为根的二分搜索树，递归算法
- public void postOrder();  二分搜索树的后序遍历
- private void postOrder(Node node);  后序遍历以node为根的二分搜索树，递归算法
- public void preOrderNR(); 二分搜索树非递归前序遍历
- public void preOrderSystemNR(); 二分搜索树非递归前序遍历(模拟系统栈)
- public void inOrderNR();  二分搜索树非递归中序遍历
- public void inOrderSystemNR();  二分搜索树非递归中序遍历(模拟系统栈)
- public void postOrderNR();    二分搜索树非递归后序遍历
- public void postOrderSystemNR();    二分搜索树非递归后序遍历(模拟系统栈)
- public void levelOrder(); 二分搜索树的层序遍历
- public E minimum();   寻找二分搜索树的最小元素
- private Node minimum(Node node);  返回以node为根的二分搜索树的最小值所在的节点
- public E maximum();   寻找二分搜索树的最大元素
- private Node maximum(Node node);  返回以node为根的二分搜索树的最大值所在的节点
- public E removeMin(); 从二分搜索树中删除最小值所在节点，返回最小值
- private Node removeMin(Node node); 删除掉以node为根的二分搜索树中的最小节点；返回删除节点后新的二分搜索树的根
- public E removeMax(); 从二分搜索树中删除最大值所在节点，返回最大值
- private Node removeMax(Node node); 删除掉以node为根的二分搜索树中的最大节点；返回删除节点后新的二分搜索树的根
- public void remove(E e);  从二分搜索树中删除元素为e的节点
- private Node remove(Node node, E e);  删除以node为根的二分搜索树中值为e的节点，递归算法；返回删除节点后新的二分搜索树的根

**参考代码：** [com.chen.data.struct.bst.BSTree](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### Leetcode 804.Unique Morse Code Words 唯一摩尔斯密码词

https://leetcode-cn.com/problems/unique-morse-code-words/

国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。

为了方便，所有26个英文字母对应摩尔斯密码表如下：

[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作单词翻译。

返回我们可以获得所有词不同单词翻译的数量。

**参考代码：** [com.chen.data.struct.bst.Leetcode804UniqueMorseCodeWords](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### Leetcode 144.Binary Tree Preorder Traversal 二叉树前序遍历

https://leetcode-cn.com/problems/binary-tree-preorder-traversal/

**参考代码：** [com.chen.data.struct.bst.Leetcode144BinaryTreePreorderTraversal](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### Leetcode 94.binary-tree-inorder-traversal 二叉树中序遍历

https://leetcode-cn.com/problems/binary-tree-inorder-traversal/

**参考代码：** [com.chen.data.struct.bst.Leetcode94BinaryTreeInorderTraversal](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### Leetcode 145.binary-tree-postorder-traversal 二叉树后序遍历

https://leetcode-cn.com/problems/binary-tree-postorder-traversal/

**参考代码：** [com.chen.data.struct.bst.Leetcode145BinaryTreePostorderTraversal](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### 基于二叉搜索树实现集合

**参考代码：** [com.chen.data.struct.bst.BSTSet](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### 基于二叉搜索树实现映射

**参考代码：** [com.chen.data.struct.bst.BSTMap](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

### Leetcode 349.Intersection of Two Arrays  两个数组的交集

https://leetcode-cn.com/problems/intersection-of-two-arrays/

给定两个数组，编写一个函数来计算它们的交集。

示例 1：
- 输入：nums1 = [1,2,2,1], nums2 = [2,2]
- 输出：[2]

示例 2：
- 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
- 输出：[9,4]

**参考代码：** [com.chen.data.struct.bst.Leetcode349IntersectionOfTwoArrays](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)


### Leetcode 350.Intersection of Two Arrays II  两个数组的交集

https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/

给定两个数组，编写一个函数来计算它们的交集。

示例 1：
- 输入：nums1 = [1,2,2,1], nums2 = [2,2]
- 输出：[2,2]

示例 2:
- 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
- 输出：[4,9]

**参考代码：** [com.chen.data.struct.bst.Leetcode350IntersectionOfTwoArrays](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/bst)

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)

## 参考

Leetcode

刘宇波《玩转数据结构》课程

左程云 《牛客网 算法初级》 课程