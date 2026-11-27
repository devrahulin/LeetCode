function smallestUniqueSubarray(nums: number[]): number {
    const n = nums.length;
    if (n === 1) return 1;

    let maxVal = 0;
    for (let i = 0; i < n; i++) {
        if (nums[i] > maxVal) maxVal = nums[i];
    }

    let sa = new Int32Array(n);
    let rk = new Int32Array(n);
    let oldRk = new Int32Array(n);
    let id = new Int32Array(n);
    const cntSize = Math.max(n, maxVal + 1);
    const cnt = new Int32Array(cntSize);

    for (let i = 0; i < n; i++) {
        rk[i] = nums[i];
        cnt[rk[i]]++;
    }
    for (let i = 1; i <= maxVal; i++) cnt[i] += cnt[i - 1];
    for (let i = n - 1; i >= 0; i--) {
        sa[--cnt[rk[i]]] = i;
    }

    oldRk.set(rk);
    let p = 0;
    rk[sa[0]] = 0;
    for (let i = 1; i < n; i++) {
        if (oldRk[sa[i]] !== oldRk[sa[i - 1]]) p++;
        rk[sa[i]] = p;
    }
    let m = p + 1;

    for (let w = 1; m < n; w <<= 1) {
        let idx = 0;
        for (let i = n - w; i < n; i++) id[idx++] = i;
        for (let i = 0; i < n; i++) {
            if (sa[i] >= w) id[idx++] = sa[i] - w;
        }

        cnt.fill(0, 0, m);
        for (let i = 0; i < n; i++) cnt[rk[id[i]]]++;
        for (let i = 1; i < m; i++) cnt[i] += cnt[i - 1];
        for (let i = n - 1; i >= 0; i--) {
            const u = id[i];
            sa[--cnt[rk[u]]] = u;
        }

        const tmp = oldRk;
        oldRk = rk;
        rk = tmp;

        p = 0;
        rk[sa[0]] = 0;
        for (let i = 1; i < n; i++) {
            const u = sa[i];
            const v = sa[i - 1];
            const u2 = u + w < n ? oldRk[u + w] : -1;
            const v2 = v + w < n ? oldRk[v + w] : -1;
            if (oldRk[u] !== oldRk[v] || u2 !== v2) p++;
            rk[u] = p;
        }
        m = p + 1;
    }

    const rank = new Int32Array(n);
    for (let i = 0; i < n; i++) rank[sa[i]] = i;

    const lcp = new Int32Array(n);
    let h = 0;
    for (let i = 0; i < n; i++) {
        if (rank[i] === 0) {
            h = 0;
            continue;
        }
        const j = sa[rank[i] - 1];
        while (i + h < n && j + h < n && nums[i + h] === nums[j + h]) {
            h++;
        }
        lcp[rank[i]] = h;
        if (h > 0) h--;
    }

    let ans = n;
    for (let i = 0; i < n; i++) {
        const leftLcp = i > 0 ? lcp[i] : 0;
        const rightLcp = i + 1 < n ? lcp[i + 1] : 0;
        const maxShared = leftLcp > rightLcp ? leftLcp : rightLcp;
        const neededLen = maxShared + 1;
        const suffixLen = n - sa[i];
        if (neededLen <= suffixLen && neededLen < ans) {
            ans = neededLen;
        }
    }

    return ans;
}
