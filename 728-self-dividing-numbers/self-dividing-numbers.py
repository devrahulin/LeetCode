class Solution:
    def selfDividingNumbers(self, left: int, right: int) -> List[int]:
        res = []
        for n in range(left, right + 1):
            s = str(n)
            if '0' not in s and all(n % int(d) == 0 for d in s):
                res.append(n)
        return res