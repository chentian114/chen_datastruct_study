package com.chen.data.struct.leetcode;

/**
 * @desc Leetcode 203. 移除链表元素
 * @Author Chentian
 * @date 2020/11/5
 */
public class LC203RemoveList {

    private static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }

        ListNode prev = head;
        while (prev.next !=null){
            ListNode node = prev.next;
            if (node.val == val){
                prev.next = node.next;
            }else {
                prev = prev.next;
            }
        }
        if(head.val == val){
            head = head.next;
        }
        return head;
    }



    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next !=null){
            ListNode node = prev.next;
            if (node.val == val){
                prev.next = node.next;
            }else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }




}
