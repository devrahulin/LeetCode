# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution(object):
    def swapPairs(self, head):
        """
        :type head: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        dummy = ListNode(0, head)
        prev = dummy

        # Ensure there are at least two nodes left to swap
        while prev.next and prev.next.next:
            first = prev.next
            second = prev.next.next

            # Rearrange pointers to swap first and second
            prev.next = second
            first.next = second.next
            second.next = first

            # Move prev to the end of the swapped pair
            prev = first

        return dummy.next