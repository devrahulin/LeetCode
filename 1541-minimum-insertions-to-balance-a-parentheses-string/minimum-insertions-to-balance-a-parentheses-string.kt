class Solution {
    fun minInsertions(s: String): Int {
        var insertions = 0
        var neededRight = 0

        for (c in s) {
            if (c == '(') {
                // Each '(' expects two ')'
                // If neededRight is odd, we encountered a lone ')' previously that needs a matching ')'
                if (neededRight % 2 != 0) {
                    insertions++      // Insert one ')' to pair up the lone ')'
                    neededRight--     // Decrement to make it an even count
                }
                neededRight += 2
            } else { // c == ')'
                neededRight--
                // More ')' than '(' available
                if (neededRight < 0) {
                    insertions++      // Insert an opening '('
                    neededRight += 2  // The inserted '(' expects two ')' (one matched by current, one more needed)
                }
            }
        }

        return insertions + neededRight
    }
}