package com.chen.data.struct.stack;

import java.util.Scanner;

/**
 * @author: Chentian
 * @date: Created in 2020/12/1 5:44
 * @desc 牛客网-在线编程-程序员代码面试指南-CD5-设计getMin功能的栈
 * 实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 */
public class GetMinStack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GetMinStack stack = new GetMinStack();
        int n = sc.nextInt();
        for (int i = 0 ; i < n ; i++){
            String cmd = sc.next();
            if("push".equals(cmd)){
                int num = sc.nextInt();
                stack.push(num);
            }
            else if("pop".equals(cmd)){
                stack.pop();
            }
            else if("getMin".equals(cmd)){
                System.out.println(stack.getMin());
            }
        }
    }

    //栈元素数据存放数组
    private int[] data = new int[16];
    //栈最小数数据存放数组
    private int[] minData = new int[16];
    //指向下一个元素入栈的位置
    private int index;

    public boolean isEmpty(){
        return index == 0;
    }

    public boolean isFull(){
        return index == data.length;
    }

    public void push(int num){
        if(isFull()){
            resize(data.length*2);
        }

        data[index] = num;
        minData[index] = index == 0 ? num : Math.min(num, minData[index-1]);
        index++;
    }

    public int pop(){
        if(isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }
        index--;

        if(index < data.length/4 && data.length/2 != 0 ){
            resize(data.length/2);
        }
        return data[index];
    }

    public int getMin(){
        if(isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }
        return minData[index-1];
    }

    private void resize(int newCapacity) {
        int[] tmpData = new int[newCapacity];
        int[] tmpMinData = new int[newCapacity];
        for (int i = 0 ; i < index ; i++){
            tmpData[i] = data[i];
            tmpMinData[i] = minData[i];
        }
        data = tmpData;
        minData = tmpMinData;
    }
}
