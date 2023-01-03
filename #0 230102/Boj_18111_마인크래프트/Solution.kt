
var n = 0
var m = 0
var b = 0
var ans = Int.MAX_VALUE to Int.MIN_VALUE
val blocks = hashMapOf<Int, Int>()

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        m = this[1]
        b = this[2]
    }
    repeat(n) {
        readLine().split(" ").map { it.toInt() }.forEach {
            blocks[it] = (blocks[it] ?: 0) + 1
        }
    }
}

fun solve() {
    val maxHeight = blocks.keys.maxOrNull()!!
    val minHeight = blocks.keys.minOrNull()!!

    for (height in maxHeight downTo minHeight) {
        flatten(height)?.let {
            ans = it to height
        }
    }

    println("${ans.first} ${ans.second}")
}

fun flatten(target: Int): Int? {
    var time = 0
    var remain = b

    blocks.forEach { (height, count) ->
        when {
            target < height -> with(height - target) {
                remain += this * count
                time += this * count * 2
            }
            target > height -> with(target - height) {
                remain -= this * count
                time += this * count
            }
        }
        if (time >= ans.first) return null
    }
    return if (remain < 0) null else time
}

fun main() {
    input()
    solve()
}