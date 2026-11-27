from collections import defaultdict

class Solution:
    def countServers(self, n: int, logs: list[list[int]], x: int, queries: list[int]) -> list[int]:
        # Sort logs by timestamp
        logs.sort(key=lambda item: item[1])
        
        # Sort queries along with their original indices
        sorted_queries = sorted(enumerate(queries), key=lambda item: item[1])
        
        ans = [0] * len(queries)
        server_count = defaultdict(int)
        active_servers = 0
        
        left = 0
        right = 0
        num_logs = len(logs)
        
        for orig_idx, q_time in sorted_queries:
            window_start = q_time - x
            window_end = q_time
            
            # Expand right pointer: include all logs occurring <= window_end
            while right < num_logs and logs[right][1] <= window_end:
                s_id = logs[right][0]
                if server_count[s_id] == 0:
                    active_servers += 1
                server_count[s_id] += 1
                right += 1
                
            # Contract left pointer: exclude all logs occurring < window_start
            while left < num_logs and logs[left][1] < window_start:
                s_id = logs[left][0]
                server_count[s_id] -= 1
                if server_count[s_id] == 0:
                    active_servers -= 1
                left += 1
                
            ans[orig_idx] = n - active_servers
            
        return ans
        