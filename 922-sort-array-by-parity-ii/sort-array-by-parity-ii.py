class Solution:
    def sortArrayByParityII(self, nums: List[int]) -> List[int]:
        n = len(nums)
        res = [0] * n
        even, odd = 0, 1
        for x in nums:
            if x % 2 == 0:
                res[even] = x
                even += 2
            else:
                res[odd] = x
                odd += 2
        return res