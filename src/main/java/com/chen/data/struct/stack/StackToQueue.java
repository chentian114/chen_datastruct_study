package com.chen.data.struct.stack;

import java.util.Stack;
/**
 * @author: Chentian
 * @date: Created in 2020/12/3 2:58
 * @desc 使用栈实现队列
 */
public class StackToQueue<E> {

    private Stack<E> dataIn = new Stack<>();
    private Stack<E> dataOut = new Stack<>();

    //入队
    public void enqueue(E e){
        dataIn.push(e);
    }

    //出队
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }

        if(!dataOut.isEmpty()){
            return dataOut.pop();
        }

        while (!dataIn.isEmpty()){
            dataOut.push(dataIn.pop());
        }
        return dataOut.pop();
    }

    public boolean isEmpty(){
        return dataOut.isEmpty() && dataIn.isEmpty();
    }

    public int size(){
        int size1 = dataIn.isEmpty() ? 0 : dataIn.size();
        int size2 = dataOut.isEmpty() ? 0 : dataOut.size();
        return size1 + size2;
    }



    public static void main(String[] args) {
        StackToQueue<Integer> queue = new StackToQueue<>();
        for (int i = 0 ; i < 10 ; i++){
            queue.enqueue(i);
        }

        for (int i = 0 ; i < 5 ; i++){
            System.out.println(queue.dequeue());
        }
        for (int i = 10 ; i < 15 ; i++) {
            queue.enqueue(i);
            System.out.println(queue.dequeue());
        }

        int size = queue.size();
        System.out.println("size="+queue.size());

        for (int i = 0 ; i < size ; i ++){
            System.out.println(queue.dequeue());
        }

    }

}
