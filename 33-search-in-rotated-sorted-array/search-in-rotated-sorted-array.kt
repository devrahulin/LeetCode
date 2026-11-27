class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var low = 0
        var high = nums.size - 1

        while (low <= high) {
            val mid = low + (high - low) / 2

            if (nums[mid] == target) {
                return mid
            }

            // Check if the left half is normally sorted
            if (nums[low] <= nums[mid]) {
                // Check if target lies within the sorted left half
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1
                } else {
                    low = mid + 1
                }
            } else {
                // Otherwise, the right half must be normally sorted
                // Check if target lies within the sorted right half
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1
                } else {
                    high = mid - 1
                }
            }
        }

        return -1
    }
}