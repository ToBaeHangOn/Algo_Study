import java.util.LinkedList

var n = 0
var str = ""
val ans = StringBuilder()
lateinit var graph: Array<HashSet<Int>>
lateinit var visit: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    str = " ${readLine()}"
    graph = Array(n + 1) { hashSetOf() }
    visit = BooleanArray(n + 1)

    repeat(n - 1) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from].add(to)
        graph[to].add(from)
    }
}

fun solve() {
    bfs()
    println(ans)
}

fun bfs() {
    val que = LinkedList<Int>()
    que.add(1)
    ans.append(str[1])

    while (true) {
        var nextChar = 'a' // 다음에 추가할 문자 -> 가장 큰 문자
        val next = LinkedList<Int>() // 큐에 추가할 노드 리스트

        // 현재 큐에 들어있는 모든 노드(from)들을 한꺼번에 고려한다.
        // from 노드와 연결된 모든 노드(to)들을 탐색하며 가장 큰 문자를 찾는다.
        // to 노드가 가장 큰 문자를 갖는다면 next에 삽입한다.
        while (que.isNotEmpty()) {
            val from = que.poll()

            graph[from].forEach { to ->
                // 중복 방문 방지를 위해 방향성을 끊어버림
                graph[to].remove(from)
                when {
                    str[to] == nextChar -> next.add(to)

                    str[to] > nextChar -> {
                        nextChar = str[to]
                        next.clear()
                        next.add(to)
                    }
                }
            }
        }

        // 큐에 삽입할 노드가 없다면 종료
        if (next.isEmpty()) break

        ans.append(nextChar)
        que.addAll(next)
    }
}

fun main() {
    input()
    solve()
}

/*
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
}*/
