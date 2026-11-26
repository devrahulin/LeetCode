from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def sufficientSubset(self, root: Optional[TreeNode], limit: int) -> Optional[TreeNode]:
        def dfs(node: Optional[TreeNode], path_sum: int) -> Optional[TreeNode]:
            if not node:
                return None

            # Base case: leaf node in the original tree
            if not node.left and not node.right:
                return node if path_sum >= limit else None

            # Recurse on existing children with updated cumulative path sums
            if node.left:
                node.left = dfs(node.left, path_sum + node.left.val)
            if node.right:
                node.right = dfs(node.right, path_sum + node.right.val)

            # If no children remain, every root-to-leaf path through this node was < limit
            if not node.left and not node.right:
                return None

            return node

        if not root:
            return None
        return dfs(root, root.val)

        