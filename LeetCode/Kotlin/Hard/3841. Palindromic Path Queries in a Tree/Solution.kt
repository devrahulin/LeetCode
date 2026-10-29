class Solution {
    fun palindromePath(n: Int, edges: Array<IntArray>, s: String, queries: Array<String>): BooleanArray {
        return palindromicPathQueries(n, edges, s, queries)
    }

    fun palindromicPathQueries(n: Int, edges: Array<IntArray>, s: String, queries: Array<String>): BooleanArray {
        val adj = Array(n) { _ -> IntArrayList() }
        for (e in edges) {
            val u = e[0]
            val v = e[1]
            adj[u].add(v)
            adj[v].add(u)
        }

        val parent = IntArray(n)
        val depth = IntArray(n)
        val heavy = IntArray(n) { _ -> -1 }
        val head = IntArray(n)
        val pos = IntArray(n)
        val chars = s.toCharArray()

        val stack = IntArray(n)
        val edgeIdx = IntArray(n)
        val order = IntArray(n)
        var orderSize = 0

        parent[0] = 0
        depth[0] = 0
        stack[0] = 0
        var top = 1

        while (top > 0) {
            val u = stack[top - 1]
            val idx = edgeIdx[u]
            if (idx < adj[u].size) {
                val v = adj[u].get(idx)
                edgeIdx[u]++
                if (v != parent[u]) {
                    parent[v] = u
                    depth[v] = depth[u] + 1
                    stack[top++] = v
                }
            } else {
                order[orderSize++] = u
                top--
            }
        }

        val size = IntArray(n) { _ -> 1 }
        for (i in 0 until n) {
            val u = order[i]
            var maxChildSize = 0
            for (j in 0 until adj[u].size) {
                val v = adj[u].get(j)
                if (v != parent[u]) {
                    size[u] += size[v]
                    if (size[v] > maxChildSize) {
                        maxChildSize = size[v]
                        heavy[u] = v
                    }
                }
            }
        }

        var curPos = 0
        val dfsStack = IntArray(n)
        val headStack = IntArray(n)
        var dfsTop = 0

        dfsStack[dfsTop] = 0
        headStack[dfsTop] = 0
        dfsTop++

        val initialVals = IntArray(n)

        while (dfsTop > 0) {
            dfsTop--
            val u = dfsStack[dfsTop]
            val h = headStack[dfsTop]

            head[u] = h
            pos[u] = curPos++
            initialVals[pos[u]] = 1 shl (chars[u] - 'a')

            val lightNodes = IntArrayList()
            for (j in 0 until adj[u].size) {
                val v = adj[u].get(j)
                if (v != parent[u] && v != heavy[u]) {
                    lightNodes.add(v)
                }
            }

            for (j in lightNodes.size - 1 downTo 0) {
                val v = lightNodes.get(j)
                dfsStack[dfsTop] = v
                headStack[dfsTop] = v
                dfsTop++
            }

            if (heavy[u] != -1) {
                dfsStack[dfsTop] = heavy[u]
                headStack[dfsTop] = h
                dfsTop++
            }
        }

        val bit = Fenwick(n)
        for (i in 0 until n) {
            bit.update(i + 1, initialVals[i])
        }

        fun queryRange(l: Int, r: Int): Int {
            return bit.query(r + 1) xor bit.query(l)
        }

        fun queryPath(nodeA: Int, nodeB: Int): Int {
            var u = nodeA
            var v = nodeB
            var res = 0
            while (head[u] != head[v]) {
                if (depth[head[u]] > depth[head[v]]) {
                    res = res xor queryRange(pos[head[u]], pos[u])
                    u = parent[head[u]]
                } else {
                    res = res xor queryRange(pos[head[v]], pos[v])
                    v = parent[head[v]]
                }
            }
            if (depth[u] > depth[v]) {
                res = res xor queryRange(pos[v], pos[u])
            } else {
                res = res xor queryRange(pos[u], pos[v])
            }
            return res
        }

        var queryCount = 0
        for (q in queries) {
            if (q[0] == 'q') queryCount++
        }

        val answer = BooleanArray(queryCount)
        var ansIdx = 0

        for (q in queries) {
            if (q[0] == 'u') {
                var idx = 7
                var u = 0
                while (idx < q.length && q[idx] != ' ') {
                    u = u * 10 + (q[idx] - '0')
                    idx++
                }
                val newChar = q[idx + 1]
                val oldMask = 1 shl (chars[u] - 'a')
                val newMask = 1 shl (newChar - 'a')
                chars[u] = newChar
                bit.update(pos[u] + 1, oldMask xor newMask)
            } else {
                var idx = 6
                var u = 0
                while (idx < q.length && q[idx] != ' ') {
                    u = u * 10 + (q[idx] - '0')
                    idx++
                }
                idx++
                var v = 0
                while (idx < q.length) {
                    v = v * 10 + (q[idx] - '0')
                    idx++
                }
                val mask = queryPath(u, v)
                answer[ansIdx++] = (mask and (mask - 1)) == 0
            }
        }

        return answer
    }

    private class IntArrayList(capacity: Int = 2) {
        var data = IntArray(capacity)
        var size = 0

        fun add(element: Int) {
            if (size == data.size) {
                data = data.copyOf(data.size * 2)
            }
            data[size++] = element
        }

        fun get(index: Int): Int = data[index]
    }

    private class Fenwick(val n: Int) {
        val tree = IntArray(n + 1)

        fun update(index: Int, delta: Int) {
            var i = index
            while (i <= n) {
                tree[i] = tree[i] xor delta
                i += i and -i
            }
        }

        fun query(index: Int): Int {
            var sum = 0
            var i = index
            while (i > 0) {
                sum = sum xor tree[i]
                i -= i and -i
            }
            return sum
        }
    }
}