package com.chen.data.struct.leetcode;

/**
 * @desc Leetcode练习-20 Valid Parentheses 匹配括号，使用自定义的ArrayStack
 * @Author Chentian
 * @date 2020/10/26
 * 结果：通过	  2 ms	36.7 MB
 */
public class LC20ValidParentheses2 {

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

    private class ArrayStack<E> implements Stack<E>{

        public static final int DEFAULT_CAPACITY = 16;
        private Array<E> data;

        public ArrayStack(){
            this(DEFAULT_CAPACITY);
        }

        public ArrayStack(int capacity){
            data = new DynamicArray<>(capacity);
        }

        @Override
        public void push(E e) {
            data.addLast(e);
        }

        @Override
        public E pop() {
            return data.removeLast();
        }

        @Override
        public E peek() {
            return data.get(data.getSize()-1);
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


    public boolean isValid(String s) {
        if( s == null || s.length() < 1 ){
            return true;
        }
        if( s.length() % 2 != 0 ){
            return false;
        }

        Stack<Character> stack = new ArrayStack<>();
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
        boolean result = new LC20ValidParentheses2().isValid(demo1);
        System.out.println(result);

        String demo2 = "(]";
        System.out.println(new LC20ValidParentheses2().isValid(demo2));

        String demo3 = "}{";
        System.out.println(new LC20ValidParentheses2().isValid(demo3));
    }
}
