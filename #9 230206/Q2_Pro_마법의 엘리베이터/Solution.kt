import kotlin.math.min

class Solution {
    var ans = Int.MAX_VALUE
    lateinit var nums: List<Int>

    fun solution(storey: Int): Int {
        // 주어진 수를 정수형 리스트로 저장
        nums = storey.toString().map { it.digitToInt() }

        dfs(nums.lastIndex, 0, 0)

        return ans
    }

    /**
     * @param k : nums[k]를 고려할 것이다
     * @param sum : 지금까지 소모한 마법의 돌 개수
     * @param flag : 올림 수가 있는지 없는지 (0 or 1)
     */
    fun dfs(k: Int, sum: Int, flag: Int) {
        // 기저 조건
        if (k < 0) {
            ans = min(ans, sum + flag)
            return
        }

        val num = nums[k] + flag

        dfs(k - 1, sum + num, 0) // num 만큼 뺀 경우
        dfs(k - 1, sum + 10 - num, 1) // (10 - num) 만큼 더한 경우
    }
}