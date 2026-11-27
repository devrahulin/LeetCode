func restoreIpAddresses(s string) []string {
    n := len(s)
    var res []string
    if n < 4 || n > 12 {
        return res
    }

    var parts [4]string
    var backtrack func(start, idx int)
    backtrack = func(start, idx int) {
        if idx == 4 {
            if start == n {
                res = append(res, parts[0]+"."+parts[1]+"."+parts[2]+"."+parts[3])
            }
            return
        }

        remParts := 4 - idx
        remChars := n - start
        if remChars < remParts || remChars > remParts*3 {
            return
        }

        val := 0
        for length := 1; length <= 3 && start+length <= n; length++ {
            if length > 1 && s[start] == '0' {
                break
            }
            val = val*10 + int(s[start+length-1]-'0')
            if val <= 255 {
                parts[idx] = s[start : start+length]
                backtrack(start+length, idx+1)
            }
        }
    }

    backtrack(0, 0)
    return res
}
