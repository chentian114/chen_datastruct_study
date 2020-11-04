package com.chen.data.struct.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 二叉树的层序遍历，使用链表循环队列实现
 * @Author Chentian
 * @date 2020/10/26
 * 结果：	通过	  3 ms	38.7 MB
 */
public class LC102LevelOrder3 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private interface MyList <E> {

        /** 添加指定元素 */
        void add(int index,E e);

        /** 在头部添加指定元素 */
        void addFirst(E e);

        /** 在尾部添加指定元素 */
        void addLast(E e);

        /** 在头部添加指定元素 */
        void set(int index, E e);

        /** 获取链表中指定索引元素 */
        E get(int index);

        /** 获取链表中首个索引元素 */
        E getFirst();

        /** 获取链表中最后一个索引元素 */
        E getLast();

        /** 链表中是否包含指定元素 */
        boolean contains(E e);

        /** 根据索引删除指定元素 */
        E remove(int index);

        /** 删除头部元素 */
        E removeFirst();

        /** 删除尾部元素 */
        E removeLast();

        /** 删除指定元素 */
        void removeElement(E e);

        /** 获取链表容量 */
        int size();

        /** 链表是否为容 */
        boolean isEmpty();
    }

    private class LinkedList<E> implements MyList<E>{

        private class Node{
            private E e;
            private Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }
        }

        private int size ;
        private Node head;

        @Override
        public void add(int index, E e) {
            if(index > size){
                throw new IllegalArgumentException("index is error!");
            }
            Node newNode = new Node(e,null);

            //需要对head节点做特殊处理
            if(index == 0){
                newNode.next = head;
                head = newNode;
                size++;
                return;
            }
            Node prev = head;
            for( int i = 0 ; i < index-1 ; i++){
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
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
            if(index >= size){
                throw new IllegalArgumentException("index is error!");
            }
            Node cur = head;
            for (int i = 0 ; i < index ; i++){
                cur = cur.next;
            }
            cur.e = e;
        }

        @Override
        public E get(int index) {
            if(index >= size){
                throw new IllegalArgumentException("index is error!");
            }
            Node cur = head;
            for (int i = 0 ; i < index ; i++){
                cur = cur.next;
            }
            return cur.e;
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
            Node cur = head;
            for (int i = 0 ; i < size ; i++){
                if(cur.e.equals(e)){
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        @Override
        public E remove(int index) {
            if(index >= size){
                throw new IllegalArgumentException("index is error!");
            }

            //需要对head节点做特殊处理
            if( index == 0 ){
                E e = head.e;
                head = head.next;
                size -- ;
                return e;
            }

            Node prev = head;
            for (int i = 0 ; i < index-1 ; i++){
                prev = prev.next;
            }
            E e = prev.next.e;
            prev.next = prev.next.next;
            size--;

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
            if(size == 0){
                return;
            }

            if(head.e.equals(e)){
                head = head.next;
                size--;
                return;
            }

            Node prev = head;
            for (int i = 0 ; i < size-1 ; i++){
                if(prev.next.e.equals(e)){
                    prev.next.next = prev.next;
                    size--;
                    return;
                }
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

    private class LinkedListQueue<E> implements Queue<E> {

        private MyList<E> list = new LinkedList<>();

        @Override
        public void enqueue(E e) {
            list.addLast(e);
        }

        @Override
        public E dequeue() {
            return list.removeFirst();
        }

        @Override
        public E getFront() {
            return list.getFirst();
        }

        @Override
        public int getSize() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
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

        Queue<Node> queue = new LinkedListQueue<>();
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

        List<List<Integer>> lists = new LC102LevelOrder3().levelOrder(treeNode);

        for (int i = 0 ; i< lists.size() ; i++){
            System.out.println(i+":");
            for (Integer obj : lists.get(i)){
                System.out.print(obj+",");
            }
            System.out.println();
        }

    }

}
