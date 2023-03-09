
var n = 0
lateinit var need: IntArray
lateinit var pay: IntArray
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    need = IntArray(n + 1)
    pay = IntArray(n + 1)
    dp = IntArray(n + 2)

    for (i in 1..n) readLine().split(" ").map { it.toInt() }.let {
        need[i] = it[0]
        pay[i] = it[1]
    }
}

fun solve() {
    /**
     * dp[x] => x일에 퇴사할 때 얻을 수 있는 최대 수익
     * 바깥 루프 i => i일에 퇴사한다 가정
     * 내부 루프 j => j일에 상담을 한다고 가정
     */
    for (i in 1..n + 1) {
        for (j in i - 5 until i) {
            if (j < 1) continue

            dp[i] = maxOf(dp[i], dp[j])
            if (need[j] + j <= i) { // 만약 j일에 상담을 했을 때 퇴사일을 넘지 않으면 상담이 가능하다.
                dp[i] = maxOf(
                    dp[i],
                    dp[j] + pay[j]
                )
            }
        }
    }
    println(dp[n + 1])
}

fun main() {
    input()
    solve()
}