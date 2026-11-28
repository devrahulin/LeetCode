class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        freq = [0] * (n + 1)
        min_p = items[0][1]
        for f, p in items:
            freq[f] += 1
            if p < min_p:
                min_p = p

        mult_count = [0] * (n + 1)
        for f in range(1, n + 1):
            if freq[f]:
                total = 0
                for m in range(f, n + 1, f):
                    total += freq[m]
                mult_count[f] = total - 1

        items.sort(key=lambda x: x[1])
        limit = 2 * min_p
        ans = 0

        for f, p in items:
            if p >= limit or budget < p:
                break
            free = mult_count[f]
            if free > 0:
                take = min(free, budget // p)
                ans += 2 * take
                budget -= take * p

        ans += budget // min_p
        return ans
