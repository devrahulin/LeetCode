class Solution(object):
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # Map each closing bracket to its matching open bracket
        bracket_map = {
            ')': '(',
            '}': '{',
            ']': '['
        }
        stack = []

        for char in s:
            if char in bracket_map:
                # If stack is non-empty, pop the top element; otherwise use a placeholder
                top_element = stack.pop() if stack else '#'
                if bracket_map[char] != top_element:
                    return False
            else:
                # char is an open bracket, push onto the stack
                stack.append(char)

        # Valid only if all opened brackets were properly matched and closed
        return len(stack) == 0