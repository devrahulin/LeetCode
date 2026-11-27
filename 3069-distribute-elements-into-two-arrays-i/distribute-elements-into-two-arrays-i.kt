class Solution {
    fun resultArray(nums: IntArray): IntArray {
        val arr1 = ArrayList<Int>()
        val arr2 = ArrayList<Int>()

        arr1.add(nums[0])
        arr2.add(nums[1])

        for (i in 2 until nums.size) {
            if (arr1.last() > arr2.last()) {
                arr1.add(nums[i])
            } else {
                arr2.add(nums[i])
            }
        }

        val result = IntArray(nums.size)
        var index = 0

        for (x in arr1) {
            result[index++] = x
        }
        for (x in arr2) {
            result[index++] = x
        }

        return result
    }
}