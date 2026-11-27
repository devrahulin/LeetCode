class Solution:
    def setZeroes(self, matrix: list[list[int]]) -> None:
        rows, cols = len(matrix), len(matrix[0])
        first_col_zero = False

        # 1. Check if first column needs to be zeroed, and mark headers
        for r in range(rows):
            if matrix[r][0] == 0:
                first_col_zero = True
            for c in range(1, cols):
                if matrix[r][c] == 0:
                    matrix[r][0] = 0
                    matrix[0][c] = 0

        # 2. Zero out cells based on row/col markers (skip first row & col)
        for r in range(1, rows):
            for c in range(1, cols):
                if matrix[r][0] == 0 or matrix[0][c] == 0:
                    matrix[r][c] = 0

        # 3. Zero out the first row if needed
        if matrix[0][0] == 0:
            for c in range(cols):
                matrix[0][c] = 0

        # 4. Zero out the first column if needed
        if first_col_zero:
            for r in range(rows):
                matrix[r][0] = 0
                