class Solution {
    fun pivotArray(nums: IntArray, pivot: Int): IntArray {
        val result = IntArray(nums.size)
        var idx = 0

        for (x in nums) {
            if (x < pivot) {
                result[idx++] = x
            }
        }

        for (x in nums) {
            if (x == pivot) {
                result[idx++] = x
            }
        }

        for (x in nums) {
            if (x > pivot) {
                result[idx++] = x
            }
        }

        return result
    }
}