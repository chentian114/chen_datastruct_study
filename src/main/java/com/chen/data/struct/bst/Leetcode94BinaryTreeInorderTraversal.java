package com.chen.data.struct.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2021/3/5 6:16
 * @desc 二叉树中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class Leetcode94BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root,list);
        return list;
    }

    private void inOrder(TreeNode node, List<Integer> data){
        if(node == null){
            return;
        }
        inOrder(node.left, data);
        data.add(node.val);
        inOrder(node.right, data);
    }

    private void inOrderNR(TreeNode node, List<Integer> data){
        if(node == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null){
            if(node != null){
                stack.push(node);
                node = node.left;
            }
            else {
                TreeNode cur = stack.pop();
                data.add(cur.val);
                node = cur.right;
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
     * 模拟系统栈进行中序遍历
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
                    stack.push(new SystemNode(curNode,"two"));
                    if(curNode.left != null){
                        stack.push(new SystemNode(curNode.left,"one"));
                    }
                    break;
                case "two":
                    data.add(curSysNode.node.val);
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
