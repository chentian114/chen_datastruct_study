package com.chen.data.struct.queue;

/**
 * @desc 固定容量循环队列
 * @Author Chentian
 * @date 2020/10/22
 */
public class LoopQueue<E> implements Queue<E>{

    public static final int DEFAULT_CAPACITY = 16;
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(){
        this(DEFAULT_CAPACITY);
    }

    public LoopQueue(int capacity){
        if(capacity < 1){
            capacity = DEFAULT_CAPACITY;
        }
        data = (E[]) new Object[capacity];
    }

    private boolean isFull(){
        return size == data.length;
    }

    @Override
    public void enqueue(E e) {
        if(isFull()){
            throw new IllegalArgumentException("queue is full!");
        }

        data[tail] = e;
        size++;
        tail = (tail + 1) % data.length ;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }

        E e = data[front];
        data[front] = null;
        size--;
        front = (front + 1) % data.length;

        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
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
        StringBuilder sbr = new StringBuilder("queue size:").append(getSize()).append(data.length);
        sbr.append(" data: front [");
        for (int i = 0 ; i < size ; i++){
            int index = (front + i) % data.length;
            sbr.append(data[index]);
            if(i != size -1 ){
                sbr.append(",");
            }
        }
        sbr.append("] tail");
        return sbr.toString();
    }
}
