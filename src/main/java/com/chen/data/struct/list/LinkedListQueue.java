package com.chen.data.struct.list;

import com.chen.data.struct.queue.Queue;

/**
 * @desc
 * @Author Chentian
 * @date 2020/11/2
 */
public class LinkedListQueue<E> implements Queue<E> {

    private List<E> list = new LinkedList<>();
//    private List<E> list = new DummyHeadList<>();

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    @Override
    public E getFront() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
