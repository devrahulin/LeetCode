class Solution:
    def jump(self, nums: list[int]) -> int:
        n = len(nums)
        if n <= 1:
            return 0

        jumps = 0
        current_end = 0
        farthest = 0

        # Traverse up to n - 2 because reaching or crossing n - 1 is the goal
        for i in range(n - 1):
            farthest = max(farthest, i + nums[i])

            # Reached the boundary of the current jump
            if i == current_end:
                jumps += 1
                current_end = farthest

                # Early exit if we can already reach the destination
                if current_end >= n - 1:
                    break

        return jumps