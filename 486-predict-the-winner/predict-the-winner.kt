import kotlin.math.max

class Solution {
    fun predictTheWinner(nums: IntArray): Boolean {
        val n = nums.size

        // Agar elements even hain, toh Player 1 hamesha win/tie guarantee kar sakta hai
        if (n % 2 == 0) {
            return true
        }

        // dp[i] represent karega subarray start index i se max score difference
        val dp = nums.clone()

        // Subarray lengths 2 se leke n tak solve karo
        for (len in 2..n) {
            for (i in 0..n - len) {
                val j = i + len - 1
                // Current player left (nums[i]) ya right (nums[j]) pick karega
                dp[i] = max(nums[i] - dp[i + 1], nums[j] - dp[i])
            }
        }

        // Agar final relative score difference >= 0 hai toh Player 1 jeet gaya
        return dp[0] >= 0
    }
}