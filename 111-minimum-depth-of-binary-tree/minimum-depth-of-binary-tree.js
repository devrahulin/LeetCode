var minDepth = function(root) {
    if (!root) return 0;
    const queue = [[root, 1]];
    let head = 0;

    while (head < queue.length) {
        const [node, depth] = queue[head++];
        if (!node.left && !node.right) {
            return depth;
        }
        if (node.left) queue.push([node.left, depth + 1]);
        if (node.right) queue.push([node.right, depth + 1]);
    }

    return 0;
};
