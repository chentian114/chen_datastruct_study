package com.chen.data.struct.segment;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2021/3/12 7:17
 * @desc 使用数组实现线段树，增加使用Lazy的批量修改操作
 */
public class SegmentTree3<E> implements SegmentTree<E>{

    private Merger<E> merger;
    private E[] data;
    private E[] tree;
    private E[] lazyData;

    public SegmentTree3(E[] data,Merger<E> merger){
        if(data == null || data.length == 0 || merger == null){
            throw new IllegalArgumentException("init error! data is empty or merger is null!");
        }
        this.data = (E[])new Object[data.length];
        for (int i = 0 ; i < data.length ; i++){
            this.data[i] = data[i];
        }
        this.lazyData = (E[])new Object[data.length];
        this.merger = merger;
        this.tree = (E[]) new Object[4 * data.length];
        buildSegmentTree(0, 0, data.length-1);
    }

    private void buildSegmentTree(int nodeIndex, int left, int right){
        if(left == right){
            tree[nodeIndex] = data[left];
            return;
        }

        int mid = left + (right - left)/2;
        int leftChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);
        buildSegmentTree(leftChild, left, mid);
        buildSegmentTree(rightChild, mid+1, right);

        tree[nodeIndex] = merger.merge(tree[leftChild],tree[rightChild]);
    }

    @Override
    public E query(int queryL, int queryR) {
        if(queryL > queryR){
            throw new IllegalArgumentException("query failed! queryL > queryR");
        }

        return query(0, 0, data.length-1, queryL, queryR);
    }

    private E query(int nodeIndex, int left, int right, int queryL, int queryR){
        if(left == queryL && right == queryR){
            if(left == right && lazyData[left] != null){
                data[left] = merger.merge(data[left],lazyData[left]);
                tree[nodeIndex] = data[left];
                lazyData[left] = null;
            }
            return tree[nodeIndex];
        }

        int mid = left + (right - left)/2;
        int lefChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);
        if(queryL > mid){
            return query(rightChild, mid +1 , right, queryL, queryR);
        }
        else if(queryR <= mid){
            return query(lefChild, left, mid , queryL, queryR);
        }
        else {
            E leftVal = query(lefChild, left, mid, queryL, mid);
            E rightVal = query(rightChild, mid + 1, right, mid + 1, queryR);
            return merger.merge(leftVal,rightVal);
        }
    }

    @Override
    public void set(int index, E e) {
        set(0, 0, data.length-1 , index, e);
    }

    private void set(int nodeIndex, int left, int right, int index, E e){
        if(left == right && left == index){
            lazyData[index] = null;
            data[index] = e;
            tree[nodeIndex] = e;
            return;
        }

        int mid = left + (right - left)/ 2;
        int leftChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);
        if(index > mid){
            set(rightChild, mid+1, right, index, e);
            if(left == mid && lazyData[left] != null){
                data[left] = merger.merge(data[left],lazyData[left]);
                tree[leftChild] = data[left];
                lazyData[left] = null;
            }
        }
        else {
            set(leftChild, left, mid, index, e);
            if(mid+1 == right && lazyData[right] != null){
                data[right] = merger.merge(data[right],lazyData[right]);
                tree[rightChild] = data[right];
                lazyData[right] = null;
            }
        }
        tree[nodeIndex] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    @Override
    public void batchUpdate(int updateL, int updateR, E num) {
        batchUpdate(0, 0, data.length-1, updateL, updateR, num);
    }

    private void batchUpdate(int nodeIndex, int left, int right, int updateL, int updateR ,E num){
        if(left == updateL && right == updateR){
            if(left != right) {
                for (int i = left ; i <= right ; i++) {
                    if (lazyData[i] != null) {
                        lazyData[i] = merger.merge(lazyData[i], num);
                    } else {
                        lazyData[i] = num;
                    }
                    tree[nodeIndex] = merger.merge(tree[nodeIndex],num);
                }
            }else {
                if(lazyData[left] != null){
                    data[left] = merger.merge(lazyData[left],data[left]);
                    lazyData[left] = null;
                }
                data[left] = merger.merge(data[left],num);
                tree[nodeIndex] = data[left];
            }
            return;
        }

        int mid = left + (right-left)/2;
        int leftChild = leftChild(nodeIndex);
        int rightChild = rightChild(nodeIndex);
        if(updateL > mid){
           batchUpdate(rightChild, mid+1, right, updateL, updateR, num);
        }
        else if(updateR <= mid){
           batchUpdate(leftChild, left, mid, updateL, updateR, num);
        }
        else {
           batchUpdate(leftChild, left, mid, updateL, mid, num);
           batchUpdate(rightChild, mid+1, right, mid+1, updateR, num);
        }
        for (int i = updateL; i <= updateR; i++) {
            tree[nodeIndex] = merger.merge(tree[nodeIndex], num);
        }
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(lazyData[index] != null){
            return merger.merge(data[index],lazyData[index]);
        }
        return data[index];
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("segment tree size = ").append(getSize());
        sbr.append(" data :");
        sbr.append(Arrays.toString(tree));
        sbr.append("\nLazy:").append(Arrays.toString(lazyData));
        return sbr.toString();
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }
    private int rightChild(int index){
        return 2 * index + 2;
    }


}
