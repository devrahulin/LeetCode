class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        left = 0
        right = len(height) - 1
        max_water = 0

        while left < right:
            # Area is width multiplied by the shorter boundary line
            width = right - left
            current_height = min(height[left], height[right])
            current_area = width * current_height
            
            if current_area > max_water:
                max_water = current_area

            # Move the pointer pointing to the shorter vertical line inward
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1

        return max_water