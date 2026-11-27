class Solution(object):
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        nums.sort()
        closest_sum = nums[0] + nums[1] + nums[2]
        n = len(nums)

        for i in range(n - 2):
            left = i + 1
            right = n - 1

            while left < right:
                current_sum = nums[i] + nums[left] + nums[right]

                # Exact match found
                if current_sum == target:
                    return target

                # Update the closest sum if the current distance is smaller
                if abs(current_sum - target) < abs(closest_sum - target):
                    closest_sum = current_sum

                # Adjust pointers based on relation to target
                if current_sum < target:
                    left += 1
                else:
                    right -= 1

        return closest_sum