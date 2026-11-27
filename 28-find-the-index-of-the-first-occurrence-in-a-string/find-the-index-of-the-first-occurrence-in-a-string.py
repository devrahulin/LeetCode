class Solution(object):
    def strStr(self, haystack, needle):
        """
        :type haystack: str
        :type needle: str
        :rtype: int
        """
        n, m = len(haystack), len(needle)
        if m == 0:
            return 0
        if m > n:
            return -1

        # Step 1: Compute Longest Prefix Suffix (LPS) array for needle
        lps = [0] * m
        length = 0  # Length of previous longest prefix suffix
        i = 1

        while i < m:
            if needle[i] == needle[length]:
                length += 1
                lps[i] = length
                i += 1
            else:
                if length != 0:
                    length = lps[length - 1]
                else:
                    lps[i] = 0
                    i += 1

        # Step 2: Search for needle in haystack using LPS table
        i = 0  # Index for haystack
        j = 0  # Index for needle

        while i < n:
            if haystack[i] == needle[j]:
                i += 1
                j += 1

            if j == m:
                return i - m  # Match found, return start index

            elif i < n and haystack[i] != needle[j]:
                if j != 0:
                    j = lps[j - 1]  # Skip redundant character checks
                else:
                    i += 1

        return -1