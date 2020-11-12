package com.chen.data.struct.list;

import com.chen.data.struct.queue.Queue;

/**
 * @desc
 * @Author Chentian
 * @date 2020/11/2
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node{
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private int size ;
    private Node head;
    private Node tail;

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e,null);
            head = tail;
        }else {
            tail.next = new Node(e,null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }

        Node delNode = head;

        head = head.next;
        if(head == null){
            tail = null;
        }
        size--;
        delNode.next = null;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty!");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("queue size=").append(size).append(" head: [");
        Node cur = head;
        for (int i = 0 ; i < size ; i++){
            sbr.append(cur.e);
            if(i != size-1){
                sbr.append("->");
            }
            cur = cur.next;
        }
        sbr.append("] tail");

        return sbr.toString();
    }


    public static void main(String[] args) {


    }
}
