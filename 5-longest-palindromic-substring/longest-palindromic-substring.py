class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        if not s or len(s) < 1:
            return ""

        start, end = 0, 0

        def expand_around_center(left, right):
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            # Length of palindrome is (right - 1) - (left + 1) + 1 = right - left - 1
            return right - left - 1

        for i in range(len(s)):
            # Odd length palindromes (single-character center, e.g., "aba")
            len1 = expand_around_center(i, i)
            # Even length palindromes (two-character center, e.g., "abba")
            len2 = expand_around_center(i, i + 1)
            
            max_len = max(len1, len2)
            if max_len > end - start:
                # Calculate the 0-based boundary indices
                start = i - (max_len - 1) // 2
                end = i + max_len // 2

        return s[start:end + 1]