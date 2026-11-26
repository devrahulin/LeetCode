class Solution:
    def rob(self, nums: list[int]) -> int:
        prev2 = 0  # Max money robbed up to house i - 2
        prev1 = 0  # Max money robbed up to house i - 1

        for num in nums:
            current = max(prev1, prev2 + num)
            prev2 = prev1
            prev1 = current

        return prev1