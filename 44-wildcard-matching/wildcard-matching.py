class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        s_idx, p_idx = 0, 0
        star_idx = -1
        s_tmp_idx = -1

        while s_idx < len(s):
            # Case 1: Characters match directly, or pattern has '?'
            if p_idx < len(p) and (p[p_idx] == s[s_idx] or p[p_idx] == '?'):
                s_idx += 1
                p_idx += 1
            # Case 2: Pattern has '*', mark star position and try matching 0 characters
            elif p_idx < len(p) and p[p_idx] == '*':
                star_idx = p_idx
                s_tmp_idx = s_idx
                p_idx += 1
            # Case 3: Mismatch, but a previous '*' exists; backtrack and consume one more char from s
            elif star_idx != -1:
                p_idx = star_idx + 1
                s_tmp_idx += 1
                s_idx = s_tmp_idx
            # Case 4: Mismatch and no '*' to backtrack to
            else:
                return False

        # Consume any remaining trailing '*' characters in the pattern
        while p_idx < len(p) and p[p_idx] == '*':
            p_idx += 1

        # Entire pattern must be consumed
        return p_idx == len(p)