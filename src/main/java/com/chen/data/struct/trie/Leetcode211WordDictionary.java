package com.chen.data.struct.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 添加与搜索单词 - 数据结构设计
 * @Author Chentian
 * @date 2021/3/11
 * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 */
public class Leetcode211WordDictionary {

    class Node{
        boolean isWorld;
        Map<Character,Node> next;
        Node(){
            isWorld = false;
            next = new HashMap<>();
        }
    }

    private Node root ;

    public Leetcode211WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
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

    public boolean search(String word) {
        return search(root,word,0);

    }

    private boolean search(Node node, String word, int index){

        if(index == word.length()){
            if(node.isWorld){
                return true;
            }
            return false;
        }

        Character c = word.charAt(index);
        if('.' != c){
            Node node1 = node.next.get(c);
            if(node1 == null){
                return false;
            }
            return search(node1, word, index+1);
        }else {
            if(node.next == null){
                return false;
            }
            for (Character key : node.next.keySet()) {
                boolean search = search(node.next.get(key), word, index + 1);
                if(search){
                    return true;
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {

        Leetcode211WordDictionary wordDictionary = new Leetcode211WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True

    }
}
