
const val MAX = 20

var l = 0
var w = 0
var h = 0
var n = 0
var ans = 0L
var flag = false
lateinit var count: LongArray
lateinit var pow: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    count = LongArray(MAX + 1)
    readLine().split(" ").map { it.toInt() }.let {
        l = it[0]
        w = it[1]
        h = it[2]
    }
    n = readLine().toInt()
    repeat(n) {
        val (i, cnt) = readLine().split(" ").map { it.toInt() }
        count[i] = cnt.toLong()
    }
    pow = IntArray(MAX + 1)
    pow[0] = 1
    for (i in 1..MAX) {
        pow[i] = pow[i - 1] * 2
    }
}

fun solve() {
    fill(l, w, h, MAX)
    println(if (flag) -1 else ans)
}

/**
 * fill()
 * length * width * height 크기의 직육면체를
 * 길이가 2^i 인 정육면체로 채워라.
 */
fun fill(
    length: Int,
    width: Int,
    height: Int,
    i: Int
): Unit = when {

    // 길이가 0인 변이 있으면 무시
    length == 0 || width == 0 || height == 0 -> {}

    // 크기가 1인 정육면체로 채울 경우 부피가 곧 소모 갯수
    i == 0 -> if (count[0] >= length.toLong() * width * height) {
        ans += length.toLong() * width * height
        count[0] -= length.toLong() * width * height
    } else flag = true

    // 크기가 2^i인 정육면체로 채울 공간이 없다면 더 작은 정육면체로 채우기
    minOf(length, width, height) < pow[i] -> fill(length, width, height, i - 1)

    // 정육면체가 없으면 더 작은 정육면체로 채우기
    count[i] <= 0 -> fill(length, width, height, i - 1)

    else -> {
        // 큐브 하나 사용
        ans++
        count[i]--

        // 하나의 큐브를 사용하고 남은 빈공간을 분리하여 채우기
        fill(length - pow[i], width, height, i)
        fill(pow[i], width - pow[i], height, i)
        fill(pow[i], pow[i], height - pow[i], i)
    }
}

fun main() {
    input()
    solve()
}