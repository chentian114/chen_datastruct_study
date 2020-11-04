package com.chen.data.struct.list;

public interface List <E> {

    /** 添加指定元素 */
    void add(int index,E e);

    /** 在头部添加指定元素 */
    void addFirst(E e);

    /** 在尾部添加指定元素 */
    void addLast(E e);

    /** 在头部添加指定元素 */
    void set(int index, E e);

    /** 获取链表中指定索引元素 */
    E get(int index);

    /** 获取链表中首个索引元素 */
    E getFirst();

    /** 获取链表中最后一个索引元素 */
    E getLast();

    /** 链表中是否包含指定元素 */
    boolean contains(E e);

    /** 根据索引删除指定元素 */
    E remove(int index);

    /** 删除头部元素 */
    E removeFirst();

    /** 删除尾部元素 */
    E removeLast();

    /** 删除指定元素 */
    void removeElement(E e);

    /** 获取链表容量 */
    int size();

    /** 链表是否为容 */
    boolean isEmpty();
}
