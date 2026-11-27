class Solution {
    public int minMoves(int[] nums) {
        int minVal = nums[0];
        for (int x : nums) {
            if (x < minVal) minVal = x;
        }
        
        int moves = 0;
        for (int x : nums) {
            moves += (x - minVal);
        }
        return moves;
    }
}
