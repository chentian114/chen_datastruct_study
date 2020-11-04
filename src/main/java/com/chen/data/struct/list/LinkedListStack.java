package com.chen.data.struct.list;

import com.chen.data.struct.stack.Stack;

/**
 * @desc
 * @Author Chentian
 * @date 2020/11/2
 */
public class LinkedListStack<E> implements Stack<E> {

    private List<E> list = new LinkedList<>();
//    private List<E> list = new DummyHeadList<>();

    @Override
    public void push(E e) {
        list.addLast(e);
    }

    @Override
    public E pop() {
        return list.removeLast();
    }

    @Override
    public E peek() {
        return list.getLast();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
