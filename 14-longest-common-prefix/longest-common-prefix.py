class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if not strs:
            return ""

        # Iterate through the characters of the first string
        for i in range(len(strs[0])):
            char = strs[0][i]

            # Compare this character with the corresponding character in all other strings
            for s in strs[1:]:
                # If index exceeds length of s or character does not match
                if i == len(s) or s[i] != char:
                    return strs[0][:i]

        return strs[0]