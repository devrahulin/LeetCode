class Solution {
    fun minMoves(nums: IntArray): Int {
        var maxVal = nums[0]
        for (x in nums) {
            if (x > maxVal) {
                maxVal = x
            }
        }

        var moves = 0
        for (x in nums) {
            moves += maxVal - x
        }

        return moves
    }
}
