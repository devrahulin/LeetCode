class Solution:
    def merge(self, intervals: list[list[int]]) -> list[list[int]]:
        if not intervals:
            return []

        # Sort intervals primarily by start time
        intervals.sort(key=lambda x: x[0])

        merged = [intervals[0]]

        for current in intervals[1:]:
            last_merged = merged[-1]

            # Overlap occurs if the current start is <= the end of the previous interval
            if current[0] <= last_merged[1]:
                # Merge by extending the end to the maximum of both ends
                last_merged[1] = max(last_merged[1], current[1])
            else:
                # No overlap: append current interval as a new independent segment
                merged.append(current)

        return merged