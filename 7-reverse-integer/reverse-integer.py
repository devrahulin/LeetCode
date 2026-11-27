class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        INT_MAX = 2**31 - 1   # 2147483647
        INT_MIN = -2**31      # -2147483648

        rev = 0
        sign = -1 if x < 0 else 1
        x = abs(x)

        while x != 0:
            pop = x % 10
            x //= 10

            # Check overflow before multiplying by 10
            if sign == 1:
                if rev > INT_MAX // 10 or (rev == INT_MAX // 10 and pop > INT_MAX % 10):
                    return 0
            else:
                if rev > abs(INT_MIN) // 10 or (rev == abs(INT_MIN) // 10 and pop > abs(INT_MIN) % 10):
                    return 0

            rev = rev * 10 + pop

        return sign * rev