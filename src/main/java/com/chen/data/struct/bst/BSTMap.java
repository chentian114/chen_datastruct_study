package com.chen.data.struct.bst;

/**
 * @desc 基于二叉搜索树实现映射
 * @Author Chentian
 * @date 2021/3/6
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{

    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private Node(K key, V val){
            this.key = key;
            this.val = val;
        }

        public Node(K key, V val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private Node root ;
    private int size;

    @Override
    public void add(K k, V v) {
        root = add(root , k, v);
    }

    private Node add(Node node, K key, V val) {
        if(node == null){
            size++;
            return new Node(key,val);
        }

        if(node.key.compareTo(key) > 0 ){
            node.left = add(node.left, key, val);
        }
        else if(node.key.compareTo(key) < 0 ){
            node.right = add(node.right, key, val);
        }
        else {
            node.val = val;
        }

        return node;
    }

    @Override
    public V remove(K k) {
        Node node = getNode(root, k);
        if(node == null){
            return null;
        }
        V val = node.val;

        root = remove(root, k);
        return val;
    }

    private Node remove(Node node, K key) {
        if(node == null){
            return null;
        }
        if(node.key.compareTo(key) > 0){
            node.left = remove(node.left, key);
            return node;
        }
        else if(node.key.compareTo(key) < 0){
            node.right = remove(node.right, key);
            return node;
        }
        else {
            if(node.left == null && node.right == null){
                size -- ;
                return null;
            }
            else if(node.left == null){
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            else if(node.right == null){
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            else {
                Node successor = minimum(node.right);

                Node left = node.left;
                Node right =removeMin(node.right);
                node.left = node.right = null;

                return new Node(successor.key,successor.val,left,right);
            }
        }
    }

    private Node minimum(Node node) {
        if(node == null){
            return null;
        }
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if(node == null){
            return null;
        }
        if(node.left == null){
            Node right = node.right;
            size --;
            node.right = null;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }
        if(node.key.compareTo(key) > 0){
            return getNode(node.left, key);
        }
        else if(node.key.compareTo(key) < 0){
            return getNode(node.right, key);
        }
        else {
            return node;
        }
    }

    @Override
    public boolean contains(K k) {
        return getNode(root,k) != null ;
    }

    @Override
    public V get(K k) {
        Node node = getNode(root, k);
        return node == null ? null : node.val;
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(root, k);
        node.val = v;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String inOrder(){
        StringBuilder sbr = new StringBuilder("bstMap size= ").append(size).append(" data:");
        inOrder(root,sbr);
        return sbr.toString();
    }

    private void inOrder(Node node, StringBuilder sbr) {
        if(node == null){
            return;
        }
        inOrder(node.left, sbr);
        sbr.append("[").append(node.key).append("-").append(node.val).append("],");
        inOrder(node.right, sbr);
    }
}
