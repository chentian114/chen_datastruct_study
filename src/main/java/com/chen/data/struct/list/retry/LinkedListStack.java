package com.chen.data.struct.list.retry;

import com.chen.data.struct.list.List;
import com.chen.data.struct.stack.Stack;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/23
 */
public class LinkedListStack<E> implements Stack<E> {

    private List<E> list = new DummyHeadList<>();

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("stack size = ").append(list.size());
        sbr.append(" data top[");
        for (int i = 0 ; i < list.size(); i++){
            sbr.append(list.get(i));
            if(i != list.size()-1){
                sbr.append(",");
            }
        }
        sbr.append("]");
        return sbr.toString();
    }
}
