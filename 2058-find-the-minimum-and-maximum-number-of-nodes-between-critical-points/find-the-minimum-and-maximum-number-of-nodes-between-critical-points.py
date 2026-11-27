# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> list[int]:
        # Need at least 3 nodes to have any critical point
        if not head or not head.next or not head.next.next:
            return [-1, -1]

        prev = head
        curr = head.next
        idx = 1  # 0-indexed position of curr

        first_critical = -1
        prev_critical = -1
        min_dist = float('inf')

        while curr.next:
            # Check if current node is a local maxima or minima
            is_maxima = curr.val > prev.val and curr.val > curr.next.val
            is_minima = curr.val < prev.val and curr.val < curr.next.val

            if is_maxima or is_minima:
                if first_critical == -1:
                    first_critical = idx
                else:
                    # Minimum distance is between adjacent critical points
                    min_dist = min(min_dist, idx - prev_critical)

                prev_critical = idx

            prev = curr
            curr = curr.next
            idx += 1

        # Fewer than 2 critical points found
        if min_dist == float('inf'):
            return [-1, -1]

        # Maximum distance is always between the last and the first critical point
        max_dist = prev_critical - first_critical

        return [min_dist, max_dist]