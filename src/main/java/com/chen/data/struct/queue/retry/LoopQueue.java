package com.chen.data.struct.queue.retry;

import com.chen.data.struct.queue.Queue;

/**
 * @author: Chentian
 * @date: Created in 2021/2/20 20:40
 * @desc 循环队列
 *
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size ;

    public LoopQueue(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("init fail! capacity must be > 0");
        }
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(16);
    }

    @Override
    public void enqueue(E e) {
        if(size == data.length){
            resize(data.length * 2);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            return null;
        }
        E ret = data[front];
        size--;
        front = (front+1)%data.length;

        if(size < data.length/4 && data.length/2 !=0){
            resize(data.length/2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            return null;
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("LoopQueue capacity = ")
                .append(data.length).append(" size = ").append(size);
        sbr.append(" front [");
        for (int i = 0 ; i < size ; i++){
            int index = (front + i) % data.length;
            sbr.append(data[index]);
            if(i != size-1){
                sbr.append(",");
            }
        }
        sbr.append("] tail");
        return sbr.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0 ; i < size; i++){
            int index = (front + i) % data.length;
            newData[i] = data[index];
        }
        data = newData;
        front = 0;
        tail = size;
    }
}
