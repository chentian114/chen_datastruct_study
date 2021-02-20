package com.chen.data.struct.stack.retry;

import com.chen.data.struct.array.DynamicArray;
import com.chen.data.struct.stack.Stack;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/20
 */
public class ArrayStack<E> implements Stack<E> {

    private DynamicArray<E> array = new DynamicArray<>();

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.get(array.getSize()-1);
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

        StringBuilder sbr = new StringBuilder("stack capacity = ").append(array.getCapacity()).append(" size = ").append(array.getSize());
        sbr.append(" data [");
        for (int i = 0 ; i < array.getSize() ; i++){
            sbr.append(array.get(i));
            if(i != array.getSize()-1){
                sbr.append(",");
            }
        }
        sbr.append("] top");
        return sbr.toString();
    }
}
