from collections import deque

class Solution:
    def lexicographicallySmallestArray(self, nums: list[int], limit: int) -> list[int]:
        n = len(nums)
        # Pair each value with its initial index and sort by value
        sorted_pairs = sorted((val, idx) for idx, val in enumerate(nums))
        
        result = [0] * n
        i = 0
        
        while i < n:
            j = i + 1
            # Find the contiguous range of elements belonging to the same group
            while j < n and sorted_pairs[j][0] - sorted_pairs[j - 1][0] <= limit:
                j += 1
            
            # Extract the original indices for this group and sort them
            indices = sorted(sorted_pairs[k][1] for k in range(i, j))
            
            # Place the sorted values into the sorted indices
            for k in range(i, j):
                val = sorted_pairs[k][0]
                target_idx = indices[k - i]
                result[target_idx] = val
            
            i = j
            
        return result