

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var n = 0
lateinit var parentOf: HashMap<String, String>
lateinit var count: HashMap<String, Int>
lateinit var relationships: MutableList<Pair<String, String>>

fun input() = with(reader) {
    n = readLine().toInt()
    parentOf = hashMapOf()
    count = hashMapOf()
    relationships = mutableListOf()

    repeat(n) {
        val (a, b) = readLine().split(" ")
        relationships.add(a to b)

        parentOf[a] = a
        parentOf[b] = b
        count[a] = 1
        count[b] = 1
    }
}

fun solve() {
    relationships.forEach {
        val (a, b) = it
        union(a, b)

        builder.appendLine(getCount(b))
    }
}

fun union(a: String, b: String) {
    val A = find(a)
    val B = find(b)

    if (A != B) {
        parentOf[A] = B
        count[B] = count[A]!! + count[B]!!
    }
}

fun find(target: String): String {
    return if (target == parentOf[target]!!) target
    else {
        parentOf[target] = find(parentOf[target]!!)
        parentOf[target]!!
    }
}

fun getCount(target: String): Int {
    return if (target == parentOf[target]!!) count[target]!!
    else getCount(parentOf[target]!!)
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}