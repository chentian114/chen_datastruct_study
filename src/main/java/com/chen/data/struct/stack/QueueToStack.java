package com.chen.data.struct.stack;

import com.chen.data.struct.queue.ArrayQueue;
import com.chen.data.struct.queue.Queue;

/**
 * @author: Chentian
 * @date: Created in 2021/2/21 7:53
 * @desc 使用队列实现栈
 */
public class QueueToStack<E> implements Stack<E>{

    private Queue<E> queue1 = new ArrayQueue<>();
    private Queue<E> queue2 = new ArrayQueue<>();

    @Override
    public void push(E e) {
        if(queue1.isEmpty() && queue2.isEmpty()){
            queue1.enqueue(e);
        }
        else if(!queue1.isEmpty()){
            queue1.enqueue(e);
        }else {
            queue2.enqueue(e);
        }
    }

    @Override
    public E pop() {
        if(queue1.isEmpty() && queue2.isEmpty()){
            return null;
        }
        int size = queue1.getSize()+queue2.getSize();
        if(!queue1.isEmpty()){
            for (int i = 0 ; i < size-1 ; i++){
                queue2.enqueue(queue1.dequeue());
            }
            return queue1.dequeue();
        }else{
            for (int i = 0 ; i < size-1 ; i++){
                queue1.enqueue(queue2.dequeue());
            }
            return queue2.dequeue();
        }
    }

    @Override
    public E peek() {
        E e = pop();
        push(e);
        return e;
    }

    @Override
    public int getSize() {
        return queue1.getSize() + queue2.getSize();
    }

    @Override
    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("QueueToStack size = ")
                .append(queue1.getSize()+queue2.getSize()).append(" data[");
        int size = queue1.getSize() + queue2.getSize();
        if(!queue1.isEmpty()){
            for (int i = 0 ; i < size; i++){
                E val = queue1.dequeue();
                sbr.append(val);
                queue2.enqueue(val);
                if(i != size-1){
                    sbr.append(",");
                }
            }
        }else{
            for (int i = 0 ; i < size ; i++){
                E val = queue2.dequeue();
                sbr.append(val);
                queue1.enqueue(val);
                if(i !=size-1){
                    sbr.append(",");
                }
            }
        }
        sbr.append("] top");

        return sbr.toString();
    }
}
