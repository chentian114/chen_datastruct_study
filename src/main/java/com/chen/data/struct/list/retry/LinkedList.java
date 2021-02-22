package com.chen.data.struct.list.retry;

import com.chen.data.struct.list.List;

/**
 * @author: Chentian
 * @date: Created in 2021/2/23 6:38
 * @desc
 */
public class LinkedList<E> implements List<E> {
    class Node{
        private E val;
        private Node next;
        private Node(E val, Node next){
            this.val = val;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size){
            throw new IllegalArgumentException("add failed! index error!");
        }
        if(index == 0){
            head = new Node(e,head);
        }else {
            Node prevNode = head;
            for (int i = 0 ; i < index-1; i++){
                prevNode = prevNode.next;
            }
            prevNode.next = new Node(e,prevNode.next);
        }
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
        Node curNode = head;
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
        Node curNode = head;
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
        Node curNode = head;
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
        E ret;
        if(index == 0){
            ret = head.val;
            head = head.next;
        }else {
            Node prevNode = head;
            for (int i = 0 ; i < index -1 ; i++){
                prevNode = prevNode.next;
            }
            ret = prevNode.next.val;
            prevNode.next = prevNode.next.next;
        }
        size--;
        return ret;
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
        Node curNode = head;
        for (int i = 0 ; i < size; i++){
            if(curNode.val.equals(e)){
                remove(i);
                return;
            }
            curNode = curNode.next;
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
        Node curNode = head;
        for (int i = 0 ; i < size ; i++){
            sbr.append(curNode.val).append("->");
            curNode = curNode.next;
        }
        sbr.append("NULL]");
        return sbr.toString();
    }
}
