package com.chen.data.struct.stack;

import java.util.Arrays;
import java.util.Random;

/**
 * @desc
 * @Author Chentian
 * @date 2021/2/26
 */
public class StackAlgorithmComparator {
    public enum StackMethodEnum{
        push,pop,peek,getSize,isEmpty
    }

    public static java.util.Stack<Integer> comparator(){
        return new java.util.Stack<>();
    }

    public static int[] generateRandomData(int maxSize, int maxValue){
        Random random = new Random();
        int[] data = new int[maxSize];

        for (int i = 0 ; i < maxSize ; i++){
            data[i] = random.nextInt(maxValue);
        }
        return data;
    }

    public static StackMethodEnum[] generateRandomMethod(int maxSize){
        Random random = new Random();
        StackMethodEnum[] cmds = new StackMethodEnum[maxSize];
        StackMethodEnum[] values = StackMethodEnum.values();
        for (int i = 0 ; i < maxSize ; i++){
            cmds[i] = values[random.nextInt(values.length)];
        }
        return cmds;
    }

    public static boolean isEqual(Stack<Integer> stack, java.util.Stack<Integer> correctStack,StackMethodEnum[] methodEnums,int[] data){
        for (int i = 0 ; i < methodEnums.length ; i ++){
            StackMethodEnum cmd = methodEnums[i];
            switch (cmd){
                case push:
                    for (int j = 0 ; j < data.length ; j++){
                        stack.push(data[i]);
                        correctStack.push(data[i]);
                    }
                    break;
                case pop:
                    if(correctStack.isEmpty()){
                        continue;
                    }
                    Integer pop1 = stack.pop();
                    Integer pop2 = correctStack.pop();
                    if(!pop1.equals(pop2)){
                        System.out.println(cmd+" error! pop1="+pop1+" pop2="+pop2);
                        printErrorInfo(stack,correctStack,methodEnums,data);
                        return false;
                    }
                    break;
                case peek:
                    if(correctStack.isEmpty()){
                        continue;
                    }
                    Integer peek1 = stack.peek();
                    Integer peek2 = correctStack.peek();
                    if(!peek1.equals(peek2)){
                        System.out.println(cmd+" error! peek1="+peek1+" peek2="+peek2);
                        printErrorInfo(stack,correctStack,methodEnums,data);
                        return false;
                    }
                    break;
                case getSize:
                    int size1 = stack.getSize();
                    int size2 = correctStack.size();
                    if(size1!=size2){
                        System.out.println(cmd+" error! size1="+size1+" size2="+size2);
                        printErrorInfo(stack,correctStack,methodEnums,data);
                        return false;
                    }
                    break;
                case isEmpty:
                    boolean empty1 = stack.isEmpty();
                    boolean empty2 = correctStack.isEmpty();
                    if(empty1 != empty2){
                        System.out.println(cmd+" error! empty1="+empty1+" empty2="+empty2);
                        printErrorInfo(stack,correctStack,methodEnums,data);
                        return false;
                    }
                    break;
            }
        }

        for (int i = 0 ; i < correctStack.size() ; i++){
            Integer pop1 = correctStack.pop();
            Integer pop2 = stack.pop();
            if(!pop1.equals(pop2)){
                printErrorInfo(stack,correctStack,methodEnums,data);
                return false;
            }
        }


        return true;
    }

    private static void printErrorInfo(Stack<Integer> stack, java.util.Stack<Integer> correctStack, StackMethodEnum[] methodEnums, int[] data) {
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

        sbr.append("correctStack ").append(Arrays.toString(correctStack.toArray()));
        sbr.append("\n");

        sbr.append("stack :").append(stack.toString());
        sbr.append("\n");
        sbr.append("-------------->comparator error!!!<-------------------\n");
        System.out.println(sbr.toString());
    }


}



