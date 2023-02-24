import java.util.LinkedList

var n = 0
var m = 0
val special = IntArray(101) { it } // 뱀 또는 사다리로 이동하는 특수 경로(자기 자신으로 초기화)
val visit = BooleanArray(101)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    repeat(n + m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        special[from] = to
    }
}

fun solve() {
    val que = LinkedList<Int>().apply {
        add(1)
        add(0)
        visit[1] = true
    }

    var ans = 0
    while (que.isNotEmpty()) {
        // 큐에서 꺼낸 지점에서 사다리 혹은 뱀을 타고 나서 탐색한다.
        val from = special[que.poll()]
        val dist = que.poll()

        if (from == 100) {
            ans = dist
            break
        }

        (1..6).forEach {
            val to = from + it

            if (to > 100 || visit[to]) return@forEach

            que.add(to)
            que.add(dist + 1)
            visit[to] = true
        }
    }

    println(ans)
}

fun main() {
    input()
    solve()
}