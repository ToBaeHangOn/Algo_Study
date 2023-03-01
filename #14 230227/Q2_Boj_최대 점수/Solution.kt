import kotlin.math.max

var n = 0
var start = 0
lateinit var scores: IntArray
lateinit var dp: LongArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        start = it[1] - 1
    }
    scores = readLine().split(" ").map { it.toInt() }.toIntArray()
    dp = LongArray(n)
}

fun solve() {
    for (i in start - 1 downTo 0) {
        dp[i] = maxOf(
            dp[i + 1] + scores[i + 1],
            dp[i + 2]  + scores[i + 2],
            dp[i]
        )
    }
}

fun main() {
    input()
    solve()
}