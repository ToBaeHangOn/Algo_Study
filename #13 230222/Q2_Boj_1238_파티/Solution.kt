import java.util.PriorityQueue

data class Info(
    val from: Int,
    val dist: Int,
)

data class Edge(
    val to: Int,
    val weight: Int,
)

var n = 0
var m = 0
var dest = 0
lateinit var graph: Array<HashSet<Edge>>
lateinit var dist: Array<IntArray> // dist[a][b]: a에서 b까지 가는 최단 거리

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
        dest = it[2]
    }
    graph = Array(n + 1) { hashSetOf() }
    dist = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }

    repeat(m) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Edge(to, weight))
    }
}

fun solve() {
    // 모든 마을을 출발지로 설정하여 각 마을 까지의 거리를 저장한다.
    (1..n).forEach { dijkstra(it) }

    println(
        (1..n).map { dist[it][dest] + dist[dest][it] }.maxOf { it }
    )
}

fun dijkstra(start: Int) {
    val que = PriorityQueue<Info>() { i1, i2 -> i1.dist - i2.dist }.apply {
        add(Info(start, 0))
        dist[start][start] = 0
    }

    while (que.isNotEmpty()) {
        val info = que.poll()

        if (dist[start][info.from] < info.dist) continue

        graph[info.from].forEach { edge ->
            val to = edge.to
            val next = dist[start][info.from] + edge.weight

            if (dist[start][to] <= next) return@forEach

            que.add(Info(to, next))
            dist[start][to] = next
        }
    }
}

fun main() {
    input()
    solve()
}