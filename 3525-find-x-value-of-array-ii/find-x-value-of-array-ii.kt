class Solution {
    fun resultArray(nums: IntArray, k: Int, queries: Array<IntArray>): IntArray {
        val n = nums.size
        val treeSize = 4 * n
        val treeProd = IntArray(treeSize)
        val treeCount = Array(treeSize) { _ -> IntArray(k) }

        fun pull(node: Int) {
            val leftChild = 2 * node
            val rightChild = 2 * node + 1

            treeProd[node] = (treeProd[leftChild] * treeProd[rightChild]) % k

            for (rem in 0 until k) {
                treeCount[node][rem] = treeCount[leftChild][rem]
            }

            val pL = treeProd[leftChild]
            for (rem in 0 until k) {
                val nextRem = (pL * rem) % k
                treeCount[node][nextRem] += treeCount[rightChild][rem]
            }
        }

        fun build(node: Int, l: Int, r: Int) {
            if (l == r) {
                val rem = nums[l] % k
                treeProd[node] = rem
                treeCount[node][rem] = 1
                return
            }
            val mid = l + (r - l) / 2
            build(2 * node, l, mid)
            build(2 * node + 1, mid + 1, r)
            pull(node)
        }

        fun update(node: Int, l: Int, r: Int, idx: Int, value: Int) {
            if (l == r) {
                val rem = value % k
                treeProd[node] = rem
                for (remIdx in 0 until k) {
                    treeCount[node][remIdx] = 0
                }
                treeCount[node][rem] = 1
                return
            }
            val mid = l + (r - l) / 2
            if (idx <= mid) {
                update(2 * node, l, mid, idx, value)
            } else {
                update(2 * node + 1, mid + 1, r, idx, value)
            }
            pull(node)
        }

        class Result(val prod: Int, val count: IntArray)

        fun query(node: Int, l: Int, r: Int, ql: Int, qr: Int): Result? {
            if (ql <= l && r <= qr) {
                return Result(treeProd[node], treeCount[node])
            }
            val mid = l + (r - l) / 2
            if (qr <= mid) {
                return query(2 * node, l, mid, ql, qr)
            }
            if (ql > mid) {
                return query(2 * node + 1, mid + 1, r, ql, qr)
            }

            val leftRes = query(2 * node, l, mid, ql, qr)!!
            val rightRes = query(2 * node + 1, mid + 1, r, ql, qr)!!

            val mergedProd = (leftRes.prod * rightRes.prod) % k
            val mergedCount = IntArray(k)

            for (rem in 0 until k) {
                mergedCount[rem] = leftRes.count[rem]
            }
            for (rem in 0 until k) {
                val nextRem = (leftRes.prod * rem) % k
                mergedCount[nextRem] += rightRes.count[rem]
            }

            return Result(mergedProd, mergedCount)
        }

        build(1, 0, n - 1)

        val result = IntArray(queries.size)
        for (i in queries.indices) {
            val idx = queries[i][0]
            val value = queries[i][1]
            val start = queries[i][2]
            val x = queries[i][3]

            nums[idx] = value
            update(1, 0, n - 1, idx, value)

            val res = query(1, 0, n - 1, start, n - 1)
            result[i] = res?.count?.get(x) ?: 0
        }

        return result
    }

    fun findXValue(nums: IntArray, k: Int, queries: Array<IntArray>): IntArray {
        return resultArray(nums, k, queries)
    }
}