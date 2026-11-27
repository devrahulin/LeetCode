class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        # Ensure nums1 is the smaller array to minimize the binary search range
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1

        m, n = len(nums1), len(nums2)
        low, high = 0, m
        half_len = (m + n + 1) // 2

        while low <= high:
            partition1 = (low + high) // 2
            partition2 = half_len - partition1

            # Boundaries for nums1
            max_left1 = float('-inf') if partition1 == 0 else nums1[partition1 - 1]
            min_right1 = float('inf') if partition1 == m else nums1[partition1]

            # Boundaries for nums2
            max_left2 = float('-inf') if partition2 == 0 else nums2[partition2 - 1]
            min_right2 = float('inf') if partition2 == n else nums2[partition2]

            # Check if partition is valid
            if max_left1 <= min_right2 and max_left2 <= min_right1:
                # If total length is odd, the median is the max of the left half
                if (m + n) % 2 == 1:
                    return float(max(max_left1, max_left2))
                # If total length is even, it is the average of middle elements
                return (max(max_left1, max_left2) + min(min_right1, min_right2)) / 2.0
            elif max_left1 > min_right2:
                # partition1 is too far right; move left
                high = partition1 - 1
            else:
                # partition1 is too far left; move right
                low = partition1 + 1