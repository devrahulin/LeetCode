class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        max_f = max(f for f, _ in items)
        freq = [0] * (max_f + 1)
        min_p = budget + 1
        for f, p in items:
            freq[f] += 1
            if p < min_p:
                min_p = p

        mult_count = [0] * (max_f + 1)
        for f in range(1, max_f + 1):
            if freq[f]:
                total = 0
                for m in range(f, max_f + 1, f):
                    total += freq[m]
                mult_count[f] = total - 1

        dp = [0] * (budget + 1)
        for f, p in items:
            if p <= budget:
                val = 1 + mult_count[f]
                for b in range(budget, p - 1, -1):
                    nv = dp[b - p] + val
                    if nv > dp[b]:
                        dp[b] = nv

        for b in range(min_p, budget + 1):
            nv = dp[b - min_p] + 1
            if nv > dp[b]:
                dp[b] = nv

        return dp[budget]
