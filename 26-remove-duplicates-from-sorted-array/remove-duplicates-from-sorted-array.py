class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0

        # Pointer k tracks the index of the last unique element placed
        k = 0

        # Scan through the array with a fast pointer
        for i in range(1, len(nums)):
            # When a new unique value is found, move k forward and overwrite
            if nums[i] != nums[k]:
                k += 1
                nums[k] = nums[i]

        # The count of unique elements is k + 1
        return k + 1