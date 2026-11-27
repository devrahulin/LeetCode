impl Solution {
    pub fn max_alternating_sum(mut nums: Vec<i32>) -> i64 {
        nums.sort_unstable_by_key(|&x| (x as i64) * (x as i64));

        let n = nums.len();
        let num_pos = (n + 1) / 2;
        let mut score: i64 = 0;

        // The smallest n - num_pos elements contribute negatively
        for &x in &nums[..n - num_pos] {
            let val = x as i64;
            score -= val * val;
        }

        // The largest num_pos elements contribute positively
        for &x in &nums[n - num_pos..] {
            let val = x as i64;
            score += val * val;
        }

        score
    }
}