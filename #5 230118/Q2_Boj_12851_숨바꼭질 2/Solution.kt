import java.util.LinkedList

const val MAX = 100_000
var n = 0
var k = 0
lateinit var time: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        k = it[1]
    }
    time = IntArray(MAX + 1)
}

fun solve() {
    // 왼쪽으로 가는 경우는 오직 한 가지 밖에 없으므로
    // 처음에 n이 k 이상이면 bfs를 돌릴 가치가 없다.
    if (n >= k) {
        print(buildString {
            appendLine(n - k)
            appendLine(1)
        })
        return
    }

    val count = bfs()
    print(buildString {
        appendLine(time[k])
        appendLine(count)
    })
}

fun bfs(): Int {
    var count = 0 // 최단 경로로 k 까지 가는 경우의 수
    var min = Int.MAX_VALUE // k 까지 가는 최단 경로
    val que = LinkedList<Int>().apply {
        add(n)
        time[n] = 0
    }

    while (que.isNotEmpty()) {
        val loc = que.poll()

        // 현재 지점 까지 온 시간이 목적지 까지 가는 시간 이상이면 더 이상 탐색할 가치가 없다.
        if (time[loc] >= min) continue

        listOf(loc - 1, loc + 1, loc * 2).forEach { next ->
            when {
                // case 1 : 다음 지점이 범위를 벗어나면 continue
                next !in 0 .. MAX || next == n -> {}

                // case 2 : 다음 지점이 목적지 이면 que 삽입 대신 count와 min 갱신
                next == k -> {
                    time[k] = time[loc] + 1
                    min = time[k]
                    count++
                }

                // case 3 : 다음 지점에 방문한 적이 없거나 한 번더 방문할 가치가 있으면 탐색
                time[next] == 0 || time[next] == time[loc] + 1 -> {
                    que.add(next)
                    time[next] = time[loc] + 1
                }
            }
        }
    }

    return count
}

fun main() {
    input()
    solve()
}