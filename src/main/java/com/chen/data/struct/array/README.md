# 数据结构之数组及动态数组剖析

[toc]

## 概述

![1.数组](https://img-blog.csdnimg.cn/20201013113132628.png)

数组是在程序设计中，为了处理方便， 把具有相同类型的若干元素按有序的形式组织起来的一种形式。

数组会利用 **索引** 来记录每个元素在数组中的位置，且在大多数编程语言中，索引是从 **0** 算起的。我们可以根据数组中的索引，快速访问数组中的元素。事实上，这里的索引其实就是内存地址。

作为线性表的实现方式之一，数组中的元素在内存中是 **连续** 存储的，且每个元素占用相同大小的内存。

数组是非常常用的线性数据结构，其核心是：**把元素码成一排进行存放，利用索引访问**。

数组最大的优点：能够快速查询，**通过索引查询，时间复杂度为：O(1)**。

索引可以有语意；也可以没有语意；数组最好应用于“索引有语意”的情况。如从0开始进行递增编号的学号等，直接用学号与索引编号进行对应，可实现快速查找；但并非所有有语意的索引都适用于数组，例如身份证号码等长度过长，造成大量空间浪费。

数组存放元素的数量，即数组的长度，在声明数组时便需要确定好，且数组长度不可变，称为定长数组，高级编程语言一般都提供了该数据类型；

后面部分主要介绍：
- 动态数组实现的原理
- 实践编写动态数组类
- 时间复杂度分析

## 动态数组实现的原理

接下来的动态数组实现，其核心是基于Java语言支持的定长数组，进行二次封装来实现动态数组；
- 在添加元素时，检查定长数组是否已满，如果已满，自动执行扩容操作，实现动态扩容的能力；
- 在删除元素时，检查定长数组是否存在大量空闲空间，如果是，自动执行缩容操作，实现动态缩容的能力；
- 查找元素，支持以索引快速定位元素；以及提供根据元素值查找其在数组中的索引位置。

**定义：**
- size : 动态数组已存储元素的数量；
- data : 动态数组类中用于存储元素的数组；
- data.length(或 capacity ) : 动态数组类中用于存储元素的数组的容量；
- 动态数组通过resize()操作，在删除或新增操作时，根据现有数组中元素数量来自动调节数组容量大小；

**数组容量动态扩容：**
- 当添加一个新的元素时，数组已存储元素数量与数组容量相等时，对数组进行扩容操作；
  - if(size == data.length) { resize(2*data.length); }
- 扩容操作：创建一个现有数组容量2倍的新数组，并将原数组中所有元素复制到新数组中；
- 将动态数组类中data的引用指向新数组，完成数组的扩容；

**数组容量动态缩容：**
- 当删除一个数组中的元素后，数组已存储元素数量是数组容量的1/4，将数组容量缩容为原容量的1/2(且原容量的1/2不等于0)，数组存储元素数量到1/4时再缩容1/2空间，目的是预留空间，避免数组中元素数量到临界值时因元素反复增删造成数组反复执行扩缩容操作；
  - if(size == data.length/4 && data.length/2 !=0) { resize(data.length/2); }
- 缩容操作：创建一个现有数组容量1/2的新数组，并将原数组中所有元素复制到新数组中；
- 将动态数组类中data的引用指向新数组，完成数组的缩容；

**向数组末尾添加元素：**
- 检查数组空间是否已满，如果已满进行扩容操作；
- 直接在数组末尾添加元素；

**向数组头部/指定索引位置添加元素：**
- 检查数组空间是否已满，如果已满进行扩容操作；
- 将数组中从末尾到头部/指定索引位置元素全部往后移动一个位置；
- 将新的元素添加到数组头部/指定索引位置；

**删除数组头部/指定索引位置元素：**
- 将数组末尾到头部/指定索引位置的后一个位置所有元素往前移动一个位置；
- 检查数组空间容量是否需要进行缩容。

## 实践编写动态数组类

**查看数组情况方法：**
1. 判断是否为空  public boolean isEmpty()
2. 获取数组中元素个数  public int getSize()
3. 获取当前数组容量  public int getCapacity()

**添加元素方法：**
1. 在指定索引位置添加元素  public void add(int index, E e)
2. 在头部添加元素  public void addLast(E e)
3. 在尾部添加元素  public void addFirst(E e)

**查询元素方法：**
1. 获取索引位置元素  public E get(int index)
2. 根据指定元素查找索引  public int find(E e)
3. 查找数组中是否包含指定元素  public boolean contains(E e)

**修改和删除元素方法：**
1. 修改指定索引位置元素  public void set(int index, E e)
2. 删除指定索引位置元素  public E remove(int index)
3. 删除第一个元素  public E removeFirst()
4. 删除最后一个元素  public E removeLast()
5. 删除指定元素  public void removeElement(E e)

**数组扩展：**
1. 动态数组支持泛型
2. 数组扩缩容操作  private void resize(int newCapacity)

## 时间复杂度分析

常见算法时间复杂度有：O(1),O(n),O(logn),O(nlogn),O(n^2)

更准确的描述：渐进时间复杂度，用于描述n趋进于无穷的情况下，输入数据规模与消耗时间的关系；

大O描述的是算法的运行时间和输入数据之间的关系。

时间复杂度推导要点：忽略常数、不要低价项，只要高阶项；再根据具体数据样本分析；

**添加操作时间复杂度：**
- addLast(e)   O(1)
- addFirst(e)  O(n)
- add(index,e) O(n/2) = O(n)
- resize()     O(n)

**删除操作时间复杂度：**
- removeLast()   O(1)
- removeFirst()  O(n)
- remove(index)  O(n/2) = O(n)
- resize()  O(n)

在动态数组中，添加和删除操作都有可能触发resize()操作，需通过均摊复杂度分析综合分析来确定。

**addLast()均摊时间复杂度分析：**
- addLast() 当数组容量未满时，直接往数组最后一个位置添加元素即可，时间复杂度为 O(1)；
- addLast() 当数组容量已满时，添加元素会触发一个 resize() 自动扩容操作，时间复杂度为 O(n)；
- addLast() 通过均摊时间复杂度，均摊时间复杂度为：O(1)
- 均摊时间复杂度分析如下，假设：
  - 当前数组容量 capacity = 8 ，并且每一次添加操作都使用addLast，
  - 添加过程消耗时间情况为：1,1,1,1,1,1,1,1,1,8+1，9次addLast() + 1次resize()，（1-代表1次基本操作的时间消耗）
  - 9次addLast()操作，触发一次resize()，总共进行了17次基本操作，
  - 平均每次addLast()操作进行2次基本操作；
- 假设：
  - capacity = n ， n+1次addLast()，触发1次resize()，总共进行2n+1次基本操作
  - 即得出：平均每次addLast()操作进行2次基本操作；
- 相当于说：
  - resize()操作实际上并不会每一次addLast()都会执行，
  - 所以综合n+1次addLast()操作，只会触发1次resize()，
  - 我们将这1次resize()操作的时间均摊到了每一次addLast()操作中，
  - 平均，每次addLast()操作，进行了2次基本操作；
  - 这样均摊计算，addLast()时间复杂度是O(1)的。
- 采用均摊计算，比计算最坏情况更有意义；

**修改操作时间复杂度:**
- set(index,e)  O(1)

**查找操作时间复杂度：**
- get(index)  O(1)
- contains(e)  O(n)
- find(e)  O(n)

**执行resize()操作要防止复杂度震荡问题：**
- 当数组容量满后，添加一个元素后，会使用resize()进行扩容(时间复杂度为O(n))，之后又删除一个元素，若又立即执行resize()进行缩容(时间复杂度为0(n))，如此反复，会让数组的增加和删除操作时间复杂度都为O(n) ；
- 解决方法：Lazy，当数组中元素数量 size==capacity/4 时，才将capacity减半，容量减半需要注意容量不能减为0(capacity/2 != 0)；

## 参考代码

[DynamicArray.java](https://gitee.com/chentian114/chen_datastruct_study/tree/master/src/main/java/com/chen/data/struct/array)

## 相关链接

gitee地址：[https://gitee.com/chentian114/chen_datastruct_study](https://gitee.com/chentian114/chen_datastruct_study)

github地址：[https://github.com/chentian114/chen_datastruct_study](https://github.com/chentian114/chen_datastruct_study)

CSDN地址：[https://blog.csdn.net/chentian114/category_9997109.html](https://blog.csdn.net/chentian114/category_9997109.html)

## 公众号

![知行chen](https://img-blog.csdnimg.cn/20201019220227866.jpg)

## 参考

Leetcode

刘宇波《玩转数据结构》课程