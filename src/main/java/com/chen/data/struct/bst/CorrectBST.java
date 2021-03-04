package com.chen.data.struct.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @desc 二叉搜索树实现参考
 * @Author Chentian
 * @date 2021/3/4
 */
public class CorrectBST<E extends Comparable<E>>{

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public CorrectBST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向二分搜索树中添加新的元素e
    public void add(E e){
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e){

        if(node == null){
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e){

        if(node == null)
            return false;

        if(e.compareTo(node.e) == 0)
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) > 0
            return contains(node.right, e);
    }

    // 二分搜索树的前序遍历
    public String preOrder(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        preOrder(root,sbr);

        delLastComma(sbr);
        return sbr.toString();
    }

    // 前序遍历以node为根的二分搜索树, 递归算法
    private void preOrder(Node node, StringBuilder sbr){

        if(node == null)
            return;

//        System.out.println(node.e);
        sbr.append(node.e).append(",");
        preOrder(node.left, sbr);
        preOrder(node.right, sbr);
    }

    // 二分搜索树的非递归前序遍历
    public String preOrderNR(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        if(root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
//            System.out.println(cur.e);
                sbr.append(cur.e).append(",");

                if (cur.right != null)
                    stack.push(cur.right);
                if (cur.left != null)
                    stack.push(cur.left);
            }
        }

        delLastComma(sbr);
        return sbr.toString();
    }

    // 二分搜索树的中序遍历
    public String inOrder(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();
        inOrder(root,sbr);
        delLastComma(sbr);
        return sbr.toString();
    }

    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node, StringBuilder sbr){

        if(node == null)
            return;

        inOrder(node.left, sbr);
//        System.out.println(node.e);
        sbr.append(node.e).append(",");
        inOrder(node.right, sbr);
    }

    // 二分搜索树的后序遍历
    public String postOrder(){
        if(root == null){
            return null;
        }

        StringBuilder sbr = new StringBuilder();
        postOrder(root,sbr);
        delLastComma(sbr);
        return sbr.toString();
    }

    // 后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node, StringBuilder sbr){

        if(node == null)
            return;

        postOrder(node.left, sbr);
        postOrder(node.right, sbr);
//        System.out.println(node.e);
        sbr.append(node.e).append(",");
    }

    // 二分搜索树的层序遍历
    public String levelOrder(){
        if(root == null){
            return null;
        }
        StringBuilder sbr = new StringBuilder();

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
//            System.out.println(cur.e);
            sbr.append(cur.e).append(",");

            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }

        delLastComma(sbr);
        return sbr.toString();
    }

    // 寻找二分搜索树的最小元素
    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node){
        if(node.right == null)
            return node;

        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在节点
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e){
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e){

        if( node == null )
            return null;

        if( e.compareTo(node.e) < 0 ){
            node.left = remove(node.left , e);
            return node;
        }
        else if(e.compareTo(node.e) > 0 ){
            node.right = remove(node.right, e);
            return node;
        }
        else{   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    private void delLastComma(StringBuilder sbr) {
        int index = sbr.lastIndexOf(",");
        if (index != -1) {
            sbr.deleteCharAt(index);
        }
    }
}
