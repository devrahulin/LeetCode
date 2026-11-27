/**
 * @param {character[][]} board
 * @return {boolean}
 */
var isValidSudoku = function(board) {
    // Sets to track numbers seen in rows, columns, and 3x3 boxes
    const rows = Array.from({ length: 9 }, () => new Set());
    const cols = Array.from({ length: 9 }, () => new Set());
    const boxes = Array.from({ length: 9 }, () => new Set());

    for (let r = 0; r < 9; r++) {
        for (let c = 0; c < 9; c++) {
            const val = board[r][c];

            // Ignore empty cells
            if (val === '.') continue;

            // Calculate 3x3 sub-box index (0 to 8)
            const boxIndex = Math.floor(r / 3) * 3 + Math.floor(c / 3);

            // Check if the number has already appeared in the current row, column, or box
            if (rows[r].has(val) || cols[c].has(val) || boxes[boxIndex].has(val)) {
                return false;
            }

            // Record the number
            rows[r].add(val);
            cols[c].add(val);
            boxes[boxIndex].add(val);
        }
    }

    return true;
};