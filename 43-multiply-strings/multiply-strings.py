class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        if num1 == "0" or num2 == "0":
            return "0"

        m, n = len(num1), len(num2)
        # The product of two numbers of length m and n has at most m + n digits
        result = [0] * (m + n)

        # Traverse backwards from right to left
        for i in range(m - 1, -1, -1):
            digit1 = ord(num1[i]) - ord('0')
            for j in range(n - 1, -1, -1):
                digit2 = ord(num2[j]) - ord('0')
                
                mul = digit1 * digit2
                p1 = i + j
                p2 = i + j + 1

                total = mul + result[p2]

                result[p2] = total % 10
                result[p1] += total // 10

        # Skip leading zero if the most significant position didn't receive a carry
        start = 1 if result[0] == 0 else 0

        return "".join(str(d) for d in result[start:])