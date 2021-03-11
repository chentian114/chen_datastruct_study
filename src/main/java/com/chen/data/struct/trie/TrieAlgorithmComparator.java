package com.chen.data.struct.trie;

import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/3/11
 */
public class TrieAlgorithmComparator {

    static char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};

    public enum TrieMethodEnum{
        getSize,add,contains,isPrefix
    }

    public static String[] generateRandom(int maxSize, int maxValSize){
        Random random = new Random();
        int size = random.nextInt(maxSize);
        String[] data = new String[size];
        for (int i = 0 ; i < size; i ++){

            StringBuilder sbr = new StringBuilder();
            for (int j = 0 ; j < random.nextInt(maxValSize); j++){
                sbr.append(alpha[random.nextInt(alpha.length)]);
            }

            if(sbr.toString().length() > 0) {
                data[i] = sbr.toString();
            }else {
                data[i] = "default";
            }
        }
        return data;
    }

    public static TrieMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        TrieMethodEnum[] values = TrieMethodEnum.values();
        TrieMethodEnum[] methodEnums = new TrieMethodEnum[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }

    public static CorrectTrie comparator(){
        return new CorrectTrie();
    }

    public static boolean isEqual(CorrectTrie correctTrie, Trie trie, TrieMethodEnum[] methods, String[] data){
        Random random = new Random();
        try {
            for (TrieMethodEnum cmd : methods) {

                switch (cmd) {
                    case getSize:
                        int size1 = correctTrie.getSize();
                        int size2 = trie.getSize();
                        if (size1 != size2) {
                            System.out.println(cmd + " error! result1=" + size1 + " result2=" + size2);
                            printErrorInfo(correctTrie, trie, methods, data);
                            return false;
                        }
                        break;
                    case add:
                        for (int i = 0; i < data.length; i++) {
                            correctTrie.add(data[i]);
                            trie.add(data[i]);
                        }
                        break;
                    case contains:
                        if (correctTrie.getSize() != 0) {
                            for (int i = 0; i < data.length; i++) {
                                boolean contains1 = correctTrie.contains(data[i]);
                                boolean contains2 = trie.contains(data[i]);
                                if (contains1 != contains2) {
                                    System.out.println(cmd + " error! result1=" + contains1 + " result2=" + contains2);
                                    printErrorInfo(correctTrie, trie, methods, data);
                                    return false;
                                }
                            }
                        }
                        break;
                    case isPrefix:
                        if (correctTrie.getSize() != 0) {
                            for (int i = 0; i < data.length; i++) {
                                String prefix = data[i].substring(0, random.nextInt(data[i].length()));
                                boolean isPrefix1 = correctTrie.isPrefix(prefix);
                                boolean isPrefix2 = trie.isPrefix(prefix);
                                if (isPrefix1 != isPrefix2) {
                                    System.out.println(cmd + " error! result1=" + isPrefix1 + " result2=" + isPrefix2);
                                    printErrorInfo(correctTrie, trie, methods, data);
                                    return false;
                                }
                            }
                        }
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            printErrorInfo(correctTrie, trie, methods, data);
            return false;
        }

        return true;
    }

    private static void printErrorInfo(CorrectTrie correctTrie, Trie trie, TrieMethodEnum[] methodEnums, String[] data) {
        StringBuilder sbr = new StringBuilder("-------------->comparator error!!!<-------------------\n");
        sbr.append("cmdList [");
        for (int i = 0 ; i < methodEnums.length ; i++){
            sbr.append(methodEnums[i].name());
            if(i != methodEnums.length-1){
                sbr.append(",");
            }
        }
        sbr.append("]\n");

        sbr.append("data [");
        for (int i = 0 ; i < data.length ; i++){
            sbr.append(data[i]);
            if(i != data.length-1){
                sbr.append(",");
            }
        }
        sbr.append("]\n");

        sbr.append("correctTrie ").append(correctTrie.toString());
        sbr.append("\n");

        sbr.append("trie :").append(trie.toString());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }

}
