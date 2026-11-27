var generateTrees = function(n) {
    if (n === 0) return [];
    const memo = new Map();

    function build(start, end) {
        if (start > end) return [null];
        const key = start * 16 + end;
        if (memo.has(key)) return memo.get(key);

        const trees = [];
        for (let rootVal = start; rootVal <= end; rootVal++) {
            const leftTrees = build(start, rootVal - 1);
            const rightTrees = build(rootVal + 1, end);

            for (const left of leftTrees) {
                for (const right of rightTrees) {
                    trees.push(new TreeNode(rootVal, left, right));
                }
            }
        }

        memo.set(key, trees);
        return trees;
    }

    return build(1, n);
};
