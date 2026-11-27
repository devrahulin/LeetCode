func levelOrderBottom(root *TreeNode) [][]int {
    var res [][]int
    if root == nil {
        return res
    }

    queue := []*TreeNode{root}
    for len(queue) > 0 {
        sz := len(queue)
        level := make([]int, sz)
        for i := 0; i < sz; i++ {
            node := queue[0]
            queue = queue[1:]
            level[i] = node.Val
            if node.Left != nil {
                queue = append(queue, node.Left)
            }
            if node.Right != nil {
                queue = append(queue, node.Right)
            }
        }
        res = append(res, level)
    }

    for l, r := 0, len(res)-1; l < r; l, r = l+1, r-1 {
        res[l], res[r] = res[r], res[l]
    }

    return res
}
