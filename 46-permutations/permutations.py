class Solution:
    def permute(self, nums: list[int]) -> list[list[int]]:
        result = []
        n = len(nums)

        def backtrack(start: int) -> None:
            # Base case: reached the end, record current permutation
            if start == n:
                result.append(nums[:])
                return

            for i in range(start, n):
                # Place nums[i] at position `start`
                nums[start], nums[i] = nums[i], nums[start]
                # Recurse to generate permutations for the remaining subarray
                backtrack(start + 1)
                # Backtrack: revert the swap
                nums[start], nums[i] = nums[i], nums[start]

        backtrack(0)
        return result