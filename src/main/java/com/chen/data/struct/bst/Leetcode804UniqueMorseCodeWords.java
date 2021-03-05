package com.chen.data.struct.bst;

/**
 * @author: Chentian
 * @date: Created in 2021/3/5 5:49
 * @desc  唯一摩尔斯密码词
 * https://leetcode-cn.com/problems/unique-morse-code-words/
 */
public class Leetcode804UniqueMorseCodeWords {

    String[] keys = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        if(words == null || words.length == 0){
            return 0;
        }

        BSTree<String> bst = new BSTree<>();
        for (int i = 0 ; i < words.length ; i++){
            String morse = convertMorseCode(words[i]);
            bst.add(morse);
        }
        return bst.size();
    }

    private String convertMorseCode(String word) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0 ; i < word.length() ; i++){
            int index = word.charAt(i) - 'a';
            sbr.append(keys[index]);
        }
        return sbr.toString();
    }

    public static void main(String[] args) {

        Leetcode804UniqueMorseCodeWords morse = new Leetcode804UniqueMorseCodeWords();
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(morse.uniqueMorseRepresentations(words));

    }
}
