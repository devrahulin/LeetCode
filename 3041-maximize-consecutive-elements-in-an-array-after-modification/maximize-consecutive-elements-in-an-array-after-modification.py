class Solution:
    def maxSelectedElements(self, nums: list[int]) -> int:
        nums.sort()
        dp = {}
        
        for x in nums:
            # Update x + 1 first so we don't reuse the current x twice
            dp[x + 1] = dp.get(x, 0) + 1
            dp[x] = dp.get(x - 1, 0) + 1
            
        return max(dp.values())
        