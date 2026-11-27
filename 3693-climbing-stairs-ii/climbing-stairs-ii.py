class Solution:
    def climbStairs(self, n: int, costs: list[int]) -> int:
        # dp[i] stores the minimum cost to reach step i
        # We need steps from 0 to n, so size n + 1
        dp = [float('inf')] * (n + 1)
        dp[0] = 0
        
        for i in range(1, n + 1):
            # Check jumps from i - 1, i - 2, i - 3
            for prev in range(max(0, i - 3), i):
                jump_cost = costs[i - 1] + (i - prev) ** 2
                dp[i] = min(dp[i], dp[prev] + jump_cost)
                
        return dp[n]
        