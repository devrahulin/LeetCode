from collections import Counter

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        if not s or not t or len(s) < len(t):
            return ""

        # Frequency map of required characters from t
        target_counts = Counter(t)
        required = len(target_counts)

        # Window state
        window_counts = {}
        formed = 0

        # (window_length, left_index, right_index)
        ans = (float("inf"), 0, 0)
        left = 0

        for right, char in enumerate(s):
            # 1. Expand window by adding character at right
            window_counts[char] = window_counts.get(char, 0) + 1

            # Check if current character's frequency matches target frequency
            if char in target_counts and window_counts[char] == target_counts[char]:
                formed += 1

            # 2. Contract window until it ceases to be valid
            while left <= right and formed == required:
                # Update minimum window result
                if (right - left + 1) < ans[0]:
                    ans = (right - left + 1, left, right)

                # Remove leftmost character from window
                left_char = s[left]
                window_counts[left_char] -= 1
                if left_char in target_counts and window_counts[left_char] < target_counts[left_char]:
                    formed -= 1

                left += 1

        return "" if ans[0] == float("inf") else s[ans[1] : ans[2] + 1]
