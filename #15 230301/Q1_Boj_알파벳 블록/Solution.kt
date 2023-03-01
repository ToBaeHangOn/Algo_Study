import java.util.LinkedList

const val TAIL = 0
const val FRONT = 1

var n = 0
lateinit var commands: List<String>
val que = LinkedList<Char>()  // 문자를 삽입할 큐
val stack = LinkedList<Int>() // 문자를 삽입할 시 어디에 삽입했는지를 기록할 스택

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    commands = List(n) { readLine() }
}

fun solve() {
    commands.forEach {
        when (it[0]) {
            '1' -> addTail(it[2])

            '2' -> addFront(it[2])

            '3' -> remove()
        }
    }

    println(
        que.joinToString("").ifEmpty { 0 }
    )
}

fun addTail(c: Char) {
    que.addLast(c)
    stack.add(TAIL)
}

fun addFront(c: Char) {
    que.addFirst(c)
    stack.add(FRONT)
}

fun remove() {
    if (stack.isEmpty()) return

    when (stack.pollLast()) {
        FRONT -> que.pollFirst()

        TAIL -> que.pollLast()
    }
}

fun main() {
    input()
    solve()
}