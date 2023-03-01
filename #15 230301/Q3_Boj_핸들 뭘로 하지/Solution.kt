import java.util.LinkedList

var n = 0
var str = ""
var ans = LinkedList<Char>()
lateinit var graph: Array<HashSet<Int>>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    str = " ${readLine()}"
    graph = Array(n + 1) { hashSetOf() }

    repeat(n - 1) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from].add(to)
        graph[to].add(from)
    }
}

fun solve() {
    bfs()
    println(ans.joinToString(""))
}

fun bfs() {
    val que = LinkedList<Int>()
    que.add(1)
    que.add(1)
    ans.add(str[1])

    while (que.isNotEmpty()) {
        val from = que.poll()
        val depth = que.poll()
        // 다음 차례에 넣을 문자 -> from과 연결된 것 중 가장 큰 문자 / 만약 from과 연결된 것이 없다면 continue
        val nextChar = graph[from].maxOfOrNull { str[it] } ?: continue

        when {
            // case1: 현재 노드의 깊이가 만들어낸 문자열보다 얕은 경우
            ans.size <= depth -> ans.add(nextChar)

            // case2: 만들어낸 문자열의 마지막 글자가 다음에 넣을 문자보다 큰 경우
            ans.peekLast() > nextChar -> continue

            // case3: 만들어낸 문자열의 마지막 글자가 다음에 넣을 문자보다 작은 경우
            ans.peekLast() < nextChar -> {
                ans.pollLast()
                ans.add(nextChar)
            }
        }

        graph[from].forEach { to ->
            // visit 처리와 동일
            // 방향성을 끊어버림으로써 재방문 방지
            graph[to].remove(from)

            if (str[to] == nextChar) {
                que.add(to)
                que.add(depth + 1)
            }
        }
    }
}

fun main() {
    input()
    solve()
}