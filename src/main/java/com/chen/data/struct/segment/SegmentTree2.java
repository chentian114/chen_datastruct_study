package com.chen.data.struct.segment;

/**
 * @author: Chentian
 * @date: Created in 2021/3/10 7:15
 * @desc 使用二叉树实现线段树
 */
public class SegmentTree2<E> implements SegmentTree<E>{

    private class Node{
        private E val;
        private Node left;
        private Node right;
        private Node(E val){
            this.val = val;
        }

        public Node(Node left, Node right, E val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
    private int size ;
    private Node root ;
    private E[] data ;
    private Merger<E> merger;

    public SegmentTree2(E[] data,Merger<E> merger){
        if (data == null || data.length == 0 || merger == null){
            throw new IllegalArgumentException("data or merge is empty!");
        }

        this.data = (E[])new Object[data.length];
        for (int i = 0 ; i < data.length ; i++){
            this.data[i] = data[i];
        }

        this.size = data.length;
        this.merger = merger;
        this.root = buildSegmentTree(0,data.length-1);
    }

    private Node buildSegmentTree( int left, int right) {
        if(left == right){
            return new Node(data[left]);
        }

        int mid = left + (right - left)/2 ;

        Node leftNode = buildSegmentTree(left, mid);
        Node rightNode = buildSegmentTree(mid+1 , right);

        E val = merger.merge(leftNode.val, rightNode.val);

        return new Node(leftNode,rightNode,val);
    }

    /**
     * 返回区间[queryL, queryR]的值
     */
    @Override
    public E query(int queryL, int queryR){
        return query(root,0,data.length-1, queryL,queryR);
    }

    private E query(Node node, int left, int right, int queryL, int queryR) {
        if(left == queryL && right == queryR){
            return node.val;
        }

        int mid = left + (right - left)/2 ;
        if(queryR <= mid){
            return query(node.left, left, mid, queryL, queryR);
        }
        else if(queryL > mid){
            return query(node.right, mid+1, right, queryL, queryR);
        }
        else {
            E leftVal = query(node.left, left, mid, queryL, mid);
            E rightVal = query(node.right, mid+1, right, mid+1, queryR);
            return merger.merge(leftVal,rightVal);
        }
    }

    @Override
    public void set(int index, E e){
        set(root,0, data.length-1, index, e);
    }

    private void set(Node node, int left, int right, int index, E e) {
        if(left == right && left == index){
            data[index] = e;
            node.val = e;
            return;
        }

        int mid = left + (right - left)/2 ;
        if(index <= mid){
            set(node.left,left,mid,index,e);
        }
        else {
            set(node.right,mid+1, right, index, e);
        }

        node.val = merger.merge(node.left.val, node.right.val);
    }

    public int getSize(){
        return size;
    }

    public E get(int index){
        return data[index];
    }

    @Override
    public void batchUpdate(int updateL, int updateR, E num) {
        for (int i = updateL ; i <= updateR ; i++){
            set(i, merger.merge(data[i],num));
        }
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("segment tree size = ").append(size);
        sbr.append(" data:\n");
        generateTreeString(root,0,sbr);
        return sbr.toString();
    }

    private void generateTreeString(Node node, int depth,StringBuilder sbr) {
        for (int i = 0 ; i < depth ; i++){
            sbr.append("-");
        }
        if(node == null){
            sbr.append("null").append("\n");
            return ;
        }

        sbr.append("[").append(node.val).append("]\n");
        generateTreeString(node.left, depth+1, sbr);
        generateTreeString(node.right, depth+1, sbr);
    }
}
