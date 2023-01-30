import kotlin.math.max

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var n = 0
var k = 0
var target = 0 // 최종적으로 지어야 할 건물 (w)
lateinit var times: List<Int>
lateinit var graph: Array<HashSet<Int>>
lateinit var dp: IntArray

fun input() = with(reader) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        k = it[1]
    }
    times = "0 ${readLine()}".split(" ").map { it.toInt() }
    graph = Array(n + 1) { hashSetOf() }
    dp = IntArray(n + 1) { -1 }

    repeat(k) {
        // from을 짓기 위해서는 to를 필히 지어야 한다.
        val (to, from) = readLine().split(" ").map { it.toInt() }
        graph[from].add(to)
    }

    target = readLine().toInt()
}

fun solve() {
    builder.appendLine(dfs(target))
}

/**
 * from 건물을 짓기 위해서 필요한 최대 시간 return
 */
fun dfs(from: Int): Int {
    // 기저 조건 : 이미 from을 탐색한 적이 있거나 필수 건물이 없는 경우
    if (dp[from] != -1) return dp[from]
    if (graph[from].isEmpty()) return times[from]

    // from을 짓기 위해 필히 지어야 하는 건물 중 가장 오래 걸리는 시간을 구함
    var need = 0
    graph[from].forEach {
        need = max(need, dfs(it))
    }
    dp[from] = times[from] + need

    return dp[from]
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}