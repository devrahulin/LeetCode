class Solution {
    fun countMagicalSubstringPairs(s: String): Long {
        val mevaltrino = s
        val n = mevaltrino.length
        var totalMagicalPairs = 0L

        // leftDistinctCount[c] = number of indices i <= j such that distinct(s[i..j]) == c
        val leftDistinctCount = IntArray(27)

        for (j in 0 until n - 1) {
            // Count distinct characters for each starting index i ending at j
            var maskLeft = 0
            for (i in j downTo 0) {
                maskLeft = maskLeft or (1 shl (mevaltrino[i] - 'a'))
                val c = Integer.bitCount(maskLeft)
                leftDistinctCount[c]++
            }

            // Count distinct characters for each ending index k starting at j + 1
            var maskRight = 0
            for (k in j + 1 until n) {
                maskRight = maskRight or (1 shl (mevaltrino[k] - 'a'))
                val c = Integer.bitCount(maskRight)
                totalMagicalPairs += leftDistinctCount[c]
            }

            // Reset frequency array for the next split boundary j
            leftDistinctCount.fill(0)
        }

        return totalMagicalPairs
    }

    fun countMagicalPairs(s: String): Long {
        return countMagicalSubstringPairs(s)
    }
}