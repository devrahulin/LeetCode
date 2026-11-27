class Solution:
    def fairCandySwap(self, aliceSizes: List[int], bobSizes: List[int]) -> List[int]:
        diff = (sum(aliceSizes) - sum(bobSizes)) // 2
        set_bob = set(bobSizes)
        for x in aliceSizes:
            if x - diff in set_bob:
                return [x, x - diff]