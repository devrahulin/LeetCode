import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting enables early pruning when a candidate exceeds the remaining target
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> current, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Prune search: since candidates is sorted, subsequent elements will also exceed remain
            if (candidates[i] > remain) {
                break;
            }

            current.add(candidates[i]);
            // Pass 'i' instead of 'i + 1' because elements can be reused
            backtrack(candidates, remain - candidates[i], i, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}