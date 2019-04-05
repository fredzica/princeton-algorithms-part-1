class ThreeSumBruteForce {

    fun threeSum(nums: IntArray): List<List<Int>> {
        val resultSet = mutableListOf<List<Int>>()

        val newNums = nums.sortedArray()

        for (i in newNums.indices) {
            for (j in i + 1..newNums.lastIndex) {
                for (t in j + 1..newNums.lastIndex) {
                    val a = newNums[i]
                    val b = newNums[j]
                    val c = newNums[t]
                    if (a + b + c == 0)
                        resultSet.add(listOf(a, b, c))
                }
            }
        }

        return resultSet
    }
}
