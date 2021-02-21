package com.chen.data.struct.queue;

import com.chen.data.struct.stack.ArrayStack;
import com.chen.data.struct.stack.Stack;

/**
 * @author: Chentian
 * @date: Created in 2021/2/21 8:15
 * @desc
 */
public class StackToQueue<E> implements Queue<E>{

    private Stack<E> stackIn = new ArrayStack<>();
    private Stack<E> stackOut = new ArrayStack<>();

    @Override
    public void enqueue(E e) {
        stackIn.push(e);
    }

    @Override
    public E dequeue() {
        if(stackIn.isEmpty() && stackOut.isEmpty()){
            return null;
        }

        if(stackOut.isEmpty()){
            stackInToOut();
        }
        return stackOut.pop();
    }

    @Override
    public E getFront() {
        if(stackOut.isEmpty()){
            stackInToOut();
        }
        return stackOut.peek();
    }

    @Override
    public int getSize() {
        return stackIn.getSize() + stackOut.getSize();
    }

    @Override
    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    @Override
    public String toString() {
        stackInToOut();
        StringBuilder sbr = new StringBuilder("StackToQueue size = ").append(getSize());
        sbr.append(" data front[");
        int size = getSize();
        for (int i = 0 ; i < size ; i++){
            E val = stackOut.pop();
            sbr.append(val);
            if(i != size-1){
                sbr.append(",");
            }
            stackIn.push(val);
        }
        sbr.append("] tail");
        stackInToOut();

        return sbr.toString();
    }

    private void stackInToOut(){
        if(stackOut.isEmpty()){
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }else {
            Stack<E> tmpStack = new ArrayStack<>();
            while (!stackOut.isEmpty()){
                tmpStack.push(stackOut.pop());
            }
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
            while (!tmpStack.isEmpty()){
                stackOut.push(tmpStack.pop());
            }
        }
    }
}
