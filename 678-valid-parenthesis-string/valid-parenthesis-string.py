class Solution(object):
    def checkValidString(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # Range of possible open '(' counts: [cmin, cmax]
        cmin = 0
        cmax = 0

        for char in s:
            if char == '(':
                cmin += 1
                cmax += 1
            elif char == ')':
                cmin -= 1
                cmax -= 1
            elif char == '*':
                # '*' can be ')' (cmin - 1), '' (cmin), or '(' (cmax + 1)
                cmin -= 1
                cmax += 1

            # Too many ')' even if all '*' were treated as '('
            if cmax < 0:
                return False

            # cmin cannot drop below 0 because '*' can always be chosen as empty string ""
            cmin = max(cmin, 0)

        # The string is valid if 0 open parentheses is a reachable outcome
        return cmin == 0