var buildTree = function(inorder, postorder) {
    const inorderMap = new Map();
    for (let i = 0; i < inorder.length; i++) {
        inorderMap.set(inorder[i], i);
    }
    let postIdx = postorder.length - 1;

    function build(inLeft, inRight) {
        if (inLeft > inRight) return null;

        const rootVal = postorder[postIdx--];
        const root = new TreeNode(rootVal);
        const mid = inorderMap.get(rootVal);

        root.right = build(mid + 1, inRight);
        root.left = build(inLeft, mid - 1);
        return root;
    }

    return build(0, inorder.length - 1);
};
