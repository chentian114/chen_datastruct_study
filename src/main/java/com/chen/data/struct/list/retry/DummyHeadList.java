package com.chen.data.struct.list.retry;

import com.chen.data.struct.list.List;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/23
 */
public class DummyHeadList<E> implements List<E> {

    private class Node{
        private E val;
        private Node next;
        private Node(E val, Node next){
            this.val = val;
            this.next = next;
        }
    }

    private Node dummyHead;
    private int size;

    public DummyHeadList(){
        dummyHead = new Node(null,null);
        size = 0 ;
    }

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size){
            throw new IllegalArgumentException("add failed! index error!");
        }

        Node prevNode = dummyHead;
        for (int i = 0 ; i < index ; i ++ ){
            prevNode = prevNode.next;
        }
        prevNode.next = new Node(e,prevNode.next);
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
            throw new IllegalArgumentException("set failed! index error!");
        }

        Node curNode = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            curNode = curNode.next;
        }
        curNode.val = e;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("get failed! index error!");
        }

        Node curNode = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            curNode = curNode.next;
        }
        return curNode.val;
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
        Node curNode = dummyHead.next;
        for (int i = 0 ; i < size ; i++){
            if(curNode.val.equals(e)){
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("remove failed! index error!");
        }
        Node prevNode = dummyHead;
        for (int i = 0 ; i < index ; i ++ ){
            prevNode = prevNode.next;
        }

        Node delNode = prevNode.next;
        prevNode.next = delNode.next;
        delNode.next = null;

        size--;
        return delNode.val;
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
        if(isEmpty()){
            return;
        }

        Node prevNode = dummyHead;
        for (int i = 0 ; i < size ; i++){
            if(prevNode.next.val.equals(e)){
                Node delNode = prevNode.next;
                prevNode.next = delNode.next;
                delNode.next = null;
                size--;
                return;
            }
            prevNode = prevNode.next;
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
        StringBuilder sbr = new StringBuilder("list size = ").append(size);
        sbr.append(" data [");
        for (Node curNode = dummyHead.next; curNode != null ; curNode = curNode.next){
            sbr.append(curNode.val).append("->");
        }
        sbr.append("NULL]");
        return sbr.toString();
    }
}
