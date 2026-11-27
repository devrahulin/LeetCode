class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val first = findBound(nums, target, isFirst = true)
        if (first == -1) {
            return intArrayOf(-1, -1)
        }
        val last = findBound(nums, target, isFirst = false)
        return intArrayOf(first, last)
    }

    private fun findBound(nums: IntArray, target: Int, isFirst: Boolean): Int {
        var low = 0
        var high = nums.size - 1
        var bound = -1

        while (low <= high) {
            val mid = low + (high - low) / 2

            if (nums[mid] == target) {
                bound = mid
                if (isFirst) {
                    // Keep searching to the left to find the first occurrence
                    high = mid - 1
                } else {
                    // Keep searching to the right to find the last occurrence
                    low = mid + 1
                }
            } else if (nums[mid] < target) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return bound
    }
}