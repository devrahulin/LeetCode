class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int prev2 = 1;
        int prev1 = 1;

        for (int i = 1; i < n; i++) {
            int curr = 0;
            char c1 = s.charAt(i);
            char c0 = s.charAt(i - 1);

            if (c1 != '0') {
                curr += prev1;
            }
            int twoDigit = (c0 - '0') * 10 + (c1 - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += prev2;
            }

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
