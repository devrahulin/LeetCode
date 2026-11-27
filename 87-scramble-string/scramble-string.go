func isScramble(s1 string, s2 string) bool {
    if len(s1) != len(s2) {
        return false
    }

    memo := make(map[string]bool)

    var helper func(a, b string) bool
    helper = func(a, b string) bool {
        if a == b {
            return true
        }
        key := a + "#" + b
        if val, ok := memo[key]; ok {
            return val
        }

        var count [26]int
        for i := 0; i < len(a); i++ {
            count[a[i]-'a']++
            count[b[i]-'a']--
        }
        for _, c := range count {
            if c != 0 {
                memo[key] = false
                return false
            }
        }

        l := len(a)
        for i := 1; i < l; i++ {
            if helper(a[:i], b[:i]) && helper(a[i:], b[i:]) {
                memo[key] = true
                return true
            }
            if helper(a[:i], b[l-i:]) && helper(a[i:], b[:l-i]) {
                memo[key] = true
                return true
            }
        }

        memo[key] = false
        return false
    }

    return helper(s1, s2)
}
