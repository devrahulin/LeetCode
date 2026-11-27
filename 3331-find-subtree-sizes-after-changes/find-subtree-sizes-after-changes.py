from typing import List

class Solution:
    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        n = len(parent)
        adj = [[] for _ in range(n)]
        for i in range(1, n):
            adj[parent[i]].append(i)
        
        new_parent = list(parent)
        last_seen = [-1] * 26
        
        # Pass 1: Find closest ancestor with the same character
        def find_new_parents(u: int) -> None:
            char_idx = ord(s[u]) - ord('a')
            prev_node = last_seen[char_idx]
            
            if prev_node != -1:
                new_parent[u] = prev_node
                
            last_seen[char_idx] = u
            for v in adj[u]:
                find_new_parents(v)
            last_seen[char_idx] = prev_node  # Backtrack

        find_new_parents(0)
        
        # Build the updated tree
        new_adj = [[] for _ in range(n)]
        for i in range(1, n):
            new_adj[new_parent[i]].append(i)
            
        ans = [0] * n
        
        # Pass 2: Calculate subtree sizes in the final tree
        def get_sizes(u: int) -> int:
            size = 1
            for v in new_adj[u]:
                size += get_sizes(v)
            ans[u] = size
            return size

        get_sizes(0)
        return ans
        