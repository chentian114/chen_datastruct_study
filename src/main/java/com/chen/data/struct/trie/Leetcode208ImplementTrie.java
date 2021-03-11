package com.chen.data.struct.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 实现一个前缀树
 * @Author Chentian
 * @date 2021/3/11
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Leetcode208ImplementTrie {

    class Node{
        boolean isWorld;
        Map<Character,Node> next;
        private Node(){
            isWorld = false;
            next = new HashMap<>();
        }
    }

    private Node root ;


    /** Initialize your data structure here. */
    public Leetcode208ImplementTrie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node curNode = root;
        for (int i = 0 ; i < word.length() ; i++){
            Character c = word.charAt(i);
            Node node = curNode.next.get(c);
            if(node == null){
                node = new Node();
                curNode.next.put(c,node);
            }
            curNode = node;
        }
        curNode.isWorld = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node curNode = root;
        for (int i = 0 ; i < word.length() ; i++){
            Character c = word.charAt(i);
            if(curNode.next.get(c) == null){
                return  false;
            }
            curNode = curNode.next.get(c);
        }
        return curNode.isWorld;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node curNode = root;
        for (int i = 0 ; i < prefix.length() ; i++){
            Character c = prefix.charAt(i);
            if(curNode.next.get(c) == null){
                return  false;
            }
            curNode = curNode.next.get(c);
        }
        return curNode != null;
    }



}
