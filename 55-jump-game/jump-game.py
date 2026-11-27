class Solution:
    def canJump(self, nums: list[int]) -> bool:
        farthest = 0
        target = len(nums) - 1

        for i, jump in enumerate(nums):
            # If the current index exceeds the farthest reachable point, we cannot proceed
            if i > farthest:
                return False

            # Update the maximum reachable index
            farthest = max(farthest, i + jump)

            # Early termination if the target is already reachable
            if farthest >= target:
                return True

        return True