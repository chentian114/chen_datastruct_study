package com.chen.data.struct.queue;

public interface Queue<E> {
    /** 入队 */
    void enqueue(E e);
    /** 出队 */
    E dequeue();
    /** 获取队首元素 */
    E getFront();
    /** 获取队列中元素数量 */
    int getSize();
    /** 是否为空 */
    boolean isEmpty();
}
