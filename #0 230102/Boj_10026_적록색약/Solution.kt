import java.util.*

var n = 0
lateinit var board: Array<String>
lateinit var visit_normal: Array<BooleanArray>
lateinit var visit_colorWeakness: Array<BooleanArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine() }
    visit_normal = Array(n) { BooleanArray(n) }
    visit_colorWeakness = Array(n) { BooleanArray(n) }
}

fun solve() {
    var normal = 0
    var colorWeakness = 0
    repeat(n) { row ->
        repeat(n) { col ->
            if (!visit_normal[row][col]) {
                bfs(row, col, false)
                normal++
            }
            if (!visit_colorWeakness[row][col]) {
                bfs(row, col, true)
                colorWeakness++
            }
        }
    }
    println("$normal $colorWeakness")
}

fun bfs(sr: Int, sc: Int, flag: Boolean) {
    val visit = if (flag) visit_colorWeakness else visit_normal
    val que = LinkedList<Int>().apply {
        add(sr)
        add(sc)
        visit[sr][sc] = true
    }
    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            when {
                nr !in 0 until n || nc !in 0 until n -> return@forEach

                visit[nr][nc] -> return@forEach

                !flag && board[nr][nc] != board[sr][sc] -> return@forEach

                flag && board[sr][sc] == 'B' && board[nr][nc] != 'B' -> return@forEach

                flag && board[sr][sc] != 'B' && board[nr][nc] == 'B' -> return@forEach

                else -> que.apply {
                    add(nr)
                    add(nc)
                    visit[nr][nc] = true
                }
            }
        }
    }
}

fun main() {
    input()
    solve()
}