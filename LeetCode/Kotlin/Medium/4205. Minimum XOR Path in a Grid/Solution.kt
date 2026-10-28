class Solution {
    fun minCost(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val maxVal = 1024

        // dp[j] stores a BitSet/BooleanArray of reachable XOR sums at column j in the current row
        val dp = Array(n) { java.util.BitSet(maxVal) }

        dp[0].set(grid[0][0])

        for (j in 1 until n) {
            val v = grid[0][j]
            var k = dp[j - 1].nextSetBit(0)
            while (k >= 0) {
                dp[j].set(k xor v)
                k = dp[j - 1].nextSetBit(k + 1)
            }
        }

        for (i in 1 until m) {
            val nextDp = Array(n) { java.util.BitSet(maxVal) }

            for (j in 0 until n) {
                val v = grid[i][j]

                var k = dp[j].nextSetBit(0)
                while (k >= 0) {
                    nextDp[j].set(k xor v)
                    k = dp[j].nextSetBit(k + 1)
                }

                if (j > 0) {
                    k = nextDp[j - 1].nextSetBit(0)
                    while (k >= 0) {
                        nextDp[j].set(k xor v)
                        k = nextDp[j - 1].nextSetBit(k + 1)
                    }
                }
            }

            for (j in 0 until n) {
                dp[j] = nextDp[j]
            }
        }

        return dp[n - 1].nextSetBit(0)
    }
}