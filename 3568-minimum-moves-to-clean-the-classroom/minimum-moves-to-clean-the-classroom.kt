import java.util.ArrayDeque

class Solution {
    fun minMoves(classroom: Array<String>, energy: Int): Int {
        val m = classroom.size
        val n = classroom[0].length

        var startR = -1
        var startC = -1
        val litterList = ArrayList<Pair<Int, Int>>()

        for (r in 0 until m) {
            for (c in 0 until n) {
                when (classroom[r][c]) {
                    'S' -> {
                        startR = r
                        startC = c
                    }
                    'L' -> litterList.add(Pair(r, c))
                }
            }
        }

        val totalLitter = litterList.size
        if (totalLitter == 0) return 0

        val litterMap = Array(m) { IntArray(n) { -1 } }
        for (i in 0 until totalLitter) {
            val (r, c) = litterList[i]
            litterMap[r][c] = i
        }

        val numMasks = 1 shl totalLitter
        val maxEnergy = Array(numMasks) { Array(m) { IntArray(n) { -1 } } }

        val queue = ArrayDeque<Long>()

        fun encode(mask: Int, r: Int, c: Int, rem: Int): Long {
            return (mask.toLong() shl 16) or (r.toLong() shl 11) or (c.toLong() shl 6) or rem.toLong()
        }

        maxEnergy[0][startR][startC] = energy
        queue.add(encode(0, startR, startC, energy))

        val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
        val targetMask = (1 shl totalLitter) - 1

        var moves = 0

        while (queue.isNotEmpty()) {
            val size = queue.size
            for (step in 0 until size) {
                val state = queue.poll()
                val mask = (state shr 16).toInt()
                val r = ((state shr 11) and 31L).toInt()
                val c = ((state shr 6) and 31L).toInt()
                val rem = (state and 63L).toInt()

                if (mask == targetMask) {
                    return moves
                }

                if (rem < maxEnergy[mask][r][c]) continue
                if (rem == 0) continue

                for (d in dirs) {
                    val nr = r + d[0]
                    val nc = c + d[1]

                    if (nr !in 0 until m || nc !in 0 until n) continue
                    val cell = classroom[nr][nc]
                    if (cell == 'X') continue

                    val nextRem = rem - 1
                    val finalRem = if (cell == 'R') energy else nextRem

                    var nextMask = mask
                    val litterIdx = litterMap[nr][nc]
                    if (litterIdx != -1) {
                        nextMask = nextMask or (1 shl litterIdx)
                    }

                    if (finalRem > maxEnergy[nextMask][nr][nc]) {
                        maxEnergy[nextMask][nr][nc] = finalRem
                        queue.add(encode(nextMask, nr, nc, finalRem))
                    }
                }
            }
            moves++
        }

        return -1
    }
}