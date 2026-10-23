/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;

        Deque<Node> stack = new ArrayDeque<>();
        Node curr = head;

        while (curr != null) {
            if (curr.child != null) {
                // Save the original next pointer to visit after the child branch
                if (curr.next != null) {
                    stack.push(curr.next);
                }

                // Splice child list as next
                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null;
            } else if (curr.next == null && !stack.isEmpty()) {
                // End of a branch reached: reconnect to the saved next node
                Node nextNode = stack.pop();
                curr.next = nextNode;
                nextNode.prev = curr;
            }

            curr = curr.next;
        }

        return head;
    }
}