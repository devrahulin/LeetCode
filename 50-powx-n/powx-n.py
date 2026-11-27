class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1.0

        exp = abs(n)
        result = 1.0
        current_product = x

        while exp > 0:
            if exp % 2 == 1:
                result *= current_product
            current_product *= current_product
            exp //= 2

        # Invert only once at the end to prevent precision drift
        return 1.0 / result if n < 0 else result