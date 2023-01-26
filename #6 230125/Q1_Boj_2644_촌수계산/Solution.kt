var n = 0
var m = 0
var x = 0
var y = 0
lateinit var parentOf: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    readLine().split(" ").map { it.toInt() }.let {
        x = it[0]
        y = it[1]
    }
    parentOf = IntArray(n + 1)

    m = readLine().toInt()
    repeat(m) {
        val (parent, child) = readLine().split(" ").map { it.toInt() }
        parentOf[child] = parent
    }
}

fun solve() {
    val ancestor_of_x = getAncestor(x)
    val ancestor_of_y = getAncestor(y)

    println(when {
        // case 1 : y가 x의 직계 조상인 경우
        ancestor_of_x.contains(y) -> ancestor_of_x.indexOf(y) + 1

        // case 2 : x가 y의 직계 조상인 경우
        ancestor_of_y.contains(x) -> ancestor_of_y.indexOf(x) + 1

        // case 3 : x와 y가 서로 무관한 경우
        ancestor_of_x.none { ancestor_of_y.contains(it) } -> -1

        // case 4 : x와 y가 공통 조상을 갖는 경우
        else -> {
            val common = ancestor_of_x.find { ancestor_of_y.contains(it) }!!
            ancestor_of_x.indexOf(common) + ancestor_of_y.indexOf(common) + 2
        }
    })
}

/**
 * id의 모든 조상을 추출함
 */
fun getAncestor(id: Int): List<Int> {
    val ancestor = mutableListOf<Int>()
    var target = parentOf[id]

    while (target != 0) {
        ancestor.add(target)
        target = parentOf[target]
    }

    return ancestor
}

fun main() {
    input()
    solve()
}