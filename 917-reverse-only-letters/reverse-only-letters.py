class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        letters = [c for c in s if c.isalpha()]
        return "".join(letters.pop() if c.isalpha() else c for c in s)