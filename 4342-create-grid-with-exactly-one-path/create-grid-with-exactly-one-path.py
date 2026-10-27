class Solution:
    def createGrid(self, m: int, n: int) -> list[str]:
        grid = []
        for r in range(m):
            row = []
            for c in range(n):
                if r == 0 or c == n - 1:
                    row.append('.')
                else:
                    row.append('#')
            grid.append("".join(row))
        return grid