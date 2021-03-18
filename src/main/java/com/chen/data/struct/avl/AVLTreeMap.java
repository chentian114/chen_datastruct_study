package com.chen.data.struct.avl;

import com.chen.data.struct.bst.Map;

/**
 * @desc 基于平衡二叉树实现 映射
 * @Author Chentian
 * @date 2021/3/18
 */
public class AVLTreeMap<K extends Comparable<K>,V> implements Map<K,V> {

    class Node{
        private K key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        private Node(K key, V value){
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    @Override
    public void add(K k, V v) {
        root = add(root, k, v);

    }

    private Node add(Node node, K key, V value) {
        if(node == null){
            size++;
            return new Node(key,value);
        }

        if(node.key.compareTo(key) > 0){
            node.left = add(node.left,key,value);
        }
        else if(node.key.compareTo(key) < 0){
            node.right = add(node.right,key,value);
        }
        else {
            node.value = value;
        }

        //维护高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            node = reBalance(node,balanceFactor);
        }

        return node;
    }

    /**
     * 维护树点的平衡
     */
    private Node reBalance(Node node, int balanceFactor) {
        // LL 右旋转
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }

        // RR 左旋转
        if(balanceFactor < 1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }

        // LR 先左旋转再右旋转
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL 先右旋转再左旋转
        if(balanceFactor < 1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node leftRotate(Node node) {
        Node retNode = node.right;
        Node T3 = retNode.left;

        node.right = T3;
        retNode.left = node;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }

    private Node rightRotate(Node node) {
        Node retNode = node.left;
        Node T3 = retNode.right;

        node.left = T3;
        retNode.right = node;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }

    /**
     * 计算节点高度
     */
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 计算节点平衡因子
     */
    private int getBalanceFactor(Node node){
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public V remove(K k) {
        if(isEmpty()){
            return null;
        }

        Node node = getNode(root, k);
        if(node == null){
            return null;
        }
        V val = node.value;

        root = remove(root,k);

        return val;
    }

    private Node remove(Node node, K k) {
        if(node == null){
            return null;
        }

        Node retNode;
        if(node.key.compareTo(k) > 0){
            node.left = remove(node.left, k);
            retNode = node;
        }
        else if(node.key.compareTo(k) < 0){
            node.right = remove(node.right, k);
            retNode = node;
        }
        else {
            if(node.left == null){
                retNode = node.right;
                node.right = null;
                size--;
            }
            else if(node.right == null){
                retNode = node.left;
                node.left = null;
                size--;
            }
            else {
                //寻找后继节点作为新的根节点
                Node successor = minimum(node.right);
                retNode = new Node(successor.key,successor.value);

                retNode.left = node.left;
                //删除右子树中的后继节点
                retNode.right = remove(node.right,successor.key);
                node.left = node.right = null;
            }
        }

        if(retNode == null){
            return null;
        }
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        int balanceFactor = getBalanceFactor(retNode);
        if(Math.abs(balanceFactor) > 1){
            retNode = reBalance(retNode,balanceFactor);
        }

        return retNode;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    @Override
    public boolean contains(K k) {
        return getNode(root,k) != null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(root, k);
        return  node == null ? null : node.value;
    }

    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }

        if(node.key.compareTo(key) > 0){
            return getNode(node.left,key);
        }
        else if(node.key.compareTo(key) < 0){
            return getNode(node.right, key);
        }
        else {
            return node;
        }
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(root, k);
        if (node == null){
            return;
        }
        node.value = v;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        if(isEmpty()){
            return sbr.toString();
        }

        generateTreeToString(root,0,sbr);
        return sbr.toString();
    }


    private void generateTreeToString(Node node, int depth, StringBuilder sbr) {
        for (int i = 0 ; i < depth ; i++){
            sbr.append("-");
        }
        if(node == null){
            sbr.append("null\n");
            return;
        }

        sbr.append(node.key).append("=").append(node.value).append("(").append(node.height).append(")").append("\n");
        generateTreeToString(node.left, depth+1, sbr);
        generateTreeToString(node.right, depth+1, sbr);
    }
}
