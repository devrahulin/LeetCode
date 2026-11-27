class Solution:
    def generateMatrix(self, n: int) -> list[list[int]]:
        matrix = [[0] * n for _ in range(n)]

        top = 0
        bottom = n - 1
        left = 0
        right = n - 1

        val = 1
        target = n * n

        while val <= target:
            # 1. Fill top row from left to right
            for col in range(left, right + 1):
                matrix[top][col] = val
                val += 1
            top += 1

            # 2. Fill right column from top to bottom
            for row in range(top, bottom + 1):
                matrix[row][right] = val
                val += 1
            right -= 1

            # 3. Fill bottom row from right to left
            for col in range(right, left - 1, -1):
                matrix[bottom][col] = val
                val += 1
            bottom -= 1

            # 4. Fill left column from bottom to top
            for row in range(bottom, top - 1, -1):
                matrix[row][left] = val
                val += 1
            left += 1

        return matrix