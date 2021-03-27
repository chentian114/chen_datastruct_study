package com.chen.data.struct.redblack;

/**
 * @desc 红黑树-添加元素、遍历实现
 * @Author Chentian
 * @date 2021/3/25
 */
public class RBTree<E extends Comparable<E>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private class Node{
        private E val;
        private Node left;
        private Node right;
        private boolean color;

        public Node(E val){
            this.val = val;
            this.color = RED;
        }
    }

    private Node root ;
    private int size ;


    public void add(E val){
        root = add(root, val);
        root.color = BLACK;
    }

    private Node add(Node node, E val){
        if(node == null){
            size++;
            return new Node(val);
        }

        if(node.val.compareTo(val) > 0){
            node.left = add(node.left, val);
        }
        else if(node.val.compareTo(val) < 0){
            node.right = add(node.right, val);
        }

        //是否需要左旋转
        if(!isRed(node.left) && isRed(node.right)){
            node = leftRotate(node);
        }

        //是否需要右旋转
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }

        //是否需要颜色翻转
        if(isRed(node.left) && isRed(node.right)){
            node = flipColor(node);
        }

        return node;
    }

    // 颜色翻转
    private Node flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color= RED;
        return node;
    }


    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node newNode = node.left;

        node.left = newNode.right;
        newNode.right = node;
        newNode.color = node.color;
        node.color = RED;

        return newNode;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node newNode = node.right;

        node.right = newNode.left;
        newNode.left = node;
        newNode.color = node.color;
        node.color = RED;

        return newNode;
    }

    private boolean isRed(Node node){
        if(node == null){
            return false;
        }
        return node.color == RED;
    }


    public String inOrder(){
        StringBuilder sbr = new StringBuilder();
        inOrder(root,sbr);
        return sbr.toString();
    }

    private void inOrder(Node node, StringBuilder sbr) {
        if(node == null){
            return;
        }

        inOrder(node.left,sbr);
        String color = node.color ? "BLACK" : "RED";
        sbr.append(node.val).append("-").append(color).append(",");
        inOrder(node.right,sbr);
    }


    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("Red Black Tree size =").append(size).append("\n");
        generateTreeToStr(root, 0, sbr);

        return sbr.toString();
    }

    private void generateTreeToStr(Node node, int depth, StringBuilder sbr) {
        for (int i = 0 ; i < depth ; i++){
            sbr.append("-");
        }
        if(node == null){
            sbr.append("NULL-BLACK\n");
            return;
        }

        String color = node.color ? "BLACK" : "RED";
        sbr.append(node.val).append("-").append(color).append("\n");
        generateTreeToStr(node.left,depth+1, sbr);
        generateTreeToStr(node.right,depth+1, sbr);
    }


}
