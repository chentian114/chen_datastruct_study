package com.chen.data.struct.stack;

import com.chen.data.struct.array.Array;
import com.chen.data.struct.array.DynamicArray;

/**
 * @desc 使用动态数组实现栈
 * @Author Chentian
 * @date 2020/10/22
 */
public class ArrayStack<E> implements Stack<E>{

    public static final int DEFAULT_CAPACITY = 16;
    private Array<E> data;

    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity){
        data = new DynamicArray<>(capacity);
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.get(data.getSize()-1);
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
        StringBuilder sbr = new StringBuilder("stack size:").append(getSize());
        sbr.append(" data: [");
        for (int i = 0 ; i < getSize() ; i++){
            sbr.append(data.get(i));
            if(i != getSize() -1 ){
                sbr.append(",");
            }
        }
        sbr.append("] top");
        return sbr.toString();
    }
}
