package com.chen.data.struct.heap;

/**
 * @desc 最小堆
 * @Author Chentian
 * @date 2021/3/7
 */
public class MinHeap<E extends Comparable<E>> implements Heap<E>{

    private int size;
    private Object[] data;

    public MinHeap(){
        size = 0;
        data = new Object[DEFAULT_CAPACITY];
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
    public void heapPush(E e) {
        if(size == data.length){
            resize(2*data.length);
        }

        data[size] = e;
        size++;
        siftUp(getLastIndex());
    }

    private void siftUp(int index) {
        if(index == 0){
            return;
        }

        int parentIndex = parent(index);
        E parent = (E)data[parentIndex];
        if(parent.compareTo((E)data[index]) <= 0){
            return;
        }

        swap(index, parentIndex);
        siftUp(parentIndex);
    }


    @Override
    public E heapPop() {
        if(isEmpty()){
            return null;
        }
        E top = (E) data[0];
        int lastIndex = getLastIndex();
        if(lastIndex > 0){
            data[0] = data[lastIndex];
            data[lastIndex] = null;
            size--;
            siftDown(0);
        }
        else {
            data[0] = null;
            size--;
        }
        return top;
    }

    private void siftDown(int index) {
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        int lastIndex = getLastIndex();

        if(leftIndex > lastIndex){
            return;
        }
        int minIndex = leftIndex;
        if(rightIndex <= lastIndex){
            E leftChild = (E)data[leftIndex];
            minIndex = leftChild.compareTo((E)data[rightIndex]) <= 0 ? leftIndex : rightIndex;
        }

        E e = (E)data[index];
        if(e.compareTo((E)data[minIndex]) <= 0){
            return;
        }

        swap(index,minIndex);
        siftDown(minIndex);
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return (E)data[0];
    }

    @Override
    public E replace(E e) {
        if(isEmpty()){
            throw new IllegalArgumentException("heap is empty!");
        }
        E top = (E)data[0];
        data[0] = e;
        siftDown(0);
        return top;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("MinHeap size = ").append(size);
        sbr.append(" data:[");
        for (int i = 0 ; i < size; i++){
            sbr.append(data[i]);
            if(i != size-1){
                sbr.append(",");
            }
        }
        sbr.append("]");
        return sbr.toString();
    }

    private void swap(int index1 , int index2){
        Object tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }
    private int parent(int index){
        return (index - 1)/2;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    private int getLastIndex() {
        return size-1;
    }

    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        for (int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}
