package com.chen.data.struct.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 字典树实现
 * @Author Chentian
 * @date 2021/3/11
 */
public class Trie {

    private class Node{
        boolean isWorld;
        Map<Character,Node> next;
        private Node(){
            this.isWorld = false;
            this.next = new HashMap<>();
        }
    }

    private Node root ;
    private int size ;

    public Trie(){
        size = 0;
        root = new Node();
    }

    public int getSize(){
        return size;
    }

    public void add(String word){
        if(word == null || word.length() == 0){
            return;
        }

        Node curNode = root;
        for (int i = 0 ; i < word.length() ; i++){
            Character c = word.charAt(i);

            Node node = curNode.next.get(c);
            if(node == null){
                node = new Node();
                curNode.next.put(c, node);
            }
            curNode = node;
        }
        size ++;
        curNode.isWorld = true;
    }

    public boolean contains(String word){
        if(word == null || word.length() == 0){
            throw new IllegalArgumentException("error: word is null or empty!");
        }

        Node curNode = getNode(word);
        return curNode != null && curNode.isWorld;
    }

    public boolean isPrefix(String word){
        if(word == null || word.length() == 0){
            throw new IllegalArgumentException("error: word is null or empty!");
        }

        Node curNode = getNode(word);
        return curNode != null;
    }

    public void remove(String word){
        if(word == null || word.length() == 0){
            return;
        }

        if (!contains(word)) {
            return;
        }

        root = remove(root, word, 0);
        size--;

    }

    private Node remove(Node node, String word, int index) {

        if(index == word.length()){
            if(node.next == null || node.next.isEmpty()){
                return null;
            }
            node.isWorld = false;
            return node;
        }

        char c = word.charAt(index);
        Node nextNode = node.next.get(c);

        nextNode = remove(nextNode,word,index+1);

        if(nextNode == null){
            node.next.remove(c);
        }

        if(node.next == null || node.next.isEmpty()){
            return null;
        }
        return node;
    }

    private Node getNode(String word){
        if(word == null || word.length() == 0){
            return null;
        }

        Node curNode = root;
        for (int i = 0 ; i < word.length() ; i++){
            Character c = word.charAt(i);

            Node nextNode = curNode.next.get(c);
            if(nextNode == null){
                return null;
            }
            curNode = nextNode;
        }
        return curNode;
    }

    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder("Trie size = ").append(size);
        sbr.append("\nroot:\n");
        generateTrieString(root, 1,sbr);
        return sbr.toString();
    }

    private void generateTrieString(Node node, int depth, StringBuilder sbr) {
        if(node.next == null){
            return;
        }

        for (Character c : node.next.keySet()){
            for (int i = 0 ; i < depth ; i++){
                sbr.append("-");
            }
            sbr.append(c).append("\n");
            generateTrieString(node.next.get(c), depth+1, sbr);
        }
    }

}
