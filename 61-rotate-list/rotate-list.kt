class Solution {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null || head.next == null || k == 0) {
            return head
        }

        var length = 1
        var tail = head
        while (tail?.next != null) {
            tail = tail.next
            length++
        }

        val effectiveK = k % length
        if (effectiveK == 0) {
            return head
        }

        tail?.next = head

        var stepsToNewTail = length - effectiveK
        var newTail = head
        while (stepsToNewTail > 1) {
            newTail = newTail?.next
            stepsToNewTail--
        }

        val newHead = newTail?.next
        newTail?.next = null

        return newHead
    }
}