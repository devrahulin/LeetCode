import kotlin.math.abs
import kotlin.math.min

class Solution {
    fun minSumSquareDiff(nums1: IntArray, nums2: IntArray, k1: Int, k2: Int): Long {
        val n = nums1.size
        var maxDiff = 0
        val diffs = IntArray(n)

        for (i in 0 until n) {
            val d = abs(nums1[i] - nums2[i])
            diffs[i] = d
            if (d > maxDiff) {
                maxDiff = d
            }
        }

        if (maxDiff == 0) return 0L

        val count = LongArray(maxDiff + 1)
        for (d in diffs) {
            count[d]++
        }

        var k = k1.toLong() + k2.toLong()

        for (d in maxDiff downTo 1) {
            if (count[d] == 0L) continue

            if (k >= count[d]) {
                k -= count[d]
                count[d - 1] += count[d]
                count[d] = 0L
            } else {
                count[d - 1] += k
                count[d] -= k
                k = 0L
                break
            }
        }

        var ans = 0L
        for (d in 1..maxDiff) {
            if (count[d] > 0L) {
                ans += count[d] * d.toLong() * d.toLong()
            }
        }

        return ans
    }
}