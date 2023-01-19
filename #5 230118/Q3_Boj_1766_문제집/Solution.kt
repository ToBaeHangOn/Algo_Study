import java.util.PriorityQueue

var n = 0
var m = 0
lateinit var graph: Array<HashSet<Int>>
lateinit var indegree: IntArray
lateinit var visit: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    graph = Array(n + 1) { hashSetOf() }
    indegree = IntArray(n + 1)
    visit = BooleanArray(n + 1)

    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }

        graph[from].add(to)
        indegree[to]++
    }
}

fun solve() {
    val sb = StringBuilder()
    val que = PriorityQueue<Int>() // 난이도가 쉬운 문제에 우선권을 부여한다.
    (1 .. n).forEach {
        // 차수가 0인 문제 (먼저 풀면 좋은 문제)를 큐에 미리 삽입한다.
        if (indegree[it] == 0) {
            que.add(it)
            visit[it] = true
        }
    }

    while (que.isNotEmpty()) {
        val from = que.poll()
        sb.append(from).append(' ')

        // 현재 문제에 방향성을 갖는 문제들의 차수를 감소시키고
        // 차수가 0이 되면 큐에 삽입한다.
        graph[from].forEach { to ->
            if (!visit[to] && --indegree[to] == 0) {
                que.add(to)
                visit[to] = true
            }
        }
    }

    println(sb)
}

fun main() {
    input()
    solve()
}