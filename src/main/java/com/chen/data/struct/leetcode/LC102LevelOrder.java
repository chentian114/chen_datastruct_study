package com.chen.data.struct.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 二叉树的层序遍历，使用自定义动态数组队列实现
 * @Author Chentian
 * @date 2020/10/26
 */
public class LC102LevelOrder {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private interface Array<E> {

        /** 判断是否为空 */
        boolean isEmpty();
        /** 获取数组中元素个数 */
        int getSize();
        /** 获取当前数组容量 */
        int getCapacity();

        /** 在指定索引位置添加元素 */
        void add(int index, E e);
        /** 在头部添加元素 */
        void addLast(E e);
        /** 在尾部添加元素 */
        void addFirst(E e);

        /** 获取索引位置元素 */
        E get(int index);
        /** 根据指定元素查找索引 */
        int find(E e);
        /** 查找数组中是否包含指定元素 */
        boolean contains(E e);

        /** 修改指定索引位置元素 */
        void set(int index, E e);

        /** 删除指定索引位置元素 */
        E remove(int index);
        /** 删除第一个元素 */
        E removeFirst();
        /** 删除最后一个元素 */
        E removeLast();
        /** 删除指定元素 */
        void removeElement(E e);

    }

    private class DynamicArray<E> implements Array<E>{

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

    private interface Queue<E> {
        /** 入队 */
        void enqueue(E e);
        /** 出队 */
        E dequeue();
        /** 获取队首元素 */
        E getFront();
        /** 获取队列中元素数量 */
        int getSize();
        /** 是否为空 */
        boolean isEmpty();
    }

    private class ArrayQueue<E> implements Queue<E>{
        public static final int DEFAULT_CAPACITY = 16;
        private Array<E> data;

        public ArrayQueue(){
            this(DEFAULT_CAPACITY);
        }

        public ArrayQueue(int capacity){
            data = new DynamicArray<>(capacity);
        }

        @Override
        public void enqueue(E e) {
            data.addLast(e);
        }

        @Override
        public E dequeue() {
            return data.removeFirst();
        }

        @Override
        public E getFront() {
            return data.get(0);
        }

        @Override
        public int getSize() {
            return data.getSize();
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

    }

    private class Node{
        TreeNode treeNode;
        int level;
        public Node(TreeNode treeNode,int level){
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();

        Queue<Node> queue = new ArrayQueue<>();
        queue.enqueue(new Node(root,0));

        int level = 0 ;
        List<Integer> tmpList = new ArrayList<>();
        while (!queue.isEmpty()){

            Node node = queue.dequeue();
            if(node.level != level){
                result.add(tmpList);
                tmpList = new ArrayList<>();
                level = node.level;
            }

            tmpList.add(node.treeNode.val);
            if(node.treeNode.left != null){
                queue.enqueue(new Node(node.treeNode.left,node.level+1));
            }
            if(node.treeNode.right != null){
                queue.enqueue(new Node(node.treeNode.right,node.level+1));
            }
        }
        result.add(tmpList);

        return result;
    }

    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        List<List<Integer>> lists = new LC102LevelOrder().levelOrder(treeNode);

        for (int i = 0 ; i< lists.size() ; i++){
            System.out.println(i+":");
            for (Integer obj : lists.get(i)){
                System.out.print(obj+",");
            }
            System.out.println();
        }

    }

}
