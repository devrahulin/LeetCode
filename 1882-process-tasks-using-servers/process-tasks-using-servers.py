import heapq

class Solution:
    def assignTasks(self, servers: list[int], tasks: list[int]) -> list[int]:
        # free_servers: (weight, server_index)
        free_servers = [(w, i) for i, w in enumerate(servers)]
        heapq.heapify(free_servers)
        
        # busy_servers: (free_time, weight, server_index)
        busy_servers = []
        
        ans = [0] * len(tasks)
        time = 0
        
        for j, task_duration in enumerate(tasks):
            # Time advances at least to the task's arrival second j
            time = max(time, j)
            
            # If all servers are currently occupied, fast-forward to when the earliest server finishes
            if not free_servers and busy_servers:
                time = max(time, busy_servers[0][0])
                
            # Free up all servers whose tasks have finished by current time
            while busy_servers and busy_servers[0][0] <= time:
                free_time, weight, s_idx = heapq.heappop(busy_servers)
                heapq.heappush(free_servers, (weight, s_idx))
                
            # Assign task to the free server with the lowest (weight, server_index)
            weight, s_idx = heapq.heappop(free_servers)
            ans[j] = s_idx
            
            # Push into busy_servers with its release time
            heapq.heappush(busy_servers, (time + task_duration, weight, s_idx))
            
        return ans
        