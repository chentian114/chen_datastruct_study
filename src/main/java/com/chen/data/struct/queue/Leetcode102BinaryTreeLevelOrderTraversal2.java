package com.chen.data.struct.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chentian
 * @date: Created in 2021/2/21 6:48
 * @desc 层序遍历二叉树
 */
public class Leetcode102BinaryTreeLevelOrderTraversal2 {

    class Node{
        private TreeNode treeNode;
        private int depth;
        public Node(TreeNode treeNode, int depth){
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }

        Queue<Node> queue = new ArrayQueue<>();
        queue.enqueue(new Node(root,0));

        List<Integer> list2 = new ArrayList<>();
        int depth = 0 ;
        while (!queue.isEmpty()){
            Node node = queue.dequeue();

            if(node.treeNode.left != null) {
                queue.enqueue(new Node(node.treeNode.left, node.depth + 1));
            }
            if(node.treeNode.right != null){
                queue.enqueue(new Node(node.treeNode.right, node.depth + 1));
            }

            if(depth != node.depth){
                depth = node.depth;
                list.add(list2);
                list2 = new ArrayList<>();
            }
            list2.add(node.treeNode.val);
        }
        list.add(list2);

        return list;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
