#include <string>
#include <stack>
#include <algorithm>

class Solution {
public:
    int longestValidParentheses(std::string s) {
        // Stack stores indices; initialize with -1 as a base reference index
        std::stack<int> st;
        st.push(-1);
        int max_len = 0;

        for (int i = 0; i < static_cast<int>(s.length()); ++i) {
            if (s[i] == '(') {
                // Push the index of the open parenthesis
                st.push(i);
            } else {
                // Pop the previous '(' index or boundary marker
                st.pop();
                if (st.empty()) {
                    // Stack is empty: this ')' is an unmatched boundary marker
                    st.push(i);
                } else {
                    // Valid substring found from st.top() to i
                    max_len = std::max(max_len, i - st.top());
                }
            }
        }

        return max_len;
    }
};