package com.chen.data.struct.list;

import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/26
 */
public class ListAlgorithmComparator {


    public enum ListMethodEnum{
        add,addFirst,addLast,set,get,getFirst,getLast,contains,remove,removeFirst,removeLast,removeElement,size,isEmpty
    }

    public static java.util.LinkedList<Integer> comparator(){
        return new java.util.LinkedList<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static ListMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        ListMethodEnum[] methodEnums = new ListMethodEnum[maxSize];
        ListMethodEnum[] values = ListMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            methodEnums[i] = values[random.nextInt(values.length)];
        }
        return methodEnums;
    }


    public static boolean isEqual(List<Integer> list, java.util.LinkedList<Integer> correctList, ListMethodEnum[] methodEnums, int[] data){
        Random random = new Random();
        for (int i = 0 ; i < methodEnums.length ; i++){
            ListMethodEnum cmd = methodEnums[i];
            switch (cmd){
                case add:
                    for (int j = 0 ; j < data.length ; j++){
                        correctList.add(j,data[j]);
                        list.add(j,data[j]);
                    }
                    break;
                case addFirst:
                    correctList.addFirst(data[0]);
                    list.addFirst(data[0]);
                    break;
                case addLast:
                    correctList.addLast(data[0]);
                    list.addLast(data[0]);
                    break;
                case set:
                    if(!correctList.isEmpty()){
                        int index = correctList.size() <= 1 ? 0 : random.nextInt(correctList.size());
                        correctList.set(index,data[0]);
                        list.set(index,data[0]);
                    }
                    break;
                case get:
                    if(!correctList.isEmpty()){
                        int index = correctList.size() <= 1 ? 0 : random.nextInt(correctList.size());
                        Integer get1 = correctList.get(index);
                        Integer get2 = list.get(index);
                        if(!get1.equals(get2)){
                            System.out.println(cmd+" result1="+get1+" result2="+get2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case getFirst:
                    if(!correctList.isEmpty()){
                        Integer get1 = correctList.getFirst();
                        Integer get2 = list.getFirst();
                        if(!get1.equals(get2)){
                            System.out.println(cmd+" result1="+get1+" result2="+get2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case getLast:
                    if(!correctList.isEmpty()){
                        Integer get1 = correctList.getLast();
                        Integer get2 = list.getLast();
                        if(!get1.equals(get2)){
                            System.out.println(cmd+" result1="+get1+" result2="+get2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case contains:
                    if(!correctList.isEmpty()){
                        int index = correctList.size() <= 1 ? 0 : random.nextInt(correctList.size());
                        boolean contains1 = correctList.contains(correctList.get(index));
                        boolean contains2 = list.contains(correctList.get(index));
                        if(contains1 != contains2){
                            System.out.println(cmd+" result1="+contains1+" result2="+contains2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case remove:
                    if(!correctList.isEmpty()){
                        int index = correctList.size() <= 1 ? 0 : random.nextInt(correctList.size());
                        Integer remove1 = correctList.remove(index);
                        Integer remove2 = list.remove(index);
                        if(!remove1.equals(remove2)){
                            System.out.println(cmd+" result1="+remove1+" result2="+remove2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case removeFirst:
                    if(!correctList.isEmpty()){
                        Integer remove1 = correctList.removeFirst();
                        Integer remove2 = list.removeFirst();
                        if(!remove1.equals(remove2)){
                            System.out.println(cmd+" result1="+remove1+" result2="+remove2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case removeLast:
                    if(!correctList.isEmpty()){
                        Integer remove1 = correctList.removeLast();
                        Integer remove2 = list.removeLast();
                        if(!remove1.equals(remove2)){
                            System.out.println(cmd+" result1="+remove1+" result2="+remove2);
                            printErrorInfo(list,correctList,methodEnums,data);
                            return false;
                        }
                    }
                    break;
                case removeElement:
                    if(!correctList.isEmpty()){
                        int index = correctList.size() <= 1 ? 0 : random.nextInt(correctList.size());
                        Integer element = correctList.get(index);
                        correctList.remove(element);
                        list.removeElement(element);
                    }
                    break;
                case size:
                    int size1 = correctList.size();
                    int size2 = list.size();
                    if(size1 != size2){
                        System.out.println(cmd+" result1="+size1+" result2="+size2);
                        printErrorInfo(list,correctList,methodEnums,data);
                        return false;
                    }
                    break;
                case isEmpty:
                    boolean empty1 = correctList.isEmpty();
                    boolean empty2 = list.isEmpty();
                    if (empty1 != empty2){
                        System.out.println(cmd+" result1="+empty1+" result2="+empty2);
                        printErrorInfo(list,correctList,methodEnums,data);
                        return false;
                    }
                    break;
            }
        }

        for (int i = 0 ; i < correctList.size() ; i++){
            Integer get1 = correctList.get(i);
            Integer get2 = list.get(i);
            if(!get1.equals(get2)){
                printErrorInfo(list,correctList,methodEnums,data);
                return false;
            }
        }
        return true;
    }

    private static void printErrorInfo(List<Integer> list, java.util.LinkedList<Integer> correctList, ListMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctList ");
        for (int i = 0 ; i <correctList.size() ; i++){
            sbr.append(correctList.get(i));
            if(i != correctList.size()-1){
                sbr.append(",");
            }
        }
        sbr.append("\n");

        sbr.append("list :").append(list.toString());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }

}
