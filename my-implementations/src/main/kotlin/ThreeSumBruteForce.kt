class ThreeSumBruteForce : ThreeSum {

    override fun threeSum(nums: IntArray): List<Triple<Int, Int, Int>> {
        val resultSet = mutableListOf<Triple<Int, Int, Int>>()

        nums.sort()

        for (i in nums.indices) {
            for (j in i + 1..nums.lastIndex) {
                for (t in j + 1..nums.lastIndex) {
                    val a = nums[i]
                    val b = nums[j]
                    val c = nums[t]
                    if (a + b + c == 0)
                        resultSet.add(Triple(a, b, c))
                }
            }
        }

        return resultSet
    }
}
