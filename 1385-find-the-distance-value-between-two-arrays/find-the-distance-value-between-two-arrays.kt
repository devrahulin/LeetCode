class Solution {
    fun findTheDistanceValue(arr1: IntArray, arr2: IntArray, d: Int): Int {
        arr2.sort()
        var count = 0

        for (x in arr1) {
            val low = x - d
            val high = x + d

            var left = 0
            var right = arr2.size - 1
            var hasElementInRange = false

            while (left <= right) {
                val mid = left + (right - left) / 2
                if (arr2[mid] in low..high) {
                    hasElementInRange = true
                    break
                } else if (arr2[mid] < low) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }

            if (!hasElementInRange) {
                count++
            }
        }

        return count
    }
}
