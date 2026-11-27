/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solveSudoku = function(board) {
    // Bitmasks to track used digits (1-9) in rows, cols, and 3x3 boxes
    const rows = new Array(9).fill(0);
    const cols = new Array(9).fill(0);
    const boxes = new Array(9).fill(0);
    const emptyCells = [];

    // Pre-populate masks with pre-existing numbers and collect empty cells
    for (let r = 0; r < 9; r++) {
        for (let c = 0; c < 9; c++) {
            if (board[r][c] === '.') {
                emptyCells.push([r, c]);
            } else {
                const digit = Number(board[r][c]);
                const mask = 1 << digit;
                const b = Math.floor(r / 3) * 3 + Math.floor(c / 3);

                rows[r] |= mask;
                cols[c] |= mask;
                boxes[b] |= mask;
            }
        }
    }

    function backtrack(index) {
        // Base case: all empty cells successfully filled
        if (index === emptyCells.length) {
            return true;
        }

        const [r, c] = emptyCells[index];
        const b = Math.floor(r / 3) * 3 + Math.floor(c / 3);

        for (let digit = 1; digit <= 9; digit++) {
            const mask = 1 << digit;

            // Check if digit is valid in the current row, column, and sub-box
            if ((rows[r] & mask) === 0 && (cols[c] & mask) === 0 && (boxes[b] & mask) === 0) {
                // Place digit
                board[r][c] = String(digit);
                rows[r] |= mask;
                cols[c] |= mask;
                boxes[b] |= mask;

                if (backtrack(index + 1)) {
                    return true;
                }

                // Backtrack
                board[r][c] = '.';
                rows[r] &= ~mask;
                cols[c] &= ~mask;
                boxes[b] &= ~mask;
            }
        }

        return false;
    }

    backtrack(0);
};