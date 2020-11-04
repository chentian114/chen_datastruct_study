package com.chen.data.struct.list;

/**
 * @desc
 * @Author Chentian
 * @date 2020/11/2
 */
public class DummyHeadList<E> implements List<E>{
    private class Node{
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private int size;
    private Node dummyHead = new Node(null,null);

    @Override
    public void add(int index, E e) {
        if(index > size){
            throw new IllegalArgumentException("index is error!");
        }

        Node newNode = new Node(e,null);

        Node prev = dummyHead;
        //不需要对head节点做特殊处理
        for (int i = 0 ; i < index ; i ++){
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    @Override
    public void addFirst(E e) {
        add(0,e);
    }

    @Override
    public void addLast(E e) {
        add(size,e);
    }

    @Override
    public void set(int index, E e) {
        if(index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        Node cur = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public E get(int index) {
        if(index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        Node cur = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size-1);
    }

    @Override
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        for (int i = 0 ; i < size ; i++){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if(index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        Node prev = dummyHead;

        //不需要对head节点做特殊处理
        for (int i = 0 ; i < index ; i ++){
            prev = prev.next;
        }
        E e = prev.next.e;
        prev.next = prev.next.next;
        size--;

        return e;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size-1);
    }

    @Override
    public void removeElement(E e) {
        if(size == 0){
            return;
        }
        Node prev = dummyHead;
        for (int i = 0 ; i < size ; i ++){
            if(prev.next.e.equals(e)){
                prev.next = prev.next.next;
                size--;
                return;
            }
            prev = prev.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("list size=").append(size).append(" elements: [");
        Node cur = dummyHead.next;
        for (int i = 0 ; i < size ; i++){
            sbr.append(cur.e);
            if(i != size-1){
                sbr.append("->");
            }
            cur = cur.next;
        }
        sbr.append("]");

        return sbr.toString();
    }
}
