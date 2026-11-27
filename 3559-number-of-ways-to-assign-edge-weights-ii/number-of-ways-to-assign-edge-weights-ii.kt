class Solution {
    fun assignEdgeWeights(edges: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val n = edges.size + 1
        val mod = 1_000_000_007

        val head = IntArray(n + 1) { -1 }
        val to = IntArray(2 * n)
        val next = IntArray(2 * n)
        var edgeCount = 0

        fun addEdge(u: Int, v: Int) {
            to[edgeCount] = v
            next[edgeCount] = head[u]
            head[u] = edgeCount++
        }

        for (e in edges) {
            val u = e[0]
            val v = e[1]
            addEdge(u, v)
            addEdge(v, u)
        }

        val log = 18
        val up = Array(log) { IntArray(n + 1) }
        val depth = IntArray(n + 1)

        val queue = IntArray(n + 1)
        var qHead = 0
        var qTail = 0

        queue[qTail++] = 1
        depth[1] = 0
        up[0][1] = 1

        val visited = BooleanArray(n + 1)
        visited[1] = true

        while (qHead < qTail) {
            val u = queue[qHead++]
            var e = head[u]
            while (e != -1) {
                val v = to[e]
                if (!visited[v]) {
                    visited[v] = true
                    depth[v] = depth[u] + 1
                    up[0][v] = u
                    queue[qTail++] = v
                }
                e = next[e]
            }
        }

        for (j in 1 until log) {
            for (i in 1..n) {
                up[j][i] = up[j - 1][up[j - 1][i]]
            }
        }

        fun getLca(nodeA: Int, nodeB: Int): Int {
            var u = nodeA
            var v = nodeB
            if (depth[u] < depth[v]) {
                val temp = u
                u = v
                v = temp
            }

            val diff = depth[u] - depth[v]
            for (j in 0 until log) {
                if ((diff and (1 shl j)) != 0) {
                    u = up[j][u]
                }
            }

            if (u == v) return u

            for (j in log - 1 downTo 0) {
                if (up[j][u] != up[j][v]) {
                    u = up[j][u]
                    v = up[j][v]
                }
            }

            return up[0][u]
        }

        val pow2 = IntArray(n + 1)
        pow2[0] = 1
        for (i in 1..n) {
            pow2[i] = (pow2[i - 1] * 2) % mod
        }

        val ans = IntArray(queries.size)
        for (i in queries.indices) {
            val u = queries[i][0]
            val v = queries[i][1]
            if (u == v) {
                ans[i] = 0
            } else {
                val lca = getLca(u, v)
                val d = depth[u] + depth[v] - 2 * depth[lca]
                ans[i] = pow2[d - 1]
            }
        }

        return ans
    }
}