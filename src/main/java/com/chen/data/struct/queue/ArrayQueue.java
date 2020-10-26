package com.chen.data.struct.queue;

import com.chen.data.struct.array.Array;
import com.chen.data.struct.array.DynamicArray;

/**
 * @desc 使用动态数组实现队列
 * @Author Chentian
 * @date 2020/10/22
 */
public class ArrayQueue<E> implements Queue<E>{
    public static final int DEFAULT_CAPACITY = 16;
    private Array<E> data;

    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity){
        data = new DynamicArray<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.get(0);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("queue size:").append(getSize()).append(" capacity:").append(data.getCapacity());
        sbr.append(" data: front [");
        for (int i = 0 ; i < getSize() ; i++){
            sbr.append(data.get(i));
            if(i != getSize() -1 ){
                sbr.append(",");
            }
        }
        sbr.append("] tail");
        return sbr.toString();
    }
}
