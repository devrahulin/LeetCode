class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;   // Count of unmatched ')'
        int closeNeeded = 0;  // Count of unmatched '('

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                closeNeeded++;
            } else {
                if (closeNeeded > 0) {
                    // Match with a previously unmatched '('
                    closeNeeded--;
                } else {
                    // No matching '(' available; an opening '(' must be inserted
                    openNeeded++;
                }
            }
        }

        // Total additions needed is unmatched ')' + unmatched '('
        return openNeeded + closeNeeded;
    }
}