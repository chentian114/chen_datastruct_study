package com.chen.data.struct.bst;


import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2021/3/4 6:43
 * @desc  二叉搜索树实现
 */
public class BSTree<E extends Comparable<E>> {


    private class Node{
        private E value;
        private Node left;
        private Node right;
        private Node(E value){
            this.value = value;
        }
    }

    private int size = 0;
    private Node root;


    public void add(E value){
        root = add(root,value);
    }

    private Node add(Node node, E value) {
        if(node == null){
            size++;
            return new Node(value);
        }

        if(node.value.compareTo(value) > 0 ){
            node.left = add(node.left, value);
        }
        else if(node.value.compareTo(value) < 0){
            node.right = add(node.right, value);
        }
        return node;
    }

    public boolean contains(E value){
        Node curNode = root;
        while (curNode != null){
            if(curNode.value.compareTo(value) == 0){
                return true;
            }
            else if(curNode.value.compareTo(value) > 0){
                curNode = curNode.left;
            }
            else {
                curNode = curNode.right;
            }
        }
        return false;
    }

    public String preOrder(){
        StringBuilder sbr = new StringBuilder("preOrder data[");
        preOrder(root,sbr);
        delLastComma(sbr);
        sbr.append("]");
        return sbr.toString();
    }

    private void preOrder(Node node, StringBuilder sbr) {
        if (node == null){
            return;
        }
        sbr.append(node.value).append(",");
        preOrder(node.left, sbr);
        preOrder(node.right, sbr);
    }

    public String preOrderNR(){
        StringBuilder sbr = new StringBuilder("preOrder data[");
        if(root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                sbr.append(node.value).append(",");
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
        }
        delLastComma(sbr);
        sbr.append("]");
        return sbr.toString();
    }

    public String inOrder(){
        StringBuilder sbr = new StringBuilder("inOrder data[");

        inOrder(root,sbr);

        delLastComma(sbr);
        sbr.append("]");
        return sbr.toString();
    }

    private void inOrder(Node node, StringBuilder sbr) {
        if(node == null){
            return;
        }

        inOrder(node.left,sbr);
        sbr.append(node.value).append(",");
        inOrder(node.right,sbr);
    }

    public String inOrderNR(){
        StringBuilder sbr = new StringBuilder("inOrder data[");
        Stack<Node> stack = new Stack<>();
        Node curNode = root;

        while (!stack.isEmpty() || curNode != null){
            if(curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }else {
                Node node = stack.pop();
                sbr.append(node.value).append(",");
                curNode = node.right;
            }
        }
        delLastComma(sbr);
        sbr.append("]");
        return sbr.toString();
    }

    public String postOrder(){
        StringBuilder sbr = new StringBuilder("postOrder data[");
        postOrder(root,sbr);
        delLastComma(sbr);
        sbr.append("]");
        return sbr.toString();
    }

    private void postOrder(Node node, StringBuilder sbr) {
        if(node == null){
            return;
        }

        postOrder(node.left, sbr);
        postOrder(node.right, sbr);
        sbr.append(node.value).append(",");
    }

    public String postOrderNR(){
        StringBuilder sbr = new StringBuilder("postOrder data[");

        Stack<Node> orderStack = new Stack<>();
        Stack<E> outStack = new Stack<>();
        orderStack.push(root);
        while (!orderStack.isEmpty()){
            Node node = orderStack.pop();
            outStack.push(node.value);
            if(node.left != null){
                orderStack.push(node.left);
            }
            if(node.right != null){
                orderStack.push(node.right);
            }
        }
        while (!outStack.isEmpty()){
            sbr.append(outStack.pop()).append(",");
        }

        delLastComma(sbr);
        sbr.append("]");
        return sbr.toString();
    }


    private void delLastComma(StringBuilder sbr) {
        int index = sbr.lastIndexOf(",");
        if (index != -1) {
            sbr.deleteCharAt(index);
        }
    }

    @Override
    public String toString() {

        StringBuilder sbr = new StringBuilder("BSTree size = ").append(size).append(" data:\n");

        generateBSTString(root, 0 , sbr);
        return sbr.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder sbr){
        for (int i = 0 ; i < depth ; i++){
            sbr.append("--");
        }
        if(node == null){
            sbr.append("NULL\n");
            return;
        }
        sbr.append(node.value).append("\n");
        generateBSTString(node.left,depth+1 , sbr);
        generateBSTString(node.right,depth+1, sbr);
    }
}
