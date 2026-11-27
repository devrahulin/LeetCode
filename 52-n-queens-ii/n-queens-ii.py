class Solution:
    def totalNQueens(self, n: int) -> int:
        count = 0
        all_ones = (1 << n) - 1

        def backtrack(row: int, cols: int, pos_diag: int, neg_diag: int) -> None:
            nonlocal count
            if row == n:
                count += 1
                return

            # Available spots are those not under attack in cols or either diagonal
            available_spots = all_ones & ~(cols | pos_diag | neg_diag)

            while available_spots:
                # Pick the lowest set bit (rightmost available column)
                spot = available_spots & -available_spots
                # Clear that bit from available positions
                available_spots ^= spot

                # Shift diagonals down to the next row:
                # pos_diag (/): moves right, so shift left by 1
                # neg_diag (\): moves left, so shift right by 1
                backtrack(
                    row + 1,
                    cols | spot,
                    (pos_diag | spot) << 1,
                    (neg_diag | spot) >> 1
                )

        backtrack(0, 0, 0, 0)
        return count