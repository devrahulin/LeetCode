class Solution:
    def permuteUnique(self, nums: list[int]) -> list[list[int]]:
        nums.sort()
        result = []
        used = [False] * len(nums)

        def backtrack(path: list[int]) -> None:
            # Base case: full permutation constructed
            if len(path) == len(nums):
                result.append(path[:])
                return

            for i in range(len(nums)):
                # If element is already used in current branch, skip it
                if used[i]:
                    continue

                # Skip duplicate elements at the same decision level
                if i > 0 and nums[i] == nums[i - 1] and not used[i - 1]:
                    continue

                # Choose
                used[i] = True
                path.append(nums[i])

                # Recurse
                backtrack(path)

                # Backtrack
                path.pop()
                used[i] = False

        backtrack([])
        return result