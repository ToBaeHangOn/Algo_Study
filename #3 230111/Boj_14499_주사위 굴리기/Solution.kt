/**
 * 주사위를 표현할 클래스
 * 위치값과 각 면의 값을 갖는다.
 * roll() 함수로 굴리는 방향에 따라 각 면의 값을 업데이트한다.
 */
data class Dice(
    var row: Int,
    var col: Int
) {
    var up: Int = 0
    var down: Int = 0
    var front: Int = 0
    var back: Int = 0
    var right: Int = 0
    var left: Int = 0

    fun roll(dir: Int) {
        val temp = up
        when (dir) {
            1 -> {
                up = left
                left = down
                down = right
                right = temp
            }
            2 -> {
                up = right
                right = down
                down = left
                left = temp
            }
            3 -> {
                up = front
                front = down
                down = back
                back = temp
            }
            else -> {
                up = back
                back = down
                down = front
                front = temp
            }
        }
    }
}

var n = 0
var m = 0
var r = 0
var c = 0
var k = 0
lateinit var dice: Dice
lateinit var board: Array<IntArray>
lateinit var commands: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        m = this[1]
        r = this[2]
        c = this[3]
        k = this[4]
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    dice = Dice(r, c)
    commands = readLine().split(" ").map { it.toInt() }
}

fun solve() {
    val ans = StringBuilder()

    // 방향값이 1 ~ 4 범위로 주어지므로 편하게 size가 5인 배열로 선언함
    val dr = intArrayOf(0, 0, 0, -1, 1)
    val dc = intArrayOf(0, 1, -1, 0, 0)

    commands.forEach { dir ->
        val nr = dice.row + dr[dir]
        val nc = dice.col + dc[dir]

        if (nr !in 0 until n || nc !in 0 until m) return@forEach // java의 continue와 같음

        // 주사위가 지도를 벗어나지 않으면 위치를 갱신하고 해당 방향으로 굴려줌
        dice.apply {
            row = nr
            col = nc
            roll(dir)
        }
        if (board[nr][nc] == 0) board[nr][nc] = dice.down
        else {
            dice.down = board[nr][nc]
            board[nr][nc] = 0
        }

        ans.appendLine(dice.up)
    }

    print(ans)
}

fun main() {
    input()
    solve()
}