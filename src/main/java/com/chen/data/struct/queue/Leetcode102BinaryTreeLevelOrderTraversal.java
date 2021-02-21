package com.chen.data.struct.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chentian
 * @date: Created in 2021/2/20 21:50
 * @desc 层序遍历二叉树
 */
public class Leetcode102BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }

        Queue<TreeNode> queue = new ArrayQueue<>();
        queue.enqueue(root);

        Queue<TreeNode> queue2 = new ArrayQueue<>();
        List<Integer> list2 = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.dequeue();

            list2.add(node.val);

            if(node.left!=null){
                queue2.enqueue(node.left);
            }
            if(node.right != null){
                queue2.enqueue(node.right);
            }

            if(queue.isEmpty() && !queue2.isEmpty()){
                queue = queue2;
                queue2 = new ArrayQueue<>();

                list.add(list2);
                list2 = new ArrayList<>();
            }
        }
        if(!list2.isEmpty()) {
            list.add(list2);
        }

        return list;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
}
