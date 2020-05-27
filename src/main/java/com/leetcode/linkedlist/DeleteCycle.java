package com.leetcode.linkedlist;

import com.leetcode.datastructures.ListNode;

/**
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 */
public class DeleteCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if(head == null || head.next == null) return null;
        while(fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                fast = head;
                while(fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
