import math

class Solution:
    def numberOfSets(self, n: int, k: int) -> int:
        MOD = 10**9 + 7
        # The number of ways to choose k non-overlapping segments among n points
        # is equal to choosing 2k points from (n + k - 1) points: C(n + k - 1, 2k)
        return math.comb(n + k - 1, 2 * k) % MOD