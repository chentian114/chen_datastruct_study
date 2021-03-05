package com.chen.data.struct.bst;


import java.util.LinkedList;
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
        private Node(E value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int size = 0;
    private Node root;


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

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
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        preOrder(root,sbr);
        delLastComma(sbr);
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
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();

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
        delLastComma(sbr);
        return sbr.toString();
    }

    class SystemNode{
        Node node;
        String times;
        private SystemNode(Node node,String times){
            this.node = node;
            this.times = times;
        }
    }
    public String preOrderSystemNR(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        Stack<SystemNode> stack = new Stack<>();
        stack.push(new SystemNode(root,"one"));

        while (!stack.isEmpty()){
            SystemNode systemNode = stack.pop();
            switch (systemNode.times){
                case "one":
                    sbr.append(systemNode.node.value).append(",");
                    stack.push(new SystemNode(systemNode.node,"two"));
                    if(systemNode.node.left != null){
                        stack.push(new SystemNode(systemNode.node.left,"one"));
                    }
                    break;
                case "two":
                    stack.push(new SystemNode(systemNode.node,"three"));
                    if(systemNode.node.right != null){
                        stack.push(new SystemNode(systemNode.node.right,"one"));
                    }
                    break;
                case "three":
                    break;
            }
        }

        delLastComma(sbr);
        return sbr.toString();
    }

    public String inOrder(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();

        inOrder(root,sbr);

        delLastComma(sbr);
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
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
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
        return sbr.toString();
    }

    public String inOrderSystemNR(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        Stack<SystemNode> stack = new Stack<>();
        stack.push(new SystemNode(root,"one"));

        while (!stack.isEmpty()){
            SystemNode systemNode = stack.pop();
            switch (systemNode.times){
                case "one":
                    stack.push(new SystemNode(systemNode.node,"two"));
                    if(systemNode.node.left != null){
                        stack.push(new SystemNode(systemNode.node.left,"one"));
                    }
                    break;
                case "two":
                    sbr.append(systemNode.node.value).append(",");
                    stack.push(new SystemNode(systemNode.node,"three"));
                    if(systemNode.node.right != null){
                        stack.push(new SystemNode(systemNode.node.right,"one"));
                    }
                    break;
                case "three":
                    break;
            }
        }

        delLastComma(sbr);
        return sbr.toString();
    }

    public String postOrder(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        postOrder(root,sbr);
        delLastComma(sbr);
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
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();

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
        return sbr.toString();
    }

    public String postOrderSystemNR(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        Stack<SystemNode> stack = new Stack<>();
        stack.push(new SystemNode(root,"one"));

        while (!stack.isEmpty()){
            SystemNode systemNode = stack.pop();
            switch (systemNode.times){
                case "one":
                    stack.push(new SystemNode(systemNode.node,"two"));
                    if(systemNode.node.left != null){
                        stack.push(new SystemNode(systemNode.node.left,"one"));
                    }
                    break;
                case "two":
                    stack.push(new SystemNode(systemNode.node,"three"));
                    if(systemNode.node.right != null){
                        stack.push(new SystemNode(systemNode.node.right,"one"));
                    }
                    break;
                case "three":
                    sbr.append(systemNode.node.value).append(",");
                    break;
            }
        }

        delLastComma(sbr);
        return sbr.toString();
    }


    public String levelOrder(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        LinkedList<Node> queue = new LinkedList<>();
        if(root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()){
            Node node = queue.remove();
            sbr.append(node.value).append(",");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }

        delLastComma(sbr);
        return sbr.toString();
    }


    public E minimum(){
        return minimum(root);
    }

    private E minimum(Node node) {
        if(node == null){
            return null;
        }

        if(node.left != null){
           return minimum(node.left);
        }
        else {
            return node.value;
        }
    }

    public E maximum(){
        return maximum(root);
    }

    private E maximum(Node node) {
        if(node == null){
            return null;
        }

        if(node.right != null){
            return maximum(node.right);
        }
        else {
            return node.value;
        }
    }

    public void removeMin(){
        root = removeMin(root);
    }

    private Node removeMin(Node node) {
        if(node == null){
            return null;
        }

        if(node.left != null){
            node.left = removeMin(node.left);
            return node;
        }
        else {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
    }

    public void removeMax(){
        root = removeMax(root);
    }

    private Node removeMax(Node node) {
        if(node == null){
            return null;
        }

        if(node.right != null){
            node.right = removeMax(node.right);
            return node;
        }
        else {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
    }

    public void remove(E e){
        root = remove(root,e);
    }

    private Node remove(Node node, E value) {
        if(node == null){
            return null;
        }

        if(node.value.compareTo(value) > 0){
            node.left = remove(node.left,value);
            return node;
        }
        else if(node.value.compareTo(value) < 0){
            node.right = remove(node.right,value);
            return node;
        }
        else {
            if(node.left == null && node.right == null){
                size --;
                return null;
            }
            else if(node.left != null && node.right == null){
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            else if(node.left == null && node.right != null){
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            else {
                E successor = minimum(node.right);
                Node left = node.left;
                Node right = removeMin(node.right);
                node.left = node.right = null;

                Node newNode = new Node(successor,left,right);
                return newNode;

            }
        }
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
