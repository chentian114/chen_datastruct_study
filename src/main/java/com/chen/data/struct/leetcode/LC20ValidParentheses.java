package com.chen.data.struct.leetcode;

import java.util.Stack;

/**
 * @desc Leetcode练习-20 Valid Parentheses 匹配括号，使用java.util.Stack
 * @Author Chentian
 * @date 2020/10/26
 * 结果： 通过	  2 ms	36.4 MB
 */

public class LC20ValidParentheses {

    public boolean isValid(String s) {
        if(s == null || s.length() < 1 ){
            return true;
        }

        if(s.length() % 2 != 0 ){
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }
            else {
                if(stack.isEmpty()){
                    return false;
                }
                char p = stack.pop();
                if( ( p == '(' && c != ')' ) || ( p == '[' && c != ']') || ( p == '{' && c != '}') ){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String demo1 = "()[]{}";
        boolean result = new LC20ValidParentheses().isValid(demo1);
        System.out.println(result);

        String demo2 = "(]";
        System.out.println(new LC20ValidParentheses().isValid(demo2));

        String demo3 = "}{";
        System.out.println(new LC20ValidParentheses().isValid(demo3));
    }

}
