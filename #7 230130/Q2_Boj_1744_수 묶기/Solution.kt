import java.util.LinkedList

var n = 0
var ans = 0
val minus = LinkedList<Int>() // 0과 음수를 저장할 큐
val plus = LinkedList<Int>()  // 1을 제외한 양수를 저장할 큐

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    repeat(n) {
        val num = readLine().toInt()
        when {
            num <= 0 -> minus.add(num)

            num > 1 -> plus.add(num)

            else -> ans++ // 1은 무조건 짝을 짓지 않는 것이 유리하므로 바로 ans에 누적시킴
        }
    }

    // 이하 숫자들 정렬
    minus.sort()
    plus.sort()
}

fun solve() {
    // 음수는 작은 수들 끼리 최대한 짝을 지어준다
    while (minus.size > 1) {
        ans += (minus.poll() * minus.poll())
    }
    if (minus.isNotEmpty()) ans += minus.poll()

    // 양수는 큰 수 끼리 최대한 짝을 지어준다
    while (plus.size > 1) {
        ans += (plus.pollLast() * plus.pollLast())
    }
    if (plus.isNotEmpty()) ans += plus.poll()

    println(ans)
}

fun main() {
    input()
    solve()
}