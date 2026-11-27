class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        # Negative numbers cannot be palindromes (e.g., -121 -> 121-)
        # Numbers ending in 0 (except 0 itself) cannot be palindromes (e.g., 10 -> 01)
        if x < 0 or (x % 10 == 0 and x != 0):
            return False

        reverted_number = 0
        while x > reverted_number:
            reverted_number = reverted_number * 10 + x % 10
            x //= 10

        # When length is even: x == reverted_number (e.g., 1221 -> x = 12, reverted_number = 12)
        # When length is odd: x == reverted_number // 10 (e.g., 12321 -> x = 12, reverted_number = 123)
        return x == reverted_number or x == reverted_number // 10