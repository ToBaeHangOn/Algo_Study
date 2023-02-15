import java.util.LinkedList

class Solution {
    var n = 0
    var dest = 0
    lateinit var graph: Array<HashSet<Int>> // 양방향 그래프
    lateinit var dist: IntArray // 목적지까지의 거리를 저장할 배열

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        this.n = n
        this.dest = destination
        graph = Array(n + 1) { hashSetOf() }
        dist = IntArray(n + 1) { -1 }

        // 양방향 그래프 저장
        roads.forEach {
            val (from, to) = it

            graph[from].add(to)
            graph[to].add(from)
        }

        bfs()

        return sources.map { dist[it] }.toIntArray()
    }

    // a 지점에서 출발하여 목적지에 가는 경로나
    // 목적지에서 출발하여 a로 가는 경로나 그 거리는 같다.
    // 따라서 아예 목적지에서 출발하여 모든 지점으로 이동하며 그 거리를 저장한다.
    fun bfs() {
        val que = LinkedList<Int>()
        que.add(dest)
        dist[dest] = 0

        while (que.isNotEmpty()) {
            val from = que.poll()

            graph[from].forEach { to ->
                // visit 체크
                if (dist[to] != -1) return@forEach

                que.add(to)
                dist[to] = dist[from] + 1
            }
        }
    }
}