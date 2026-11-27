class Solution(object):
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        # 32-bit signed integer limits
        INT_MAX = 2**31 - 1
        INT_MIN = -2**31

        # Handle 32-bit overflow edge case: -2^31 / -1 = 2^31 (overflows to INT_MAX)
        if dividend == INT_MIN and divisor == -1:
            return INT_MAX

        # Determine the sign of the quotient
        negative = (dividend < 0) ^ (divisor < 0)

        # Work with absolute values
        dvd = abs(dividend)
        dvs = abs(divisor)

        quotient = 0

        # Exponential search: subtract multiples of divisor using bit shifting
        while dvd >= dvs:
            temp_dvs = dvs
            multiple = 1

            # Double the divisor until it exceeds the remaining dividend
            while dvd >= (temp_dvs << 1):
                temp_dvs <<= 1
                multiple <<= 1

            dvd -= temp_dvs
            quotient += multiple

        if negative:
            quotient = -quotient

        # Clamp within 32-bit signed integer range
        return max(INT_MIN, min(INT_MAX, quotient))