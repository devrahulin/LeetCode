class Solution:
    def spiralOrder(self, matrix: list[list[int]]) -> list[int]:
        if not matrix or not matrix[0]:
            return []

        top = 0
        bottom = len(matrix) - 1
        left = 0
        right = len(matrix[0]) - 1

        result = []

        while top <= bottom and left <= right:
            # 1. Traverse from left to right along top boundary
            for col in range(left, right + 1):
                result.append(matrix[top][col])
            top += 1

            # 2. Traverse from top to bottom along right boundary
            for row in range(top, bottom + 1):
                result.append(matrix[row][right])
            right -= 1

            # 3. Traverse from right to left along bottom boundary (if row still valid)
            if top <= bottom:
                for col in range(right, left - 1, -1):
                    result.append(matrix[bottom][col])
                bottom -= 1

            # 4. Traverse from bottom to top along left boundary (if col still valid)
            if left <= right:
                for row in range(bottom, top - 1, -1):
                    result.append(matrix[row][left])
                left += 1

        return result