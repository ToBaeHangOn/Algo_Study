import java.util.LinkedList

class Solution {
    lateinit var graph: Array<HashSet<Int>>
    lateinit var visit: BooleanArray

    fun solution(n: Int, edge: Array<IntArray>): Int {
        graph = Array(n + 1) { hashSetOf() }
        edge.forEach { (from, to) ->
            graph[from].add(to)
            graph[to].add(from)
        }
        visit = BooleanArray(n + 1)

        return bfs()
    }

    fun bfs(): Int {
        var maxDist = 0 // 탐색한 최대 거리
        var count = 0   // maxDist 만큼 떨어진 노드의 갯수

        val que = LinkedList<Int>().apply {
            add(1)
            add(0)
            visit[1] = true
        }
        while (que.isNotEmpty()) {
            val from = que.poll()
            val dist = que.poll()

            // 현재 큐에서 꺼낸 노드까지의 거리에 따라 maxDist와 count 갱신
            when {
                dist > maxDist -> {
                    maxDist = dist
                    count = 1
                }
                dist == maxDist -> count++
            }

            graph[from].forEach { to ->
                if (visit[to]) return@forEach

                que.add(to)
                que.add(dist + 1)
                visit[to] = true
            }
        }

        return count
    }
}