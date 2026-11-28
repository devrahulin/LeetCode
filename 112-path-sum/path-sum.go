func hasPathSum(root *TreeNode, targetSum int) bool {
    if root == nil {
        return false
    }
    if root.Left == nil && root.Right == nil {
        return root.Val == targetSum
    }
    rem := targetSum - root.Val
    return hasPathSum(root.Left, rem) || hasPathSum(root.Right, rem)
}
