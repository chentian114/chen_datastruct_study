package com.chen.data.struct.array;

public interface Array<E> {

    /** 判断是否为空 */
    boolean isEmpty();
    /** 获取数组中元素个数 */
    int getSize();
    /** 获取当前数组容量 */
    int getCapacity();

    /** 在指定索引位置添加元素 */
    void add(int index, E e);
    /** 在头部添加元素 */
    void addLast(E e);
    /** 在尾部添加元素 */
    void addFirst(E e);

    /** 获取索引位置元素 */
    E get(int index);
    /** 根据指定元素查找索引 */
    int find(E e);
    /** 查找数组中是否包含指定元素 */
    boolean contains(E e);

    /** 修改指定索引位置元素 */
    void set(int index, E e);

    /** 删除指定索引位置元素 */
    E remove(int index);
    /** 删除第一个元素 */
    E removeFirst();
    /** 删除最后一个元素 */
    E removeLast();
    /** 删除指定元素 */
    void removeElement(E e);

}
