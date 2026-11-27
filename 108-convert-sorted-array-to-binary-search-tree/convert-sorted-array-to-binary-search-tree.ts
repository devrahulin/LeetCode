function sortedArrayToBST(nums: number[]): TreeNode | null {
    function build(left: number, right: number): TreeNode | null {
        if (left > right) return null;
        const mid = (left + right) >> 1;
        return new TreeNode(
            nums[mid],
            build(left, mid - 1),
            build(mid + 1, right)
        );
    }
    return build(0, nums.length - 1);
}
