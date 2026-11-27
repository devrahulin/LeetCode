class Solution {
    fun distinctSubseqII(s: String): Int {
        val mod = 1_000_000_007
        val lastEnd = IntArray(26)
        var total = 0

        for (c in s) {
            val idx = c - 'a'
            val newSubseq = (total + 1 - lastEnd[idx] + mod) % mod
            total = (total + newSubseq) % mod
            lastEnd[idx] = (lastEnd[idx] + newSubseq) % mod
        }

        return total
    }
}