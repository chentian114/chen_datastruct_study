package com.chen.data.struct.queue.retry;

import com.chen.data.struct.array.DynamicArray;
import com.chen.data.struct.queue.Queue;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/20
 */
public class ArrayQueue<E> implements Queue<E> {

    private DynamicArray<E> array = new DynamicArray<>();

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("queue capacity = ").append(array.getCapacity()).append(" size = ").append(array.getSize());
        sbr.append(" data front [");
        for (int i = 0 ; i < array.getSize(); i++){
            sbr.append(array.get(i));
            if(i != array.getSize() -1){
                sbr.append(",");
            }
        }
        sbr.append("] tail");
        return sbr.toString();
    }
}
