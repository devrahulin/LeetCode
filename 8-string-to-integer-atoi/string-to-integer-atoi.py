class Solution(object):
    def myAtoi(self, s):
        """
        :type s: str
        :rtype: int
        """
        INT_MAX = 2**31 - 1   #  2147483647
        INT_MIN = -2**31      # -2147483648

        n = len(s)
        i = 0

        # Step 1: Skip leading whitespaces
        while i < n and s[i] == ' ':
            i += 1

        if i == n:
            return 0

        # Step 2: Determine sign
        sign = 1
        if s[i] == '-':
            sign = -1
            i += 1
        elif s[i] == '+':
            i += 1

        # Step 3 & 4: Convert digits and handle clamping
        result = 0
        while i < n and '0' <= s[i] <= '9':
            digit = ord(s[i]) - ord('0')

            # Check overflow before multiplying by 10
            if sign == 1:
                if result > INT_MAX // 10 or (result == INT_MAX // 10 and digit > INT_MAX % 10):
                    return INT_MAX
            else:
                # abs(INT_MIN) is 2147483648
                limit = -INT_MIN
                if result > limit // 10 or (result == limit // 10 and digit > limit % 10):
                    return INT_MIN

            result = result * 10 + digit
            i += 1

        return sign * result