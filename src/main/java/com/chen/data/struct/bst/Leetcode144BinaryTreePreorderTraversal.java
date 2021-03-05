package com.chen.data.struct.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2021/3/5 6:05
 * @desc 二叉树的前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class Leetcode144BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root,list);
        return list;
    }

    private void preOrder(TreeNode node, List<Integer> data){
        if(node == null){
            return;
        }
        data.add(node.val);
        preOrder(node.left,data);
        preOrder(node.right,data);
    }

    private void preOrderNR(TreeNode node, List<Integer> data){
        if(node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            data.add(cur.val);
            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    private class SystemNode{
        TreeNode node;
        String times;
        private SystemNode(TreeNode node,String times){
            this.node = node;
            this.times = times;
        }
    }

    /**
     * 模拟系统栈进行先序遍历
     */
    private void inOrderSystemNR(TreeNode node, List<Integer> data){
        if(node == null){
            return;
        }
        SystemNode rootNode = new SystemNode(node,"one");
        Stack<SystemNode> stack = new Stack<>();
        stack.push(rootNode);
        while (!stack.isEmpty()){
            SystemNode curSysNode = stack.pop();
            TreeNode curNode = curSysNode.node;
            switch (curSysNode.times){
                case "one":
                    data.add(curSysNode.node.val);
                    stack.push(new SystemNode(curNode,"two"));
                    if(curNode.left != null){
                        stack.push(new SystemNode(curNode.left,"one"));
                    }
                    break;
                case "two":
                    stack.push(new SystemNode(curNode,"three"));
                    if(curNode.right != null){
                        stack.push(new SystemNode(curNode.right,"one"));
                    }
                    break;
                case "three":
                    break;
            }
        }
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
}
