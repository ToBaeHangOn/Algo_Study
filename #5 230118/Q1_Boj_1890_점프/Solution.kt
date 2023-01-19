
var n = 0
lateinit var board: Array<IntArray>
lateinit var dp: Array<LongArray>

// 오른쪽, 아래 방향으로만 이동한다.
val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1)
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    dp = Array(n) { LongArray(n) { -1L } }

    // dp[x][y] -> x, y 지점에서 목적지 까지 가는 경로의 수
    // 목적지에서 목적지로 가는 경로는 한 가지
    dp[n - 1][n - 1] = 1
}

fun solve() {
    println(dfs(0, 0))
}

fun dfs(row: Int, col: Int): Long {
    // 이미 해당 지점을 방문한 적이 있으면 dp 리턴
    if (dp[row][col] != -1L) return dp[row][col]

    dp[row][col] = 0
    dir.forEach {
        val nr = row + it[0] * board[row][col]
        val nc = col + it[1] * board[row][col]

        if (nr !in 0 until n || nc !in 0 until n) return@forEach

        // 백트랙킹으로 현 지점에서 목적지 까지의 경우를 누적 시킨다.
        dp[row][col] += dfs(nr, nc)
    }

    return dp[row][col]
}

fun main() {
    input()
    solve()
}