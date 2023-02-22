class Solution {
    var end = 0
    lateinit var count: IntArray // count[i]: i의 약수 개수
    lateinit var dp: IntArray    // dp[i]: i ~ e의 수 중 가장 많이 등장한 수

    fun solution(e: Int, starts: IntArray): IntArray {
        end = e
        init()

        dp = IntArray(end + 1) { end }
        var max = count[end] // 최대로 등장한 횟수(최대 약수 개수)를 저장할 변수

        for (i in end - 1 downTo 1) {
            if (count[i] >= max) { // max가 갱신되면
                max = count[i]
                dp[i] = i // 현재 수가 가장 많이 등장한 가장 작은 수 이다
            }
            else dp[i] = dp[i + 1]
        }

        return starts.map { dp[it] }.toIntArray()
    }

    // 1 ~ e 각각의 약수를 저장
    fun init() {
        count = IntArray(end + 1)
        for (i in 1..end) {
            for (j in i..end step i) {
                count[j]++
            }
        }
    }
}