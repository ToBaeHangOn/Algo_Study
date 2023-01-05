import java.util.*

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var k = 0
lateinit var nums: List<Int>

fun input(line: String) = with(line.split(" ").map { it.toInt() }){
    k = this[0]
    nums = this.subList(1, this.size)
}

fun solve() {
    rec_func(0, LinkedList())
}

fun rec_func(i: Int, selected: LinkedList<Int>) {
    if (i == k) {
        if (selected.size != 6) return
        selected.forEach {
            builder.append(it).append(' ')
        }
        builder.appendLine()
        return
    }

    selected.add(nums[i])
    rec_func(i + 1, selected)
    selected.pollLast()
    rec_func(i + 1, selected)
}

fun main() = with(reader) {
    while (true) {
        val line = readLine()
        if (line[0] == '0') break

        input(line)
        solve()
        builder.appendLine()
    }
    println(builder)
}