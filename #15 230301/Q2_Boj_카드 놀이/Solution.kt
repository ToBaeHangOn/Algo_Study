import kotlin.math.abs

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var n = 0
var m = 0
var k = 0
var min = 0
var max = 0
lateinit var aCards: IntArray
lateinit var bCards: IntArray
lateinit var A: HashSet<Int>
lateinit var B: HashSet<Int>

fun input() = with(reader) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
        k = it[2]
    }
    bCards = readLine().split(" ").map { it.toInt() }.toIntArray() // Bob이 보유한 카드
    aCards = readLine().split(" ").map { it.toInt() }.toIntArray() // Alice가 보유한 카드

    A = HashSet() // Alice가 보유한 카드들의 조합으로 만들 수 있는 합
    B = HashSet() // Bob이 보유한 카드들의 조합으로 만들 수 있는 합
}

fun solve() {
    dfs(0, aCards, A, 0, 0) // Alice가 보유한 카드를 k개 조합하여 만들 수 있는 합을 모두 저장
    dfs(0, bCards, B, 0, 0) // Bob이 보유한 카드를 k개 조합하여 만들 수 있는 합을 모두 저장

    // Alice와 Bob이 만들 수 있는 합을 정렬
    val a = A.sorted()
    val b = B.sorted()

    // 최댓값을 저장
    // 최댓값은 정렬한 것의 양 끝을 뺀 것들 중 하나
    max = maxOf(
        abs(a.first() - b.last()),
        abs(b.first() - a.last()),
    )
    min = Int.MAX_VALUE
    var pointer_a = 0
    var pointer_b = 0

    // 원하는 최솟값 탐색
    while (pointer_a < A.size && pointer_b < B.size) {
        val diff = abs(a[pointer_a] - b[pointer_b])

        when {
            diff < min -> min = diff

            a[pointer_a] <= b[pointer_b] -> pointer_a++

            else -> pointer_b++
        }
    }

    builder.append(min).append(' ').appendLine(max)
}

fun dfs(i: Int, cards: IntArray, set: HashSet<Int>, sum: Int, count: Int): Any = when {
    count > k -> {}

    count == k -> set.add(sum)

    i == cards.size -> {}

    else -> {
        dfs(i + 1, cards, set, sum, count)
        dfs(i + 1, cards, set, sum + cards[i], count + 1)
    }
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}