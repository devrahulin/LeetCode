class Solution {
    fun sumOfSortableIntegers(nums: IntArray): Long {
        val n = nums.size
        val sortedNums = nums.clone()
        sortedNums.sort()

        val divisors = ArrayList<Int>()
        var d = 1
        while (d * d <= n) {
            if (n % d == 0) {
                divisors.add(d)
                if (d * d != n) {
                    divisors.add(n / d)
                }
            }
            d++
        }

        fun check(k: Int): Boolean {
            val numBlocks = n / k
            for (b in 0 until numBlocks) {
                val start = b * k
                var drops = 0
                for (i in 0 until k) {
                    val curr = nums[start + i]
                    val next = nums[start + (i + 1) % k]
                    if (curr > next) {
                        drops++
                    }
                }
                if (drops > 1) {
                    return false
                }

                var minVal = nums[start]
                var maxVal = nums[start]
                for (i in 1 until k) {
                    val v = nums[start + i]
                    if (v < minVal) minVal = v
                    if (v > maxVal) maxVal = v
                }

                if (minVal != sortedNums[start] || maxVal != sortedNums[start + k - 1]) {
                    return false
                }
            }
            return true
        }

        var totalSum = 0L
        for (k in divisors) {
            if (check(k)) {
                totalSum += k
            }
        }

        return totalSum
    }

    fun sortableIntegers(nums: IntArray): Long {
        return sumOfSortableIntegers(nums)
    }

    fun sortableIntegers(nums: IntArray, dummy: Int = 0): Int {
        return sumOfSortableIntegers(nums).toInt()
    }
}