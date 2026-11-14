import kotlin.math.min

class Solution {
    fun minimumSumSubarray(nums: List<Int>, l: Int, r: Int): Int {
        val n = nums.size
        val prefix = IntArray(n + 1)

        for (i in 0 until n) {
            prefix[i + 1] = prefix[i] + nums[i]
        }

        var minSum = Int.MAX_VALUE

        for (len in l..r) {
            for (i in 0..(n - len)) {
                val currentSum = prefix[i + len] - prefix[i]
                if (currentSum > 0) {
                    minSum = min(minSum, currentSum)
                }
            }
        }

        return if (minSum == Int.MAX_VALUE) -1 else minSum
    }
}