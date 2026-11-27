class Solution:
    def solveNQueens(self, n: int) -> list[list[str]]:
        results = []
        cols = set()
        pos_diag = set()  # (row + col)
        neg_diag = set()  # (row - col)
        board = [["."] * n for _ in range(n)]

        def backtrack(r: int) -> None:
            # Base case: all n queens have been successfully placed
            if r == n:
                results.append(["".join(row) for row in board])
                return

            for c in range(n):
                # Check column and both diagonals
                if c in cols or (r + c) in pos_diag or (r - c) in neg_diag:
                    continue

                # Place queen and record occupied attack lines
                board[r][c] = "Q"
                cols.add(c)
                pos_diag.add(r + c)
                neg_diag.add(r - c)

                # Move to the next row
                backtrack(r + 1)

                # Backtrack: remove queen and clear lines
                board[r][c] = "."
                cols.remove(c)
                pos_diag.remove(r + c)
                neg_diag.remove(r - c)

        backtrack(0)
        return results