package com.chen.data.struct.list.retry;

import com.chen.data.struct.list.List;
import com.chen.data.struct.queue.Queue;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/23
 */
public class LinkedListQueue<E> implements Queue<E> {

    private List<E> list = new DummyHeadList<>();

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

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("queue size = ").append(list.size());
        sbr.append(" data front [");
        for (int i = 0 ; i < list.size() ; i++){
            sbr.append(list.get(i));
            if(i != list.size()-1){
                sbr.append(",");
            }
        }
        sbr.append("] tail");
        return sbr.toString();
    }
}
