
var n = 0
var k = 0

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        k = this[1]
    }
}

fun solve() {
    if (n <= k) {
        println(0)
        return
    }

    var ans = 0
    while (n.countOneBits() > k) {
        ans++
        n++
    }

    println(ans)
}



fun main() {
    input()
    solve()
}