class ThreeSumBinarySearch : ThreeSum {

    override fun threeSum(nums: IntArray): List<Triple<Int, Int, Int>> {
        val resultSet = mutableListOf<Triple<Int, Int, Int>>()

        // for binary search
        nums.sort()

        for (i in nums.indices) {
            for (j in i + 1..nums.lastIndex) {
                val a = nums[i]
                val b = nums[j]
                val lookFor = -(a + b)
                val result = BinarySearchIterative().find(lookFor, nums)

                if (result != null && result > j)
                    resultSet += Triple(a, b, lookFor)
            }
        }

        return resultSet
    }
}
