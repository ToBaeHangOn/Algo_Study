import java.util.LinkedList

class Solution {

    var n = 0
    lateinit var graph: Array<HashSet<Int>>
    lateinit var indeg: IntArray
    lateinit var isLightOn: BooleanArray
    lateinit var visit: BooleanArray

    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        this.n = n
        graph = Array(n + 1) { hashSetOf() }
        indeg = IntArray(n + 1)
        isLightOn = BooleanArray(n + 1)
        visit = BooleanArray(n + 1)

        lighthouse.forEach { edge ->
            val (from, to) = edge

            // 그래프(트리)를 형성하면서
            graph[from].add(to)
            graph[to].add(from)

            // 각각의 차수를 증가시킨다.
            indeg[from]++
            indeg[to]++
        }

        bfs()

        return isLightOn.count { it }
    }

    fun bfs() {
        val que = LinkedList<Int>()
        (1..n).forEach {
            // 차수가 1인 노드(리프 노드)를 큐에 우선 삽입한다.
            if (indeg[it] == 1) {
                que.add(it)
                visit[it] = true
            }
        }

        while (que.isNotEmpty()) {
            val from = que.poll()
            graph[from].forEach { to ->
                // 상대방 노드를 방문한 적이 있으면 continue
                if (visit[to]) return@forEach

                // 현재 노드가 꺼져있으면 상대방 노드를 켠다.
                if (!isLightOn[from]) isLightOn[to] = true
                if (--indeg[to] == 1) {
                    que.add(to)
                    visit[to] = true
                }
            }
        }
    }
}

fun main() {
    val n = 11
    val lighthouse = arrayOf(
        intArrayOf(6, 11),
        intArrayOf(4, 1),
        intArrayOf(5, 1),
        intArrayOf(5, 11),
        intArrayOf(7, 6),
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(6, 8),
        intArrayOf(2, 9),
        intArrayOf(9, 10)
    )
    println(
        Solution().solution(n, lighthouse)
    )
}