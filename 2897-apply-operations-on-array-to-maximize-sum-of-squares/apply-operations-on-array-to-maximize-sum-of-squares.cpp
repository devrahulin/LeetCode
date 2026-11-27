#include <vector>

class Solution {
public:
    int maxSum(std::vector<int>& nums, int k) {
        const int MOD = 1'000'000'007;
        
        // Count the frequency of each bit (0 to 30) across all elements
        std::vector<int> bitCount(31, 0);
        for (int num : nums) {
            for (int b = 0; b <= 30; ++b) {
                if ((num >> b) & 1) {
                    bitCount[b]++;
                }
            }
        }
        
        long long totalSum = 0;
        
        // Greedily build the top k largest numbers
        for (int i = 0; i < k; ++i) {
            long long currentVal = 0;
            for (int b = 0; b <= 30; ++b) {
                if (bitCount[b] > 0) {
                    currentVal |= (1LL << b);
                    bitCount[b]--;
                }
            }
            long long squared = (currentVal % MOD) * (currentVal % MOD) % MOD;
            totalSum = (totalSum + squared) % MOD;
        }
        
        return totalSum;
    }
};