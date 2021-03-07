package com.chen.data.struct.heap;

public interface Heap<E> {

    int DEFAULT_CAPACITY = 16;

    /** 返回堆中的元素个数*/
    int size();
    /** 返回一个布尔值, 表示堆中是否为空 */
    boolean isEmpty();
    /** 向堆中添加元素 */
    void heapPush(E e);
    /** 取出最大堆中最大元素或最小堆中最小元素 */
    E heapPop();
    /** 查看堆顶元素 */
    E peek();
    /** 替换堆顶元素 */
    E replace(E e);

}
