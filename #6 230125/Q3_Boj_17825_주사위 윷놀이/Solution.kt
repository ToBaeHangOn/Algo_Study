import kotlin.math.max

/**
 * Node : 게임판의 각 칸을 의미한다.
 * next : 현재 칸에서 이동할 때의 다음 칸
 * special : 10, 20, 30 칸에서 출발할 경우의 다음 칸
 */
data class Node(
    val score: Int,
    var next: Node? = null,
    var special: Node? = null
) {
    // 현재 칸에서 dist 만큼 이동한 결과를 반환
    fun move(dist: Int): Node? {
        // Node target = special != null ? special : next
        var target = special ?: next // 출발칸이 지름길을 갖는지 확인
        var count = 1

        while (count < dist) {
            target ?: break
            target = target.next

            count++
        }

        return target
    }
}

var ans = 0
val start = Node(0) // 시작 지점
val pieces: Array<Node?> = (0 until 4).map { start }.toTypedArray() // 모든 말을 시작 지점으로 둔다.
lateinit var order: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    order = readLine().split(" ").map { it.toInt() }

    val node25 = initNode25() // 25번 노드를 미리 저장 해둠
    var node = start

    // 크게 돌아가는 경우를 연결해 감
    for (i in 2 .. 40 step 2) {
        val next = Node(i)
        node.next = next
        node = next

        // 지름길을 갖는 노드인 경우 따로 special 루트를 지정해둔다.
        when (i) {
            10 -> initNode10(node, node25)

            20 -> initNode20(node, node25)

            30 -> initNode30(node, node25)
        }
    }
}

fun initNode25(): Node {
    val node40 = Node(40, null) // 40 칸의 다음은 도착 지점
    val node35 = Node(35, node40)
    val node30 = Node(30, node35)

    return Node(25, node30)
}

fun initNode10(node10: Node, node25: Node) {
    val node19 = Node(19, node25)
    val node16 = Node(16, node19)
    val node13 = Node(13, node16)

    node10.special = node13
}

fun initNode20(node20: Node, node25: Node) {
    val node24 = Node(24, node25)
    val node22 = Node(22, node24)

    node20.special = node22
}

fun initNode30(node30: Node, node25: Node) {
    val node26 = Node(26, node25)
    val node27 = Node(27, node26)
    val node28 = Node(28, node27)

    node30.special = node28
}

fun solve() {
    dfs(0, 0)
    println(ans)
}

fun dfs(k: Int, sum: Int) {
    if (k == 10) {
        ans = max(sum, ans)
        return
    }

    for (i in 0 until 4) {
        val target = pieces[i] ?: continue // if (pieces[i] == null) continue
        val moveResult = target.move(order[k]) // 이동한 결과 null이 반환되면 해당 말은 도착 지점에 온 것

        if (moveResult != null && pieces.contains(moveResult)) continue // 도착 칸을 제외하고 한 칸에 여러 말이 올 수 없다.

        pieces[i] = moveResult
        // dfs(k + 1, sum + (moveResult != null ? moveResult.score : 0))
        dfs(k + 1, sum + (moveResult?.score ?: 0))
        pieces[i] = target
    }
}

fun main() {
    input()
    solve()
}

/**
import kotlin.math.max

var ans = 0
lateinit var moves: List<Int>
lateinit var pieces: IntArray
lateinit var next: IntArray
lateinit var special: IntArray
lateinit var flags: BooleanArray
lateinit var finished: BooleanArray
val specialPoints = hashSetOf(10, 20, 30)

fun input() = with(System.`in`.bufferedReader()) {
    moves = readLine().split(" ").map { it.toInt() }
    pieces = IntArray(4)
    flags = BooleanArray(4)
    finished = BooleanArray(4)

    next = IntArray(41) { it + 2 }
    special = IntArray(41)

    special[10] = 13
    special[13] = 16
    special[16] = 19
    special[19] = 25

    special[20] = 22
    special[22] = 24
    special[24] = 25

    special[30] = 28
    special[28] = 27
    special[27] = 26
    special[26] = 25

    special[25] = 30
    special[30] = 35
    special[35] = 40
}

fun solve() {
    dfs(0, 0)
    println(ans)
}

fun dfs(k: Int, score: Int) {
    println(pieces.contentToString())
    if (k == 10) {
        ans = max(ans, score)
        return
    }

    for (i in pieces.indices) {
        if (finished[i]) continue

        val now = pieces[i]
        if (!flags[i]) flags[i] = specialPoints.contains(pieces[i])

        move(i, moves[k])
        dfs(k + 1, score + pieces[i])

        pieces[i] = now
        if (specialPoints.contains(pieces[i])) flags[i] = false
        if (finished[i]) finished[i] = false
    }
}

fun move(idx: Int, dist: Int) {
    if (dist == 0) return
    if (pieces[idx] == 40) {
        finished[idx] = true
        return
    }

    if (flags[idx]) {
        pieces[idx] = special[pieces[idx]]
    } else {
        pieces[idx] = next[pieces[idx]]
    }

    move(idx, dist - 1)
}

fun main() {
    input()
    solve()
}
**/