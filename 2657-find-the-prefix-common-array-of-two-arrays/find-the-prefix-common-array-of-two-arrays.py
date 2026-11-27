class Solution:
    def findThePrefixCommonArray(self, A: list[int], B: list[int]) -> list[int]:
        n = len(A)
        ans = [0] * n
        count = [0] * (n + 1)
        common = 0

        for i in range(n):
            # Increment frequency for element from A
            count[A[i]] += 1
            if count[A[i]] == 2:
                common += 1

            # Increment frequency for element from B
            count[B[i]] += 1
            if count[B[i]] == 2:
                common += 1

            ans[i] = common

        return ans