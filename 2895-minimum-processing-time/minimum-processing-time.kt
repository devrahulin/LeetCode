import kotlin.math.max

class Solution {
    fun minProcessingTime(processorTime: MutableList<Int>, tasks: MutableList<Int>): Int {
        processorTime.sort()
        tasks.sortDescending()

        var maxTime = 0

        for (i in processorTime.indices) {
            val taskTime = tasks[i * 4]
            maxTime = max(maxTime, processorTime[i] + taskTime)
        }

        return maxTime
    }
}