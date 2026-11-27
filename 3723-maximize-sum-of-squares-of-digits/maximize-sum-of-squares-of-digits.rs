impl Solution {
    pub fn max_sum_of_squares(num: i32, mut sum: i32) -> String {
        let n = num as usize;
        if (sum as usize) > n * 9 {
            return String::new();
        }

        let mut bytes = Vec::with_capacity(n);
        for _ in 0..n {
            let digit = sum.min(9);
            bytes.push(b'0' + digit as u8);
            sum -= digit;
        }

        String::from_utf8(bytes).unwrap()
    }
}