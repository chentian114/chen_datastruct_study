# 数据结构之字典树Trie剖析

[toc]

## 概述

**字典树(Trie)：**
- 又称前缀树，是 N 叉树的特殊形式。
- 通常来说，一个前缀树是用来存储字符串的。
- 前缀树的每一个节点代表一个字符串（前缀）。
- 每一个节点会有多个子节点，通往不同子节点的路径上有着不同的字符。
- 子节点代表的字符串是由节点本身的原始字符串 ，以及通往该子节点路径上所有的字符组成的。

![1.前缀树](https://img-blog.csdnimg.cn/20210310171837744.png "字典树")

**例如，**
- 我们从根节点开始，选择第二条路径 'b'，然后选择它的第一个子节点 'a'，接下来继续选择子节点 'd'，我们最终会到达叶节点 "bad"。
- 节点的值是由从根节点开始，与其经过的路径中的字符按顺序形成的。

**我们再来看这个例子。例如，**
- 以节点 "b" 为根的子树中的节点表示的字符串，都具有共同的前缀 "b"。
- 反之亦然，具有公共前缀 "b" 的字符串，全部位于以 "b" 为根的子树中，
- 并且具有不同前缀的字符串来自不同的分支。

值得注意的是，根节点表示 空字符串 。

前缀树的一个重要的特性是，节点所有的后代都与该节点相关的字符串有着共同的前缀。这就是前缀树名称的由来。

**字典树结构：**
- 根节点不包含字符，除根节点外每一个节点都只包含一个字符；
- 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串；
- 每个节点的所有子节点包含的字符都不相同。

**应用：**
- 典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
  - 它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。
- 通讯录
- 自动补全，拼写检查等等。

## 原理

![字典树](https://img-blog.csdnimg.cn/20200512060652741.jpg "字典树")

**Trie树存储结构**
```java
private class Node{
    boolean isWord;
    TreeMap<Character, Node> next;
}
Node root = new Node();
int size = 0;
```

**添加一个单词：**
- 遍历单词的每一个字符，从Trie树的root节点开始，不断将下一个字符添加为前一个字符节点的子节点；
- 当所有字符都添加完成后，判断是否已经存在改单词，如果不存在size++;

**查询一个单词：**
- 遍历单词的每一个字符，从Trie树的root节点开始，不断寻找节点的子节点中是否存在这一个字符，
- 直到单词的所有字符都遍历完成且最后一个节点isWord为true，返回存在该单词；否则返回false；

**前缀搜索：**
- 搜索是否有某一个前缀的单词，
- 遍历单词的每一个字符，从Trie树的root节点开始，不断寻找节点的子节点中是否存在这一个字符，
- 直到单词的所有字符都遍历完成，返回存在该前缀的单词，否则返回fasle；

**删除操作：**
- 从root节点开始，遍历要删除的单词的每一个字符，如果最后一个字符的节点还有叶子节点，将isWord置为fasle即可；
- 否则递归向上删除没有其它子节点的节点；

**更多Trie的问题：**
- 压缩字典树
  - Trie的局限性：最大问题是空间；可以采用压缩字典树
  - 节省了一定空间，但维护成本会提高；
  
![压缩字典树](https://img-blog.csdnimg.cn/20210310173002275.jpg "压缩字典树")

- Ternary Search Trie 三分搜索树，减少了空间，牺牲了一定时间；

![三分搜索树](https://img-blog.csdnimg.cn/2021031017302085.jpg "三分搜索树")

- 后缀树；

**更多字符串问题：**
- 子串查询：KMP Boyer-Moore Rabin-Karp
- 文件压缩
- 模式匹配

## 时间复杂度分析

Trie 查询每个条目的时间复杂度，和字典中一共有多少条目无关！

时间复杂度为O(w) w为查询单词的长度！

## 实现

1. 实现Trie树
2. Leetcode 208.实现Trie
3. Leetcode 211.添加和搜索单词
4. Leetcode 677.键值映射

### 实现Trie树

- public int getSize() 获得Trie中存储的单词数量
- public void add(String word) 向Trie中添加一个新的单词word
- public boolean contains(String word) 查询单词word是否在Trie中
- public void remove(String word) 删除单词

**参考代码：** [com.chen.data.struct.trie.Trie](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/trie)

### Leetcode 208. Implement Trie (Prefix Tree)

https://leetcode-cn.com/problems/implement-trie-prefix-tree/

实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

**参考代码：** [com.chen.data.struct.trie.Trie](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/trie)

### Leetcode 211. Add and Search Word - Data structure design

请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

实现词典类 WordDictionary ：
- WordDictionary() 初始化词典对象
- void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
- bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个. 都可以表示任何一个字母。

**参考代码：** [com.chen.data.struct.trie.Leetcode211WordDictionary](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/trie)

### Leetcode 677.键值映射

![5.Trie和映射](https://img-blog.csdnimg.cn/20210310173448413.jpg)

https://leetcode-cn.com/problems/map-sum-pairs/

实现一个 MapSum 类，支持两个方法，insert 和 sum：
- MapSum() 初始化 MapSum 对象
- void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
- int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。

**参考代码：** [com.chen.data.struct.trie.Leetcode677MapSum](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/trie)

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)


## 参考

Leetcode https://leetcode-cn.com/leetbook/detail/trie/

刘宇波《玩转数据结构》课程