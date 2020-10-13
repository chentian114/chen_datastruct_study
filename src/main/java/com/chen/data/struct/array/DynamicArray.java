package com.chen.data.struct.array;

import java.util.Arrays;

/**
 * @desc
 * @Author Chentian
 * @date 2020/10/13
 */
public class DynamicArray<E> implements Array<E>{

    private static final int DEFAULT_CAPACITY = 16 ;
    /** 元素存放数组 */
    private E[] data;
    /** 数组中元素存放数量 */
    private int size;

    public DynamicArray(){
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public DynamicArray(int capacity){
        if(capacity <= 0 ){
            throw new IllegalArgumentException("DynamicArray init failed. Capacity is illegal.");
        }
        data = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Index is illegal.");
        }

        if(size == data.length){
            resize(data.length * 2);
        }

        for (int i = size-1 ; i >= index ; i--){
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        add(size,e);
    }

    @Override
    public void addFirst(E e) {
        add(0,e);
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    @Override
    public int find(E e) {
        for (int i = 0 ; i < size ; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E e) {
        return find(e) != -1;
    }

    @Override
    public void set(int index, E e) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        E e = data[index];

        for (int i=index+1 ; i < size ; i++ ){
            data[i-1] = data[i];
        }
        data[size-1] = null;
        size--;

        if(size == data.length/4 && data.length/2 != 0 ){
            resize(data.length/2);
        }
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
        int index = find(e);
        if(index == -1){
            return;
        }
        remove(index);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d , data = ", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity){
        if(newCapacity <= 0){
            return;
        }

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

}
