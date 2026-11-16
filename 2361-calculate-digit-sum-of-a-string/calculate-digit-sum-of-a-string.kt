class Solution {
    fun digitSum(s: String, k: Int): String {
        var current = s

        while (current.length > k) {
            val nextRound = StringBuilder()

            for (i in current.indices step k) {
                var groupSum = 0
                val end = minOf(i + k, current.length)
                for (j in i until end) {
                    groupSum += current[j] - '0'
                }
                nextRound.append(groupSum)
            }

            current = nextRound.toString()
        }

        return current
    }
}
