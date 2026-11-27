# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution(object):
    def reverseKGroup(self, head, k):
        """
        :type head: Optional[ListNode]
        :type k: int
        :rtype: Optional[ListNode]
        """
        dummy = ListNode(0, head)
        group_prev = dummy

        def get_kth_node(curr, steps):
            while curr and steps > 0:
                curr = curr.next
                steps -= 1
            return curr

        while True:
            # Check if there are at least k nodes remaining
            kth = get_kth_node(group_prev, k)
            if not kth:
                break

            group_next = kth.next

            # Reverse the k nodes between group_prev and group_next
            prev, curr = group_next, group_prev.next
            while curr != group_next:
                temp = curr.next
                curr.next = prev
                prev = curr
                curr = temp

            # Re-link the reversed segment with the previous part
            new_group_end = group_prev.next
            group_prev.next = kth
            group_prev = new_group_end

        return dummy.next