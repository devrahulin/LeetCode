/**
 * @param {number} n
 * @return {string}
 */
var countAndSay = function(n) {
    let current = "1";

    for (let step = 2; step <= n; step++) {
        let nextStr = "";
        let count = 1;

        for (let i = 0; i < current.length; i++) {
            // Check if current character matches the next one
            if (i + 1 < current.length && current[i] === current[i + 1]) {
                count++;
            } else {
                // Group finished: append count and the digit
                nextStr += count + current[i];
                count = 1;
            }
        }

        current = nextStr;
    }

    return current;
};