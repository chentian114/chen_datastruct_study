package com.chen.data.struct.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Chentian
 * @date: Created in 2020/12/3 3:14
 * @desc 使用队列实现栈
 */
public class QueueToStack <E>{

    private Queue<E> queue1 = new LinkedList<>();
    private Queue<E> queue2 = new LinkedList<>();

    //入队
    public void push(E e){
        if(queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(e);
        }
        else if(queue1.isEmpty()){
            queue2.add(e);
        }
        else {
            queue1.add(e);
        }
    }

    public E pop(){
        if(isEmpty()){
            throw new IllegalArgumentException("stack is empty!");
        }
        if(queue1.isEmpty()){
            while (queue2.size() > 1){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }else{
            while (queue1.size() > 1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }
    }

    public boolean isEmpty(){
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public int size(){
        int size1 = queue1.isEmpty() ? 0 : queue1.size();
        int size2 = queue2.isEmpty() ? 0 : queue2.size();
        return size1 + size2;
    }

    public static void main(String[] args) {
        QueueToStack<Integer> stack = new QueueToStack<>();
        for (int i = 0 ; i < 10 ; i++){
            stack.push(i);
        }
        for (int i = 0 ; i < 5 ; i++){
            System.out.println(stack.pop());
        }
        System.out.println("size="+stack.size());
        for (int i = 10 ; i< 15 ; i++){
            stack.push(i);
            System.out.println(stack.pop());
        }
        int size = stack.size();
        for (int i = 0 ; i < size ; i++){
            System.out.println(stack.pop());
        }


    }
}
