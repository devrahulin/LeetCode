class Solution {
    fun asteroidsDestroyed(mass: Int, asteroids: IntArray): Boolean {
        asteroids.sort()
        var currentMass = mass.toLong()

        for (asteroid in asteroids) {
            if (currentMass < asteroid) {
                return false
            }
            currentMass += asteroid
        }

        return true
    }
}