
var num = 0L

fun input() = with(System.`in`.bufferedReader()) {
    num = readLine().toLong()
}

fun solve() {
    val basePosition = getBasePosition()
    val temp = (basePosition * basePosition - basePosition) / 2L + 1

    val numerator = if (basePosition % 2 == 0L) 1 + (num - temp) else basePosition - (num - temp)
    val denominator = if (basePosition % 2 == 0L) basePosition - (num - temp) else 1 + (num - temp)

    println("$numerator/$denominator")
}

fun getBasePosition(): Long {
    var left = 0L
    var right = Int.MAX_VALUE.toLong()
    var target = 0L

    while (left <= right) {
        val mid = (left + right) / 2L
        val result = (mid * mid - mid) / 2L + 1

        when {
            result == num -> {
                target = mid
                break
            }
            result < num -> {
                left = mid + 1
                target = mid
            }
            result > num -> right = mid - 1
        }
    }

    return target
}

fun main() {
    input()
    solve()
}