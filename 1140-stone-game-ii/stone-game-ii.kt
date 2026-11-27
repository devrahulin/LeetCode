import kotlin.math.max

class Solution {
    fun stoneGameII(piles: IntArray): Int {
        val n = piles.size
        val suffixSum = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            suffixSum[i] = suffixSum[i + 1] + piles[i]
        }

        val memo = Array(n) { IntArray(n + 1) }

        fun dp(i: Int, m: Int): Int {
            if (i >= n) return 0
            if (i + 2 * m >= n) return suffixSum[i]
            if (memo[i][m] != 0) return memo[i][m]

            var minOpponent = Int.MAX_VALUE
            for (x in 1..(2 * m)) {
                minOpponent = minOf(minOpponent, dp(i + x, max(m, x)))
            }

            memo[i][m] = suffixSum[i] - minOpponent
            return memo[i][m]
        }

        return dp(0, 1)
    }
}