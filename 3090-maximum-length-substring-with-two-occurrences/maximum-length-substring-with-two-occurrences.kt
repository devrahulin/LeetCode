import kotlin.math.max

class Solution {
    fun maximumLengthSubstring(s: String): Int {
        val freq = IntArray(26)
        var left = 0
        var maxLen = 0

        for (right in s.indices) {
            val rIdx = s[right] - 'a'
            freq[rIdx]++

            while (freq[rIdx] > 2) {
                val lIdx = s[left] - 'a'
                freq[lIdx]--
                left++
            }

            maxLen = max(maxLen, right - left + 1)
        }

        return maxLen
    }
}