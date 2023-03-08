import java.util.*

var n = 0
var r = 0      // 아기 상어의 세로 위치
var c = 0      // 아기 사어의 가로 위치
var size = 2   // 아기 상어의 크기
var ans = 0    // 지난 시간 (정답)
var remain = 0 // 남아있는 상어 수
var count = 0  // 아기 상어가 현재 크기에서 먹은 물고기 수
val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)
lateinit var board: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { row ->
        readLine().split(" ").mapIndexed { col, s ->
            val value = s.toInt()
            if (value == 9) {
                r = row
                c = col
                0
            }
            else {
                if (value > 0) remain++
                value
            }
        }.toIntArray()
    }
}

fun solve() {
    while (find()) {}
    println(ans)
}

fun find(): Boolean {
    // 만약 남아있는 물고기가 없으면 종료
    if (remain == 0) return false

    val visit = Array(n) { BooleanArray(n) }
    val que = LinkedList<Int>().apply {
        add(r)
        add(c)
        add(0)
        visit[r][c] = true
    }
    var min = Int.MAX_VALUE // 먹을 수 있는 물고기와의 최소 거리
    val cands = arrayListOf<Pair<Int, Int>>() // 먹을 수 있는 물고기 위치 목록

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val dist = que.poll()

        if (dist > min) continue  // 이미 찾은 최소 거리를 초과하면 탐색할 가치가 없다.
        if (isValid(row, col)) {  // 현재 위치에 먹을 수 있는 물고기가 존재하면
            min = dist            // 최소 거리 갱신하고
            cands.add(row to col) // 목록에 등록
            continue
        }

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (visit[nr][nc] || board[nr][nc] > size) return@forEach

            que.apply {
                add(nr)
                add(nc)
                add(dist + 1)
                visit[nr][nc] = true
            }
        }
    }
    // 만약 먹을 수 있는 물고기가 없다면 false, 있다면 true를 리턴할 것이다.
    // 단, true를 리턴하기 전 필요 데이터를 갱신한다.
    return if (cands.isEmpty()) false else {
        // 가장 우선순위가 높은 물고기를 찾음
        val target = cands.sortedWith(compareBy( { it.first }, { it.second }))[0]
        board[target.first][target.second] = 0 // 해당 위치에 물고기를 제거
        r = target.first  // 아기 상어위 위치 변경
        c = target.second
        ans += min // 시간 갱신
        remain--   // 잔여 물고기 감소
        if (++count == size) { // 만약 몸집이 커질 조건 확인
            count = 0
            size++
        }
        true
    }
}

// 해당 위치에 물고기가 존재하며 먹을 수 있는가?
fun isValid(row: Int, col: Int) = board[row][col] in 1..6 && board[row][col] < size

fun main() {
    input()
    solve()
}