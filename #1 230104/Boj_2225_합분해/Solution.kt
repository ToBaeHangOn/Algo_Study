
const val MOD = 1_000_000_000
var n = 0
var k = 0
lateinit var dp: Array<IntArray>
// dp[x][y] : x개의 수를 사용해 y를 만드는 경우의 수

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        k = this[1]
    }
    dp = Array(k + 1) { IntArray(n + 1) }
}

fun solve() {
    for (i in 0..n) dp[1][i] = 1
    for (count in 2..k) {
        for (target in 0..n) {
            for (num in 0 .. target) {
                dp[count][target] += dp[count - 1][target - num]
                dp[count][target] %= MOD
            }
        }
    }
    println(dp[k][n])
}

fun main() {
    input()
    solve()
}