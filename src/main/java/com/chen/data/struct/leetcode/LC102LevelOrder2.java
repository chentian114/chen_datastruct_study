package com.chen.data.struct.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 二叉树的层序遍历，使用自定义动态循环队列实现
 * @Author Chentian
 * @date 2020/10/26
 */
public class LC102LevelOrder2 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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

    public class DynamicLoopQueue<E> implements Queue<E> {

        public static final int DEFAULT_CAPACITY = 16;
        private E[] data;
        private int front;
        private int tail;
        private int size;

        public DynamicLoopQueue(){
            this(DEFAULT_CAPACITY);
        }

        public DynamicLoopQueue(int capacity){
            if(capacity < 1){
                capacity = DEFAULT_CAPACITY;
            }
            // 多留一个空间，用于区分队列空和满的情况
            data = (E[])new Object[capacity+1];
        }

        private boolean isFull(){
            return front == (tail + 1) % data.length;
        }

        private int getCapacity(){
            return data.length - 1 ;
        }

        @Override
        public void enqueue(E e) {
            if(isFull()){
                resize(getCapacity() * 2);
            }

            data[tail] = e;
            tail = (tail +1)%data.length;
            size++;
        }

        @Override
        public E dequeue() {
            if(isEmpty()){
                throw new IllegalArgumentException("queue is empty!");
            }

            E e = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;

            if(size < (getCapacity() / 4) && (getCapacity() / 2) != 0){
                resize(getCapacity()/2);
            }
            return e;
        }

        @Override
        public E getFront() {
            if(isEmpty()){
                throw new IllegalArgumentException("queue is empty!");
            }
            return data[front];
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return front == tail;
        }

        private void resize(int newCapacity) {
            E[] newData = (E[])new Object[newCapacity+1];

            for (int i = 0 ; i < size ; i++ ){
                int index = (front + i)%data.length;
                newData[i] = data[index];
            }
            front = 0 ;
            tail = size ;
            data = newData;
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

        Queue<Node> queue = new DynamicLoopQueue<>();
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

        List<List<Integer>> lists = new LC102LevelOrder2().levelOrder(treeNode);

        for (int i = 0 ; i< lists.size() ; i++){
            System.out.println(i+":");
            for (Integer obj : lists.get(i)){
                System.out.print(obj+",");
            }
            System.out.println();
        }

    }

}
