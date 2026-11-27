class Solution:
    def minOperations(self, nums: list[int], x: int, k: int) -> int:
        n = len(nums)
        if k * x > n:
            return 0
        
        # Coordinate compression for the Fenwick Tree
        sorted_unique = sorted(set(nums))
        m = len(sorted_unique)
        val_to_idx = {v: i + 1 for i, v in enumerate(sorted_unique)}
        
        # Fenwick trees: one for counts, one for sums of values
        bit_count = [0] * (m + 2)
        bit_sum = [0] * (m + 2)
        
        def update(idx: int, c_delta: int, s_delta: int):
            while idx <= m:
                bit_count[idx] += c_delta
                bit_sum[idx] += s_delta
                idx += idx & (-idx)
                
        def query(idx: int):
            cnt = 0
            sm = 0
            while idx > 0:
                cnt += bit_count[idx]
                sm += bit_sum[idx]
                idx -= idx & (-idx)
            return cnt, sm
        
        def find_kth_index(target_k: int) -> int:
            idx = 0
            accum_count = 0
            step = 1 << (m.bit_length())
            while step > 0:
                nxt = idx + step
                if nxt <= m and accum_count + bit_count[nxt] < target_k:
                    idx = nxt
                    accum_count += bit_count[nxt]
                step >>= 1
            return idx + 1
        
        def add(val: int):
            idx = val_to_idx[val]
            update(idx, 1, val)
            
        def remove(val: int):
            idx = val_to_idx[val]
            update(idx, -1, -val)
            
        target_rank = (x + 1) // 2
        
        def get_window_cost():
            med_idx = find_kth_index(target_rank)
            median = sorted_unique[med_idx - 1]
            
            cnt_left, sum_left = query(med_idx)
            cnt_total, sum_total = query(m)
            
            cnt_right = cnt_total - cnt_left
            sum_right = sum_total - sum_left
            
            # Left side elements <= median, right side > median
            cost_left = cnt_left * median - sum_left
            cost_right = sum_right - cnt_right * median
            return cost_left + cost_right

        # Precompute cost for every window of length x
        cost = [0] * (n + 1)
        for i in range(n):
            add(nums[i])
            if i >= x:
                remove(nums[i - x])
            if i >= x - 1:
                cost[i + 1] = get_window_cost()

        # DP: dp[j][i] = min operations choosing j non-overlapping windows from prefix i
        INF = float('inf')
        dp = [0] * (n + 1)
        
        for j in range(1, k + 1):
            next_dp = [INF] * (n + 1)
            for i in range(j * x, n + 1):
                next_dp[i] = min(next_dp[i - 1], dp[i - x] + cost[i])
            dp = next_dp

        return dp[n]
        