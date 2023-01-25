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