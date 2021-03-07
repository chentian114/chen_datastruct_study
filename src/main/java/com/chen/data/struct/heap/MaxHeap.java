package com.chen.data.struct.heap;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/7
 */
public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private int size;
    private Object[] data;

    public MaxHeap(){
        size = 0 ;
        data = new Object[DEFAULT_CAPACITY];
    }

    public MaxHeap(E[] data){
        heapify(data);
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
            resize(data.length * 2);
        }
        data[size] = e;
        size++;
        siftUp(getLastIndex());
    }

    /**
     * 对新添加进堆中元素执行上浮操作，确保堆的特性不被破坏
     */
    private void siftUp(int index) {
        if(index == 0){
            return;
        }

        int parentIndex = parent(index);
        if(((E)data[parentIndex]).compareTo((E)data[index]) >= 0){
            return;
        }

        swap(parentIndex,index);
        siftUp(parentIndex);
    }


    @Override
    public E heapPop() {
        if(isEmpty()){
            return null;
        }

        E top = (E)data[0];
        int lastIndex = getLastIndex();
        if(lastIndex > 0 ){
            data[0] = data[lastIndex];
            data[lastIndex] = null;
            size -- ;
            siftDown(0);
        }else {
            data[lastIndex] = null;
            size -- ;
        }

        if(size < data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return top;
    }

    /**
     * 对堆中元素执行下沉操作，确保堆的特性不被破坏
     */
    private void siftDown(int index) {
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        int lastIndex = getLastIndex();

        if(leftIndex > lastIndex){
            return;
        }
        int maxChildIndex = leftIndex;
        if(rightIndex <= lastIndex){
            maxChildIndex = ((E)data[leftIndex]).compareTo((E)data[rightIndex]) > 0 ? leftIndex : rightIndex;
        }

        if(((E)data[index]).compareTo((E)data[maxChildIndex]) >= 0){
            return;
        }

        swap(index,maxChildIndex);
        siftDown(maxChildIndex);
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
        StringBuilder sbr = new StringBuilder("MaxHeap size = ").append(size);
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


    /**
     * 获取堆中最后一个元素的索引
     */
    private int getLastIndex() {
        return size - 1;
    }

    /**
     * 获取父节点索引
     */
    private int parent(int index){
        return (index - 1)/2;
    }

    /**
     * 获取左孩子节点索引
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 获取右孩子节点索引
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 将一个数组整理成堆
     */
    private void heapify(E[] data) {
        if(data == null){
            return;
        }

        this.size = data.length;
        this.data = data;

        int firstIndex = parent(getLastIndex());
        for (int i = firstIndex ; i >= 0 ; i--){
            siftDown(i);
        }
    }

    /**
     * 对数组进行扩缩容
     */
    private void resize(int newCapacity){
        Object[] newData =  new Object[newCapacity];
        for (int i = 0 ; i < size ; i ++){
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 交换两个元素的值
     */
    private void swap(int index1, int index2) {
        Object tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }


}
