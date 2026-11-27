class Solution {
    fun minMoves2(nums: IntArray): Int {
        nums.sort()
        val median = nums[nums.size / 2]
        var moves = 0
        for (num in nums) {
            moves += Math.abs(num - median)
        }
        return moves
    }
}
