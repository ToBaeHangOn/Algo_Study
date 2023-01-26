import java.util.LinkedList

data class Point(
    val row: Int,
    val col: Int,
)

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

var n = 0
var k = 0
var head = Point(1, 1)
var dir = Direction.RIGHT
var isOver = false
lateinit var body: LinkedList<Point>
lateinit var apples: HashSet<Point>
lateinit var commands: LinkedList<Pair<Int, String>>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    k = readLine().toInt()
    apples = hashSetOf()
    body = LinkedList<Point>()

    repeat(k) {
        val (row, col) = readLine().split(" ").map { it.toInt() }
        apples.add(Point(row, col))
    }

    commands = LinkedList()
    repeat(readLine().toInt()) {
        val (time, dir) = readLine().split(" ")
        commands.add(time.toInt() to dir)
    }
}

fun solve() {
    var time = 0
    while (true) {
        if (isOver) break

        move()     // 움직이고
        eatApple() // 사과 먹고
        time++     // 시간 증가하고

        // 방향 바꿀 시간인지 확인
        commands.peek()?.let {
            if (it.first == time) turn(commands.poll().second)
        }
    }

    println(time)
}

fun move() = with(getDir()){
    val nr = head.row + this[0]
    val nc = head.col + this[1]
    val next = Point(nr, nc) // 움직인 후 뱀의 머리 위치

    // 머리가 머리나 몸통에 부딪히는지 확인
    isOver = nr !in 1 .. n || nc !in 1 .. n || body.contains(next)

    // 지금의 머리 위치를 몸통에 넣어주고 다음 머리 위치로 갱신
    body.addFirst(head)
    head = next
}

fun getDir(): IntArray = when (dir) {
    Direction.UP -> intArrayOf(-1, 0)

    Direction.DOWN -> intArrayOf(1, 0)

    Direction.LEFT -> intArrayOf(0, -1)

    else -> intArrayOf(0, 1)
}

fun eatApple() {
    // 머리가 있는 위치에 사과가 있다면 해당 사과를 없애고
    // 사과가 없다면 몸통의 마지막(꼬리)를 삭제함
    if (!apples.contains(head)) {
        body.pollLast()
    } else {
        apples.remove(head)
    }
}

fun turn(to: String) {
    dir = when (dir) {
        Direction.UP -> if (to == "D") Direction.RIGHT else Direction.LEFT

        Direction.DOWN -> if (to == "D") Direction.LEFT else Direction.RIGHT

        Direction.LEFT -> if (to == "D") Direction.UP else Direction.DOWN

        else -> if (to == "D") Direction.DOWN else Direction.UP
    }
}

fun main() {
    input()
    solve()
}