from collections import deque

class Solution:
    def removeInvalidParentheses(self, s: str) -> list[str]:
        def is_valid(string: str) -> bool:
            count = 0
            for char in string:
                if char == '(':
                    count += 1
                elif char == ')':
                    count -= 1
                    if count < 0:
                        return False
            return count == 0

        # Queue for BFS level traversal
        queue = deque([s])
        visited = {s}
        result = []
        found = False

        while queue:
            current = queue.popleft()

            if is_valid(current):
                result.append(current)
                found = True

            # If we already found valid strings at this removal level,
            # do not generate the next level (to ensure minimum removals).
            if found:
                continue

            # Generate all possible next states by removing one parenthesis
            for i, char in enumerate(current):
                if char not in ('(', ')'):
                    continue
                
                # Prune consecutive duplicate removals: e.g., in "(((", 
                # removing index 0, 1, or 2 yields the same next string.
                if i > 0 and current[i] == current[i - 1]:
                    continue

                next_str = current[:i] + current[i + 1:]
                if next_str not in visited:
                    visited.add(next_str)
                    queue.append(next_str)

        return result