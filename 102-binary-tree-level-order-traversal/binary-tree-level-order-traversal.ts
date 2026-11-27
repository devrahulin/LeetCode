function levelOrder(root: TreeNode | null): number[][] {
    if (!root) return [];
    const result: number[][] = [];
    let queue: TreeNode[] = [root];

    while (queue.length > 0) {
        const level: number[] = [];
        const nextQueue: TreeNode[] = [];
        for (const node of queue) {
            level.push(node.val);
            if (node.left) nextQueue.push(node.left);
            if (node.right) nextQueue.push(node.right);
        }
        result.push(level);
        queue = nextQueue;
    }

    return result;
}
