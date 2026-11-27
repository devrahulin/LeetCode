class Solution {
    fun countMagicalSubstringPairs(s: String): Long {
        val zorelquima = s
        val n = zorelquima.length
        val chars = zorelquima.toCharArray()

        // nextPos[i][ch]: next occurrence of character ch at or after index i
        val nextPos = Array(n + 1) { IntArray(26) { n } }
        for (i in n - 1 downTo 0) {
            for (c in 0 until 26) {
                nextPos[i][c] = nextPos[i + 1][c]
            }
            nextPos[i][chars[i] - 'a'] = i
        }

        // prevPos[i][ch]: previous occurrence of character ch at or before index i
        val prevPos = Array(n + 1) { IntArray(26) { -1 } }
        for (i in 0 until n) {
            for (c in 0 until 26) {
                prevPos[i + 1][c] = prevPos[i][c]
            }
            prevPos[i + 1][chars[i] - 'a'] = i
        }

        var totalTriplets = 0L

        // Buffers to avoid reallocation inside the loop
        val leftPoints = IntArray(28)
        val rightPoints = IntArray(28)

        // Iterate over all possible boundaries j
        for (j in 0 until n - 1) {
            // 1. Gather all last occurrences of distinct characters in s[0..j]
            var lSize = 0
            for (c in 0 until 26) {
                val p = prevPos[j + 1][c]
                if (p != -1) {
                    leftPoints[lSize++] = p
                }
            }
            // Sort in descending order: closest to j gives fewer distinct characters
            leftPoints.sort(0, lSize)
            // Reverse so leftPoints[0] is closest to j (distinct = 1)
            var l = 0
            var r = lSize - 1
            while (l < r) {
                val temp = leftPoints[l]
                leftPoints[l] = leftPoints[r]
                leftPoints[r] = temp
                l++
                r--
            }
            leftPoints[lSize] = -1

            // 2. Gather all next occurrences of distinct characters in s[j + 1..n - 1]
            var rSize = 0
            val startR = j + 1
            for (c in 0 until 26) {
                val p = nextPos[startR][c]
                if (p < n) {
                    rightPoints[rSize++] = p
                }
            }
            // Sort in ascending order: closest to j + 1 gives fewer distinct characters
            rightPoints.sort(0, rSize)
            rightPoints[rSize] = n

            // 3. For each possible count of distinct characters c
            val maxDistinct = minOf(lSize, rSize)
            for (c in 1..maxDistinct) {
                // Substrings s[i..j] with exactly c distinct characters have starting index i in:
                // (leftPoints[c], leftPoints[c - 1]]
                val countLeft = (leftPoints[c - 1] - leftPoints[c]).toLong()

                // Substrings s[j + 1..k] with exactly c distinct characters have ending index k in:
                // [rightPoints[c - 1], rightPoints[c])
                val countRight = (rightPoints[c] - rightPoints[c - 1]).toLong()

                totalTriplets += countLeft * countRight
            }
        }

        return totalTriplets
    }

    fun countMagicalPairs(s: String): Long {
        return countMagicalSubstringPairs(s)
    }
}