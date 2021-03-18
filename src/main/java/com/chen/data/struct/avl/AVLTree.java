package com.chen.data.struct.avl;

import java.util.LinkedList;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/16
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E>{

    private class Node{
        private E val;
        private int height;
        private Node left;
        private Node right;

        private Node(E val){
            this.val = val;
            this.height = 1;
        }

        public Node(E val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size ;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E value) {
        root = add(root, value);
    }

    private Node add(Node node, E value) {
        if(node == null){
            size++;
            return new Node(value);
        }

        if(node.val.compareTo(value) > 0){
            node.left = add(node.left, value);
        }
        else if(node.val.compareTo(value) < 0){
            node.right = add(node.right, value);
        }
        else {
            node.val = value;
        }

        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);

        if(Math.abs(balanceFactor) > 1){
            node = reBalance(node,balanceFactor);
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }

        return node;
    }

    /**
     * 对不平衡节点，重平衡处理
     */
    private Node reBalance(Node node, int balanceFactor){
        //LL 右旋转
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }

        //RR 左旋转
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }

        //LR 先左旋转，再右旋转
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            node = rightRotate(node);
            return node;
        }

        //RL 先右旋转，再左旋转
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            node = leftRotate(node);
            return node;
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node node){
        Node x = node.left;
        Node T3 = x.right;

        x.right = node;
        node.left = T3;

        // 更新height
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node node){
        Node x = node.right;
        Node T2 = x.left;

        node.right = T2;
        x.left = node;

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return leftHeight - rightHeight;
    }

    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        else {
            return node.height;
        }
    }

    @Override
    public boolean contains(E value) {
        if(isEmpty()){
            return false;
        }
        return find(root, value) != null;
    }

    private Node find(Node node, E value){
        if(node == null){
            return null;
        }

        if(node.val.compareTo(value) > 0){
            return find(node.left, value);
        }
        else if(node.val.compareTo(value) < 0){
            return find(node.right, value);
        }
        else {
            return node;
        }
    }

    @Override
    public E minimum() {
        if(isEmpty()){
            return null;
        }
        Node minimum = minimum(root);
        return minimum == null ? null : minimum.val;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    @Override
    public E maximum() {
        if(isEmpty()){
            return null;
        }
        Node maximum = maximum(root);
        return maximum == null ? null : maximum.val;
    }

    private Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    @Override
    public void remove(E e) {
        if(isEmpty()){
            return;
        }
        root = remove(root, e);
    }

    private Node remove(Node node, E value) {
        if(node == null){
            return null;
        }

        Node retNode ;
        if(node.val.compareTo(value) > 0){
            node.left = remove(node.left, value);
            retNode = node;
        }
        else if(node.val.compareTo(value) < 0){
            node.right = remove(node.right, value);
            retNode = node;
        }
        else {
            if(node.left == null){
                Node right = node.right;
                node.right = null;
                size--;
                retNode =  right;
            }
            else if(node.right == null){
                Node left = node.left;
                node.left = null;
                size--;
                retNode =  left;
            }
            else {
                Node successor = minimum(node.right);
                Node right = remove(node.right,successor.val);
                Node left = node.left;
                node.left = node.right = null;
                retNode =  new Node(successor.val,left,right);
            }
        }

        if(retNode == null){
            return null;
        }

        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        int balanceFactor = getBalanceFactor(retNode);
        if(Math.abs(balanceFactor) > 1){
            retNode = reBalance(retNode,balanceFactor);
            retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        }

        return retNode;
    }

    public String levelOrder() {
        StringBuilder sbr = new StringBuilder();
        if(isEmpty()){
            return sbr.toString();
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.push(root);

        while (!queue.isEmpty()){
            Node poll = queue.poll();
            if(poll.left != null) {
                queue.offer(poll.left);
            }
            if(poll.right != null) {
                queue.offer(poll.right);
            }
            sbr.append(poll.val).append("(").append(poll.height).append(")").append(",");
        }
        return sbr.toString();
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("AVLTree size = ").append(size).append(" data: \n");
        generateTreeToString(root,0, sbr);
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

        sbr.append(node.val).append("(").append(node.height).append(")").append("\n");
        generateTreeToString(node.left, depth+1, sbr);
        generateTreeToString(node.right, depth+1, sbr);
    }

}
