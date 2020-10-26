package com.chen.data.struct.stack;

public interface Stack<E> {
    /** 入栈 */
    void push(E e);
    /** 出栈 */
    E pop();
    /** 查看栈顶元素 */
    E peek();
    /** 查看栈内元素数量 */
    int getSize();
    /** 是否为空栈 */
    boolean isEmpty();
}
