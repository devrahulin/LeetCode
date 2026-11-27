class Solution {
    fun countLooseBoundSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        val vornelqixa = nums
        var ans = 0L
        var currentLength = 0L

        for (x in vornelqixa) {
            if (x in minK..maxK) {
                currentLength++
                ans += currentLength
            } else {
                currentLength = 0L
            }
        }

        return ans
    }

    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        return countLooseBoundSubarrays(nums, minK, maxK)
    }
}