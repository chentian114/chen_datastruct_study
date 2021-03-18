package com.chen.data.struct.avl;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/16
 */
public interface Tree<E> {
    /** 树中节点数量 */
    int size();

    boolean isEmpty();

    /** 往树中添加元素 */
    void add(E value);

    /** 检查树中是否包含某个元素 */
    boolean contains(E value);

    /** 获取树中最小的元素 */
    E minimum();

    /** 获取树中最大的元素 */
    E maximum();

    /** 删除树中的元素 */
    void remove(E e);
}
