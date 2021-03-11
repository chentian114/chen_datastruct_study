package com.chen.data.struct.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 键值映射
 * @Author Chentian
 * @date 2021/3/11
 * https://leetcode-cn.com/problems/map-sum-pairs/
 */
public class Leetcode677MapSum {

    class Node{
        int count;
        Map<Character,Node> next;
        Node(){
            next = new HashMap<>();
            count = 0;
        }
    }

    private Node root ;

    public Leetcode677MapSum(){
        root = new Node();
    }

    public void insert(String key, int val) {
        Node curNode = root;
        for (int i = 0 ; i < key.length() ; i++){
            Character c = key.charAt(i);

            Node node = curNode.next.get(c);
            if (node == null){
                node = new Node();
                curNode.next.put(c,node);
            }
            curNode = node;
        }
        curNode.count = val;
    }

    public int sum(String prefix) {
        int sum = 0 ;
        Node curNode = root;
        for (int i = 0 ; i < prefix.length() ; i ++){
            Character c = prefix.charAt(i);

            Node node = curNode.next.get(c);
            if(node == null){
                return 0;
            }
            curNode = node;
        }


        return sum(curNode);
    }

    private int sum(Node node){
        if(node.next == null || node.next.isEmpty()){
            return node.count;
        }

        int sum = node.count;
        for (Character c : node.next.keySet()){
            sum += sum(node.next.get(c));
        }
        return sum;
    }

    public static void main(String[] args) {
        Leetcode677MapSum mapSum = new Leetcode677MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));           // return 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));           // return 5 (apple + app = 3 + 2 = 5)
    }
}
