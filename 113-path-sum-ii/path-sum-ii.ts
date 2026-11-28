function pathSum(root: TreeNode | null, targetSum: number): number[][] {
    const res: number[][] = [];
    const path: number[] = [];

    function dfs(node: TreeNode | null, rem: number): void {
        if (!node) return;
        path.push(node.val);
        const nextRem = rem - node.val;
        if (!node.left && !node.right && nextRem === 0) {
            res.push([...path]);
        } else {
            dfs(node.left, nextRem);
            dfs(node.right, nextRem);
        }
        path.pop();
    }

    dfs(root, targetSum);
    return res;
}
