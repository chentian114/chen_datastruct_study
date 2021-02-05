package com.chen.data.struct.array.retry;

import com.chen.data.struct.array.Array;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/2
 */
public class DynamicArray<E> implements Array<E> {

    private E[] data ;
    private int size ;


    @SuppressWarnings("unchecked")
    public DynamicArray(int capacity){
        if(capacity <= 0 ){
            throw new IllegalArgumentException("init failed! capacity must be > 0!");
        }
        data = (E[]) new Object[capacity];
    }
    public DynamicArray(){
        this(16);
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
            throw new IllegalArgumentException("add failed! index must be >= 0 and < array element size!");
        }
        if(size == data.length){
            resize(2 * data.length);
        }
        for (int i= size - 1 ; i >= index ; i--){
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
            throw new IllegalArgumentException("get failed! index must be >= 0 and < array element size");
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
            throw new IllegalArgumentException("set failed! index must be >= 0 and < array element size");
        }
        data[index] = e;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("remove failed! index must be >= 0 and < array element size");
        }
        E ret = data[index];

        for (int i = index ; i < size-1 ; i++){
            data[i] = data[i+1];
        }
        size--;

        if(size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }

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
        int index = find(e);
        if(index == -1){
            return;
        }
        remove(index);
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("data size = ").append(size)
                .append(" capacity = ").append(data.length)
                .append(" data = [");
        for (int i = 0 ; i < size ; i++){
            sbr.append(data[i]);
            if(i != size-1){
                sbr.append(", ");
            }
        }
        sbr.append("]");
        return sbr.toString();
    }
}
