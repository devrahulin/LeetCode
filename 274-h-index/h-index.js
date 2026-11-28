var hIndex = function(citations) {
    const n = citations.length;
    const buckets = new Array(n + 1).fill(0);
    for (const c of citations) {
        buckets[Math.min(c, n)]++;
    }
    let count = 0;
    for (let h = n; h >= 0; h--) {
        count += buckets[h];
        if (count >= h) {
            return h;
        }
    }
    return 0;
};
