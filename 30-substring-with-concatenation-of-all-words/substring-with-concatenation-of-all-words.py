from collections import Counter, defaultdict

class Solution(object):
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        if not s or not words:
            return []

        word_len = len(words[0])
        num_words = len(words)
        total_len = word_len * num_words
        n = len(s)

        if n < total_len:
            return []

        word_counts = Counter(words)
        results = []

        # Run a sliding window for each offset from 0 to word_len - 1
        for i in range(word_len):
            left = i
            right = i
            current_counts = defaultdict(int)
            matched_words = 0

            while right + word_len <= n:
                # Extract the next word chunk
                word = s[right:right + word_len]
                right += word_len

                if word in word_counts:
                    current_counts[word] += 1
                    matched_words += 1

                    # If the word appears more times than allowed, shift left pointer
                    while current_counts[word] > word_counts[word]:
                        removed_word = s[left:left + word_len]
                        current_counts[removed_word] -= 1
                        matched_words -= 1
                        left += word_len

                    # If all words match exactly, record the starting index
                    if matched_words == num_words:
                        results.append(left)
                else:
                    # Invalid word found: reset window
                    current_counts.clear()
                    matched_words = 0
                    left = right

        return results