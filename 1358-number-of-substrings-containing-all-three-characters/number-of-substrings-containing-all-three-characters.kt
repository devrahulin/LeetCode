import kotlin.math.min

class Solution {
    fun numberOfSubstrings(s: String): Int {
        val lastSeen = intArrayOf(-1, -1, -1)
        var count = 0

        for (i in s.indices) {
            lastSeen[s[i] - 'a'] = i
            val minIndex = min(lastSeen[0], min(lastSeen[1], lastSeen[2]))
            count += minIndex + 1
        }

        return count
    }
}