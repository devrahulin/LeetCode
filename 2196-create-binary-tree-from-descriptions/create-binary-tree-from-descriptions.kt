class Solution {
    fun createBinaryTree(descriptions: Array<IntArray>): TreeNode? {
        val nodeMap = HashMap<Int, TreeNode>()
        val children = HashSet<Int>()

        for (desc in descriptions) {
            val parentVal = desc[0]
            val childVal = desc[1]
            val isLeft = desc[2] == 1

            val parentNode = nodeMap.getOrPut(parentVal) { TreeNode(parentVal) }
            val childNode = nodeMap.getOrPut(childVal) { TreeNode(childVal) }

            if (isLeft) {
                parentNode.left = childNode
            } else {
                parentNode.right = childNode
            }

            children.add(childVal)
        }

        for (desc in descriptions) {
            val parentVal = desc[0]
            if (!children.contains(parentVal)) {
                return nodeMap[parentVal]
            }
        }

        return null
    }
}