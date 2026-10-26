import java.util.ArrayDeque

class Solution {
    fun countIslands(grid: Array<IntArray>, k: Int): Int {
        val m = grid.size
        val n = grid[0].size
        var count = 0

        val dirs = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0)
        )

        val queue = ArrayDeque<Int>()

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] > 0) {
                    var sum = 0L
                    queue.add(i * n + j)
                    sum += grid[i][j]
                    grid[i][j] = 0

                    while (queue.isNotEmpty()) {
                        val curr = queue.poll()
                        val r = curr / n
                        val c = curr % n

                        for (d in dirs) {
                            val nr = r + d[0]
                            val nc = c + d[1]

                            if (nr in 0 until m && nc in 0 until n && grid[nr][nc] > 0) {
                                sum += grid[nr][nc]
                                grid[nr][nc] = 0
                                queue.add(nr * n + nc)
                            }
                        }
                    }

                    if (sum % k == 0L) {
                        count++
                    }
                }
            }
        }

        return count
    }

    fun numIslands(grid: Array<IntArray>, k: Int): Int {
        return countIslands(grid, k)
    }
}