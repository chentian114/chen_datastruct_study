package com.chen.data.struct.leetcode;

/**
 * @desc Leetcode练习-20 Valid Parentheses 匹配括号，使用自定义的LinkedListStack
 * @Author Chentian
 * @date 2020/10/26
 * 结果：通过 	21 ms	36.7 MB
 */
public class LC20ValidParentheses3 {

    private interface List <E> {

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

    private class LinkedList<E> implements List<E>{

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

    private interface Stack<E> {
        /** 入栈 */
        void push(E e);
        /** 出栈 */
        E pop();
        /** 查看栈顶元素 */
        E peek();
        /** 查看栈内元素数量 */
        int getSize();
        /** 是否为空栈 */
        boolean isEmpty();
    }

    private class LinkedListStack<E> implements Stack<E> {

        private List<E> list = new LinkedList<>();

        @Override
        public void push(E e) {
            list.addLast(e);
        }

        @Override
        public E pop() {
            return list.removeLast();
        }

        @Override
        public E peek() {
            return list.getLast();
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

    public boolean isValid(String s) {
        if( s == null || s.length() < 1 ){
            return true;
        }
        if( s.length() % 2 != 0 ){
            return false;
        }

        Stack<Character> stack = new LinkedListStack<>();
        for (int i = 0 ; i < s.length() ; i++ ){
            char c = s.charAt(i);
            if( c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                char p = stack.pop();
                if(p == '(' && c != ')'){
                    return false;
                }
                if(p == '[' && c != ']'){
                    return false;
                }
                if(p == '{' && c != '}'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String demo1 = "()[]{}";
        boolean result = new LC20ValidParentheses3().isValid(demo1);
        System.out.println(result);

        String demo2 = "(]";
        System.out.println(new LC20ValidParentheses3().isValid(demo2));

        String demo3 = "}{";
        System.out.println(new LC20ValidParentheses3().isValid(demo3));
    }
}
