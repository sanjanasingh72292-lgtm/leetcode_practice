/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Compute length and get the tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Reduce k
        k = k % length;
        if (k == 0) return head;

        // 3. Make the list circular
        tail.next = head;

        // 4. Find the new tail: (length - k) steps forward from the old tail
        ListNode newTail = tail;
        for (int i = 0; i < length - k; i++) {
            newTail = newTail.next;
        }

        // 5. Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}