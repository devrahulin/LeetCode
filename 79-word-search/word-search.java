class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char[] wordArray = word.toCharArray();

        int[] boardFreq = new int[128];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                boardFreq[board[r][c]]++;
            }
        }
        int[] wordFreq = new int[128];
        for (char ch : wordArray) {
            wordFreq[ch]++;
            if (wordFreq[ch] > boardFreq[ch]) {
                return false;
            }
        }

        if (boardFreq[wordArray[wordArray.length - 1]] < boardFreq[wordArray[0]]) {
            int left = 0, right = wordArray.length - 1;
            while (left < right) {
                char temp = wordArray[left];
                wordArray[left] = wordArray[right];
                wordArray[right] = temp;
                left++;
                right--;
            }
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == wordArray[0]) {
                    if (dfs(board, r, c, wordArray, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int r, int c, char[] word, int index) {
        if (index == word.length) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word[index]) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '#';

        boolean found = dfs(board, r + 1, c, word, index + 1) ||
                        dfs(board, r - 1, c, word, index + 1) ||
                        dfs(board, r, c + 1, word, index + 1) ||
                        dfs(board, r, c - 1, word, index + 1);

        board[r][c] = temp;
        return found;
    }
}
