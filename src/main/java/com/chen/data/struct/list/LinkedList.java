package com.chen.data.struct.list;

/**
 * @desc
 * @Author Chentian
 * @date 2020/10/30
 */
public class LinkedList<E> implements List<E>{

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

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size){
            throw new IllegalArgumentException("index is error!");
        }
        Node newNode = new Node(e,null);

        //需要对head节点做特殊处理
        if(index == 0){
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        Node prev = head;
        for( int i = 0 ; i < index-1 ; i++){
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
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        Node cur = head;
        for (int i = 0 ; i < index ; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }
        Node cur = head;
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
        Node cur = head;
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
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("index is error!");
        }

        //需要对head节点做特殊处理
        if( index == 0 ){
            E e = head.e;
            head = head.next;
            size -- ;
            return e;
        }

        Node prev = head;
        for (int i = 0 ; i < index-1 ; i++){
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

        if(head.e.equals(e)){
            head = head.next;
            size--;
            return;
        }

        Node prev = head;
        for (int i = 0 ; i < size-1 ; i++){
            if(prev.next.e.equals(e)){
                prev.next.next = prev.next;
                size--;
                return;
            }
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
        Node cur = head;
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
