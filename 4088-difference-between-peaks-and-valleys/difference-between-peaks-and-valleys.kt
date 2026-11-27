class Solution {
    fun peakValleyDifference(nums: IntArray): Int {
        val n = nums.size
        var peakSum = 0
        var valleySum = 0

        for (i in 1 until n - 1) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peakSum += nums[i]
            } else if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) {
                valleySum += nums[i]
            }
        }

        return peakSum - valleySum
    }

    fun differenceOfPeaksAndValleys(nums: IntArray): Int {
        return peakValleyDifference(nums)
    }
}