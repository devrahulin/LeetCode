class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0
        }

        val dp = IntArray(n)
        dp[0] = 1

        for (r in 0 until m) {
            for (c in 0 until n) {
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0
                } else if (c > 0) {
                    dp[c] += dp[c - 1]
                }
            }
        }

        return dp[n - 1]
    }
}