class Solution {
    private val MOD = 1_000_000_007

    fun uniquePaths(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        if (grid[0][0] == 1) return 0
        if (m - 1 == 0 && n - 1 == 0) return 1

        val memo = Array(m) { IntArray(n) { -1 } }

        fun simulateReflection(startR: Int, startC: Int, startDir: Int): Pair<Int, Int>? {
            var r = startR
            var c = startC
            var dir = startDir

            while (true) {
                if (grid[r][c] == 1) {
                    if (dir == 0) {
                        r += 1
                        dir = 1
                    } else {
                        c += 1
                        dir = 0
                    }
                    if (r >= m || c >= n) return null
                } else {
                    return Pair(r, c)
                }
            }
        }

        fun dfs(r: Int, c: Int): Int {
            if (r == m - 1 && c == n - 1) return 1
            if (memo[r][c] != -1) return memo[r][c]

            var ways = 0L

            if (c + 1 < n) {
                val nextPos = simulateReflection(r, c + 1, 0)
                if (nextPos != null) {
                    ways = (ways + dfs(nextPos.first, nextPos.second)) % MOD
                }
            }

            if (r + 1 < m) {
                val nextPos = simulateReflection(r + 1, c, 1)
                if (nextPos != null) {
                    ways = (ways + dfs(nextPos.first, nextPos.second)) % MOD
                }
            }

            memo[r][c] = ways.toInt()
            return memo[r][c]
        }

        return dfs(0, 0)
    }
}
