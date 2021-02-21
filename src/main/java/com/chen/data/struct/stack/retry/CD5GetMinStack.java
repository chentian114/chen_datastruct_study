package com.chen.data.struct.stack.retry;

import com.chen.data.struct.stack.Stack;

import java.util.Scanner;

/**
 * @author: Chentian
 * @date: Created in 2021/2/21 7:06
 * @desc
 */
public class CD5GetMinStack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new ArrayStack<>();
        Stack<Integer> minStack = new ArrayStack<>();

        int num = scanner.nextInt();
        for (int i = 0 ; i < num ; i++){
            String cmd = scanner.next();

            if("push".equals(cmd)){
                int val = scanner.nextInt();
                stack.push(val);
                if(minStack.isEmpty() || val <= minStack.peek()){
                    minStack.push(val);
                }
            }

            if("pop".equals(cmd)){
                int val = stack.pop();
                if(val == minStack.peek()){
                    minStack.pop();
                }
            }

            if("getMin".equals(cmd)){
                System.out.println(minStack.peek());
            }
        }
    }

}
