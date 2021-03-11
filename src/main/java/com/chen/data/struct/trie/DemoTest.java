package com.chen.data.struct.trie;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/11
 */
public class DemoTest {

    public static void main(String[] args) {

        Trie trie = new Trie();

        String[] words = {"cat","dog","deer","panda"};
        for (String word : words){
            trie.add(word);
            System.out.println(trie);
        }

        System.out.println(trie.contains("cat"));
        System.out.println(trie.contains("ca"));
        System.out.println(trie.contains("catt"));
        System.out.println(trie.isPrefix("ca"));
        System.out.println(trie.isPrefix("cab"));

        trie.remove("deer");
        System.out.println(trie);
        trie.remove("panda");
        System.out.println(trie);
        trie.add("deer");
        System.out.println(trie);

        testByComparator(1,10,10,10);
    }


    private static void testByComparator(int testTimes,int maxSize,int maxValueSize,int maxMethodSize){

        boolean result = false;
        for (int i = 0 ; i < testTimes; i++){

            Trie trie = new Trie();
            CorrectTrie correctTrie = TrieAlgorithmComparator.comparator();

            String[] data = TrieAlgorithmComparator.generateRandom(maxSize, maxValueSize);
            TrieAlgorithmComparator.TrieMethodEnum[] methodEnums = TrieAlgorithmComparator.generateRandomMethod(maxMethodSize);

            result = TrieAlgorithmComparator.isEqual(correctTrie, trie, methodEnums, data);
            if(!result){
                return;
            }
        }
        if(result){
            System.out.println("----------->comparator is ok!<---------------");
        }
    }
}
