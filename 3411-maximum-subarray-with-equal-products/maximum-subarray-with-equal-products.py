from math import gcd

class Solution:
    def maxLength(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 2  # Any subarray of length 2 satisfies a * b == gcd(a, b) * lcm(a, b)

        for i in range(n):
            prod = nums[i]
            g = nums[i]
            l = nums[i]
            for j in range(i + 1, n):
                prod *= nums[j]
                if prod > 25200:
                    break
                g = gcd(g, nums[j])
                l = (l * nums[j]) // gcd(l, nums[j])
                if prod == g * l:
                    ans = max(ans, j - i + 1)

        return ans
        