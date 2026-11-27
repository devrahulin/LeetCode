class Solution {
    private class Edge(val to: Int, val weight: Long)
    private class State(val dist: Long, val node: Int) : Comparable<State> {
        override fun compareTo(other: State): Int = this.dist.compareTo(other.dist)
    }

    fun minimumWeight(n: Int, edges: Array<IntArray>, src1: Int, src2: Int, dest: Int): Long {
        val graph = Array(n) { _ -> ArrayList<Edge>() }
        val revGraph = Array(n) { _ -> ArrayList<Edge>() }

        for (edge in edges) {
            val u = edge[0]
            val v = edge[1]
            val w = edge[2].toLong()
            graph[u].add(Edge(v, w))
            revGraph[v].add(Edge(u, w))
        }

        fun dijkstra(start: Int, adj: Array<ArrayList<Edge>>): LongArray {
            val dist = LongArray(n) { _ -> Long.MAX_VALUE }
            val pq = java.util.PriorityQueue<State>()

            dist[start] = 0L
            pq.add(State(0L, start))

            while (pq.isNotEmpty()) {
                val curr = pq.poll()
                val d = curr.dist
                val u = curr.node

                if (d > dist[u]) continue

                for (edge in adj[u]) {
                    val v = edge.to
                    val nextDist = d + edge.weight
                    if (nextDist < dist[v]) {
                        dist[v] = nextDist
                        pq.add(State(nextDist, v))
                    }
                }
            }

            return dist
        }

        val d1 = dijkstra(src1, graph)
        val d2 = dijkstra(src2, graph)
        val dDest = dijkstra(dest, revGraph)

        var ans = Long.MAX_VALUE

        for (i in 0 until n) {
            if (d1[i] != Long.MAX_VALUE && d2[i] != Long.MAX_VALUE && dDest[i] != Long.MAX_VALUE) {
                val total = d1[i] + d2[i] + dDest[i]
                if (total < ans) {
                    ans = total
                }
            }
        }

        return if (ans == Long.MAX_VALUE) -1L else ans
    }
}