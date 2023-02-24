
var n = 0
var q = 0
lateinit var nums: LongArray // nums[i]: 생후 i일의 잔고 변화 값
lateinit var tree: LongArray // 세그먼트 트리

val commands = mutableListOf<String>()

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        q = it[1]
    }
    nums = LongArray(n + 1)
    tree = LongArray(4 * n + 1)

    repeat(q) { commands.add(readLine()) }
}

fun solve() {
    val sb = StringBuilder()

    commands.forEach {  command ->
        val (action, p, q) = command.split(" ").map { it.toInt() }

        if (action == 1) {
            update(1, n, 1, p, q.toLong())
        } else {
            sb.appendLine(sum(1, n, 1, p, q))
        }
    }

    print(sb)
}

/**
 * nums의 L~R 구간합을 구한다.
 * 이는 곧 L~R에 대응하는 tree 범위를 찾고 그 값을 반환하는 것과 같다.
 */
fun sum(left: Int, right: Int, target: Int, L: Int, R: Int): Long = when {
    left > R || right < L -> 0

    left >= L && right <= R -> tree[target]

    else -> {
        val mid = (left + right) / 2
        sum(left, mid, target * 2, L, R) + sum(mid + 1, right, target * 2 + 1, L, R)
    }
}

/**
 * nums[i]의 값을 num으로 변경하라.
 * 이는 곧 i를 포함하는 tree 범위를 찾고 그 값에 num을 더하는 것과 같다.
 */
fun update(left: Int, right: Int, target: Int, i: Int, num: Long): Unit = when {
    i !in left..right -> {}

    left == right -> tree[target] += num

    else -> {
        tree[target] += num

        val mid = (left + right) / 2
        update(left, mid, target * 2, i, num)
        update(mid + 1, right, target * 2 + 1, i, num)
    }
}

fun main() {
    input()
    solve()
}