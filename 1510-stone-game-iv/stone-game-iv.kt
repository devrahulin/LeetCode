class Solution {
    fun winnerSquareGame(n: Int): Boolean {
        val dp = BooleanArray(n + 1)

        for (i in 1..n) {
            var k = 1
            while (k * k <= i) {
                if (!dp[i - k * k]) {
                    dp[i] = true
                    break
                }
                k++
            }
        }

        return dp[n]
    }
}