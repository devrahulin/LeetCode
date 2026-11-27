class Solution {
    fun maxProduct(n: Int): Int {
        var num = n
        var max1 = -1
        var max2 = -1

        while (num > 0) {
            val d = num % 10
            num /= 10

            if (d > max1) {
                max2 = max1
                max1 = d
            } else if (d > max2) {
                max2 = d
            }
        }

        return max1 * max2
    }
}