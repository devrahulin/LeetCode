class Solution {
    fun sumOfSquares(nums: IntArray): Int {
        val n = nums.size
        var sum = 0

        for (i in nums.indices) {
            val index1Based = i + 1
            if (n % index1Based == 0) {
                sum += nums[i] * nums[i]
            }
        }

        return sum
    }
}