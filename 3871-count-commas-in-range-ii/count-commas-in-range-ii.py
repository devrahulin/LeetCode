class Solution:
    def countCommas(self, n: int) -> int:
        total_commas = 0
        threshold = 1000  # First comma appears at 1,000

        # Each threshold 10^(3*k) adds an additional comma for all numbers >= threshold
        while n >= threshold:
            total_commas += n - threshold + 1
            threshold *= 1000

        return total_commas