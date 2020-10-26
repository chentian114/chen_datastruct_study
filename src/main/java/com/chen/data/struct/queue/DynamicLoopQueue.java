package com.chen.data.struct.queue;

/**
 * @desc 动态容量循环队列
 * @Author Chentian
 * @date 2020/10/22
 */
public class DynamicLoopQueue<E> implements Queue<E> {

    public static final int DEFAULT_CAPACITY = 16;
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public DynamicLoopQueue(){
        this(DEFAULT_CAPACITY);
    }

    public DynamicLoopQueue(int capacity){
        if(capacity < 1){
            capacity = DEFAULT_CAPACITY;
        }
        // 多留一个空间，用于区分队列空和满的情况
        data = (E[])new Object[capacity+1];
    }

    private boolean isFull(){
        return front == (tail + 1) % data.length;
    }

    private int getCapacity(){
        return data.length - 1 ;
    }

    @Override
    public void enqueue(E e) {
        if(isFull()){
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail +1)%data.length;
        size++;
    }


    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }

        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if(size < (getCapacity() / 4) && (getCapacity() / 2) != 0){
            resize(getCapacity()/2);
        }
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
        return front == tail;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity+1];

        for (int i = 0 ; i < size ; i++ ){
            int index = (front + i)%data.length;
            newData[i] = data[index];
        }
        front = 0 ;
        tail = size ;
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder(String.format("queue size= %d capacity= %d",getSize(),getCapacity()));
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
