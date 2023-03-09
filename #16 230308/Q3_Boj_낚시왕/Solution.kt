/**
 * 상어를 나타낼 클래스
 * @row, col: 상어 위치
 * @speed: 상어 속도
 * @direction: 상어 이동 방향
 * @size: 상어 크기
 */
data class Shark(
    val row: Int,
    val col: Int,
    val speed: Int,
    val direction: Int,
    val size: Int,
) {
    // 현재 위치에서 본인이 갖는 속력과 방향에 맞게 이동한 결과를 반환한다.
    fun moved(): Shark {
        // 새로은 위치와 방향
        var nr = row
        var nc = col
        var nd = direction

        when (direction) {
            // 세로 방향으로 움직이는 경우
            1, 2 -> {
                // 실제로 이동할 거리를 계산한다.
                // 계산한 거리만큼 이동하며 끝점에 도착하면 방향을 바꾼다.
                val dist = speed % ((r - 1) * 2)
                repeat(dist) {
                    nr += dir[nd][0]
                    if (nr <= 0) {
                        nr = 2
                        nd = DOWN
                    }
                    if (nr > r) {
                        nr = r - 1
                        nd = UP
                    }
                }
            }
            // 가로 방향으로 움직이는 경우
            3, 4 -> {
                val dist = speed % ((c - 1) * 2)
                repeat(dist) {
                    nc += dir[nd][1]
                    if (nc <= 0) {
                        nc = 2
                        nd = RIGHT
                    }
                    if (nc > c) {
                        nc = c - 1
                        nd = LEFT
                    }
                }
            }
        }

        return Shark(nr, nc, speed, nd, size)
    }
}

const val UP = 1
const val DOWN = 2
const val RIGHT = 3
const val LEFT = 4

val dir = arrayOf(
    arrayOf(0, 0),
    arrayOf(-1, 0),
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1),
)

var r = 0
var c = 0
var m = 0
lateinit var board: Array<Array<Shark?>>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        r = it[0]
        c = it[1]
        m = it[2]
    }
    board = Array(r + 1) { Array(c + 1) { null } }
    repeat(m) {
        val (row, col, speed, dir, size) = readLine().split(" ").map { it.toInt() }
        board[row][col] = Shark(row, col, speed, dir, size)
    }
}

fun solve() {
    var ans = 0

    // 낚시꾼이 1 부터 c 까지 차례로 이동
    for (loc in 1..c) {
        ans += catch(loc)
        move()
    }

    println(ans)
}

// 낚시꾼이 col위치에서 상어를 잡는다.
fun catch(col: Int): Int {
    for (row in 1..r) {
        // 가장 위쪽의 상어부터 탐색한다.
        val target = board[row][col] ?: continue // null이면(존재x) continue한다
        return target.size.also {  // 존재하면 해당 상어의 크기를 반환하고
            board[row][col] = null // 상어를 제거한다.
        }
    }

    return 0
}

// 모든 상어를 이동시킨다.
fun move() {
    val sharks = hashSetOf<Shark>()
    // 존재하는 모든 상어를 set에 넣고 board에는 제거한다.
    // 특정 상어가 움직인 이후 겹치는 위치가 발생했을 때
    // 해당 위치에 이미 존재하는 상어가 이동하기 전인지, 후인지 판단하는 과정을 없애기 위해서 이다.
    for (row in 1..r) {
        for (col in 1..c) {
            board[row][col]?.let {
                sharks.add(it)
                board[row][col] = null
            }
        }
    }

    sharks.forEach {
        val shark = it.moved()
        val other = board[shark.row][shark.col]

        // 만약 움직인 위치에 다른 상어가 존재하면 크기 비교 후 갱신
        if (other == null || other.size < shark.size) {
            board[shark.row][shark.col] = shark
        }
    }
}

fun main() {
    input()
    solve()
}