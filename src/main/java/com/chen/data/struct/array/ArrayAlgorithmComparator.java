package com.chen.data.struct.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/25
 */
public class ArrayAlgorithmComparator {




    public enum ArrayMethodEnum{
        isEmpty,getSize,add,addLast,addFirst,get,contains,set,remove,removeFirst,removeLast,removeElement
    }

    /**
     * 绝对正确的实现
     * @return 正确的数组实现
     */
    public static java.util.ArrayList<Integer> comparator(){
        return new java.util.ArrayList<>();
    }

    /**
     * 随机生成样本数据
     * @param maxSize 数组最大容量
     * @param maxValue 值域参照值
     * @return 随机样本数据
     */
    public static int[] generateRandomData(int maxSize, int maxValue){

        Random random = new Random();
        int[] data = new int[maxSize];
        for (int i = 0 ; i < maxSize ; i ++) {
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    /**
     * 随机生成操作方法数据
     * @param maxSize 数组最大容量
     * @return 随机操作方法数据
     */
    public static java.util.List<ArrayMethodEnum> generateRandomMethod(int maxSize){
        Random random = new Random();
        ArrayMethodEnum[] enums = ArrayMethodEnum.values();
        java.util.List<ArrayMethodEnum> cmdList = new java.util.ArrayList<>();
        for (int i = 0 ; i < maxSize ; i ++) {
            cmdList.add(enums[random.nextInt(enums.length)]) ;
        }
        return cmdList;
    }

    /**
     * 比较两种实现的处理结果
     * @param array 自定义数组实现
     * @param cmdList  随机操作方法数组
     * @param data  随机样要数据
     * @return  执行操作方法结果是否一致
     */
    public static boolean isEqual(Array<Integer> array,java.util.List<Integer> correctList, java.util.List<ArrayMethodEnum> cmdList, int[] data){
        Random random = new Random();

        for (ArrayMethodEnum cmd : cmdList){
            switch (cmd){
                case isEmpty:
                    boolean empty1 = array.isEmpty();
                    boolean empty2 = correctList.isEmpty();
                    if(empty1 != empty2){
                        System.out.println(cmd+" empty1="+empty1+" empty2="+empty2);
                        printErrorInfo(array,correctList,cmdList,data);
                        return false;
                    }
                    break;
                case getSize:
                    int size1 = array.getSize();
                    int size2 = correctList.size();
                    if(size1 != size2){
                        System.out.println(cmd+" size1="+size1+" size2="+size2);
                        printErrorInfo(array,correctList,cmdList,data);
                        return false;
                    }
                    break;
                case add:
                    for (int i = 0 ; i < data.length ; i++){
                        int max = correctList.size() > 1 ?  correctList.size() : 1;
                        int index = random.nextInt(max);
                        array.add(index,data[i]);
                        correctList.add(index,data[i]);
                    }
                    break;
                case addLast:
                    for (int i = 0 ; i < data.length ; i++){
                        array.addLast(data[i]);
                        correctList.add(correctList.size(),data[i]);
                    }
                    break;
                case addFirst:
                    for (int i = 0 ; i < data.length ; i++){
                        array.addFirst(data[i]);
                        correctList.add(0,data[i]);
                    }
                    break;
                case get:
                    if(!correctList.isEmpty()) {
                        int max = correctList.size() > 1 ?  correctList.size() : 1;
                        int index = random.nextInt(max);
                        Integer get1 = array.get(index);
                        Integer get2 = correctList.get(index);
                        if (!get1.equals(get2)) {
                            System.out.println(cmd + " get1=" + get1 + " get2=" + get2);
                            printErrorInfo(array, correctList, cmdList, data);
                            return false;
                        }
                    }
                    break;
                case contains:
                    if(!correctList.isEmpty()){
                        int max = correctList.size() > 1 ?  correctList.size() : 1;
                        int index = random.nextInt(max);
                        Integer element = correctList.get(index);
                        boolean contains1 = correctList.contains(element);
                        boolean contains2 = array.contains(element);
                        if(contains1 != contains2){
                            System.out.println(cmd+" contains1="+contains1+" contains2="+contains2);
                            printErrorInfo(array,correctList,cmdList,data);
                            return false;
                        }
                    }
                    break;
                case set:
                    if(!correctList.isEmpty()){
                        int max = correctList.size() > 1 ?  correctList.size() : 1;
                        int index = random.nextInt(max);
                        correctList.set(index,data[0]);
                        array.set(index,data[0]);
                    }
                    break;
                case remove:
                    if(!correctList.isEmpty()){
                        int max = correctList.size() > 1 ?  correctList.size() : 1;
                        int index = random.nextInt(max);
                        correctList.remove(index);
                        array.remove(index);
                    }
                    break;
                case removeFirst:
                    if(!correctList.isEmpty()){
                        correctList.remove(0);
                        array.removeFirst();
                    }
                    break;
                case removeLast:
                    if(!correctList.isEmpty()){
                        correctList.remove(correctList.size()-1);
                        array.removeLast();
                    }
                    break;
                case removeElement:
                    if(!correctList.isEmpty()){
                        int max = correctList.size() > 1 ?  correctList.size() : 1;
                        int index = random.nextInt(max);
                        Integer element = correctList.get(index);
                        correctList.remove(element);
                        array.removeElement(element);
                    }
                    break;
            }
        }

        for (int i = 0 ; i < correctList.size() ; i++){
            if(!correctList.get(i).equals(array.get(i))){
                printErrorInfo(array,correctList,cmdList,data);
                return false;
            }
        }

        return true;
    }


    private static void printErrorInfo(Array<Integer> array,java.util.List correctList, java.util.List<ArrayMethodEnum> cmdList, int[] data){

        StringBuilder sbr = new StringBuilder("-------------->comparator error!!!<-------------------\n");
        sbr.append("cmdList [");
        for (int i = 0 ; i < cmdList.size() ; i++){
            sbr.append(cmdList.get(i).name());
            if(i != cmdList.size()-1){
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

        sbr.append("correctList [");
        for (int i = 0 ; i < correctList.size() ; i++){
            sbr.append(correctList.get(i));
            if(i != correctList.size()-1){
                sbr.append(",");
            }
        }
        sbr.append("]\n");

        sbr.append("array [");
        for (int i = 0 ; i < array.getSize() ; i++){
            sbr.append(array.get(i));
            if(i != array.getSize() -1){
                sbr.append(",");
            }
        }
        sbr.append("]\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }

    public static void main(String[] args) {

        int[] data = {1,2,3};
        List<Integer> correctList = new ArrayList<>();
        for (int i = 0 ; i < data.length ; i++){
            correctList.add(correctList.size(),data[i]);
        }
        System.out.println(correctList.size());
    }

}
