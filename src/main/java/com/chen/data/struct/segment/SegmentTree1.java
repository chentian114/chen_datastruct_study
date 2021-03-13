package com.chen.data.struct.segment;

import java.util.Arrays;

/**
 * @author: Chentian
 * @date: Created in 2021/3/9 20:48
 * @desc 线段树固定数组实现
 */
public class SegmentTree1<E> implements SegmentTree<E>{

    private Merger<E> merger;
    private E[] data;
    private E[] tree;

    public SegmentTree1(E[] data, Merger<E> merger){
        if(data == null || merger == null){
            throw new IllegalArgumentException("data or merger is null!");
        }

        this.data = (E[])new Object[data.length];
        for (int i = 0 ; i < data.length ; i++){
            this.data[i] = data[i];
        }
        this.merger = merger;
        tree = (E[]) new Object[data.length * 4];
        buildSegment(0, 0, data.length-1);
    }

    private void buildSegment(int treeIndex, int left, int right) {
        if(left == right){
            tree[treeIndex] = data[left];
            return;
        }
        if(left > right){
            return;
        }

        int mid = getMid(left,right);
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        buildSegment(leftChildIndex, left, mid);
        buildSegment(rightChildIndex, mid + 1, right);

        E lefVal = tree[leftChildIndex];
        E rightVal = tree[rightChildIndex];
        tree[treeIndex] = merger.merge(lefVal,rightVal);
    }

    /**
     * 返回区间[queryL, queryR]的值
     */
    @Override
    public E query(int queryL, int queryR){
        if(queryL > queryR){
            throw new IllegalArgumentException("error queryL > queryR!");
        }

        return query(0, 0, getLastIndex(), queryL, queryR);
    }

    private E query(int treeIndex, int left, int right, int queryL, int queryR){
        int mid = getMid(left,right);
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        if(left == queryL && right == queryR){
            return tree[treeIndex];
        }
        else if(queryR <= mid){
            return query(leftChildIndex, left , mid, queryL, queryR);
        }
        else if(queryL > mid){
            return query(rightChildIndex, mid+1, right, queryL, queryR);
        }
        else {
            E leftVal = query(leftChildIndex, left, mid, queryL, mid);
            E rightVal = query(rightChildIndex, mid+1, right, mid+1, queryR);
            return merger.merge(leftVal,rightVal);
        }
    }


    @Override
    public void set(int index, E e){
        data[index] = e;
        set(0, 0, getLastIndex(), index, e);
    }

    private void set(int treeIndex, int left, int right, int index, E e){
        if(left == right && left == index){
            tree[treeIndex] = e;
            return ;
        }

        int mid = getMid(left,right);
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        if(index <= mid){
            set(leftChildIndex, left, mid, index, e);
        }
        else {
            set(rightChildIndex, mid+1, right, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }

    @Override
    public void batchUpdate(int updateL, int updateR, E num) {
        for (int i = updateL ; i <= updateR ; i++){
            set(i, merger.merge(data[i],num));
        }
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("segment tree size = ").append(getSize());
        sbr.append(" data :");
        sbr.append(Arrays.toString(tree));
        return sbr.toString();
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        return data[index];
    }

    private int getLastIndex(){
        return getSize() - 1;
    }

    private int getMid(int left, int right){
        return left + (right - left)/2;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

}
