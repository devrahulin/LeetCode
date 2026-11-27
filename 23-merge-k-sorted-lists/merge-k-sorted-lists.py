import heapq

# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[Optional[ListNode]]
        :rtype: Optional[ListNode]
        """
        heap = []
        dummy = ListNode(0)
        tail = dummy

        # Push the head of each non-empty linked list into the heap
        # Include an index `i` as a tie-breaker to prevent comparing ListNode instances directly
        for i, node in enumerate(lists):
            if node:
                heapq.heappush(heap, (node.val, i, node))

        while heap:
            val, i, smallest_node = heapq.heappop(heap)
            tail.next = smallest_node
            tail = tail.next

            # If the extracted node has a next node, push it into the heap
            if smallest_node.next:
                heapq.heappush(heap, (smallest_node.next.val, i, smallest_node.next))

        return dummy.next