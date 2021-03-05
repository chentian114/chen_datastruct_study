package com.chen.data.struct.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Chentian
 * @date: Created in 2021/3/5 6:33
 * @desc 二叉树后序遍历
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class Leetcode145BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root,list);
        return list;
    }

    private void postOrder(TreeNode node, List<Integer> data) {
        if(node == null){
            return;
        }
        postOrder(node.left,data);
        postOrder(node.right,data);
        data.add(node.val);
    }

    private void postOrderNR(TreeNode node, List<Integer> data) {
        if(node == null){
            return;
        }
        Stack<TreeNode> orderStack = new Stack<>();
        Stack<Integer> outStack = new Stack<>();
        orderStack.push(node);
        while (!orderStack.isEmpty()){
            TreeNode curNode = orderStack.pop();
            outStack.push(curNode.val);
            if(curNode.left != null) {
                orderStack.push(curNode.left);
            }
            if(curNode.right != null) {
                orderStack.push(curNode.right);
            }
        }

        while (!outStack.isEmpty()){
            data.add(outStack.pop());
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
     * 模拟系统栈进行后序遍历
     */
    private void postOrderSystemNR(TreeNode node, List<Integer> data){
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
                    stack.push(new SystemNode(curNode,"three"));
                    if(curNode.right != null){
                        stack.push(new SystemNode(curNode.right,"one"));
                    }
                    break;
                case "three":
                    data.add(curSysNode.node.val);
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
