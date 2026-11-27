#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    void backtrack(vector<string>& result, string& current, int open, int close, int n) {
        // Base case: combination is complete
        if (current.length() == 2 * n) {
            result.push_back(current);
            return;
        }

        // Option 1: Add '(' if we haven't used all n open parentheses
        if (open < n) {
            current.push_back('(');
            backtrack(result, current, open + 1, close, n);
            current.pop_back(); // backtrack
        }

        // Option 2: Add ')' if it won't exceed the number of open parentheses
        if (close < open) {
            current.push_back(')');
            backtrack(result, current, open, close + 1, n);
            current.pop_back(); // backtrack
        }
    }

    vector<string> generateParenthesis(int n) {
        vector<string> result;
        string current = "";
        backtrack(result, current, 0, 0, n);
        return result;
    }
};