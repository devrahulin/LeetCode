class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        result = []
        depth = 0

        for char in s:
            if char == '(':
                # If depth > 0, this '(' is not the outermost bracket of its primitive block
                if depth > 0:
                    result.append(char)
                depth += 1
            else:  # char == ')'
                depth -= 1
                # If depth > 0 after decrementing, this ')' is not the outermost bracket
                if depth > 0:
                    result.append(char)

        return "".join(result)