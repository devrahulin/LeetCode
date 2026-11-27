#include <vector>
#include <deque>

using namespace std;

class Solution {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        long long ans = 0;
        long long cost = 0;
        deque<int> dq; // Stores indices with strictly decreasing values in [l, r]
        int r = n - 1;

        for (int l = n - 1; l >= 0; --l) {
            // Merge blocks dominated by nums[l]
            while (!dq.empty() && nums[dq.back()] <= nums[l]) {
                int idx = dq.back();
                dq.pop_back();
                int next_idx = dq.empty() ? (r + 1) : dq.back();
                cost += 1LL * (next_idx - idx) * (nums[l] - nums[idx]);
            }
            dq.push_back(l);

            // Shrink from the right if operations exceed k
            while (cost > k) {
                cost -= (nums[dq.front()] - nums[r]);
                if (dq.front() == r) {
                    dq.pop_front();
                }
                --r;
            }

            ans += (r - l + 1);
        }

        return ans;
    }
};
