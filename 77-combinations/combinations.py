class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        res = []
        def backtrack(start: int, comb: list[int]):
            if len(comb) == k:
                res.append(comb[:])
                return
            for i in range(start, n - (k - len(comb)) + 2):
                comb.append(i)
                backtrack(i + 1, comb)
                comb.pop()
        backtrack(1, [])
        return res
