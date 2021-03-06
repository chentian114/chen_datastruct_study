package com.chen.data.struct.bst;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/6
 */
public interface Set<E> {
    /** 添加元素 */
    void add(E e);
    /** 删除元素 */
    void remove(E e);
    /** 是否存在某元素*/
    boolean contains(E e);
    /** 获取集合中元素数量 */
    int getSize();
    /** 是否为空 */
    boolean isEmpty();
}
