class Solution:
    def firstMissingPositive(self, nums: list[int]) -> int:
        n = len(nums)

        # Place each number in its target index if 1 <= nums[i] <= n
        for i in range(n):
            while 1 <= nums[i] <= n and nums[nums[i] - 1] != nums[i]:
                # Swap nums[i] with the number at its target destination nums[i] - 1
                target_idx = nums[i] - 1
                nums[i], nums[target_idx] = nums[target_idx], nums[i]

        # Find the first index that doesn't hold i + 1
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1

        # If all numbers 1..n are present, the answer is n + 1
        return n + 1