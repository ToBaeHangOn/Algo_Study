
var n = 0
var start = 0 // 시작 지점
var left = 0  // 시작 지점 기준 왼쪽 포인터
var right = 0 // 시작 지점 기준 오른쪽 포인터
var ans = 0L  // 최종 정답 (최대 점수)
lateinit var scores: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        start = it[1] - 1
        left = start - 1
        right = start + 1
    }
    scores = readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun solve() {
    while (true) {
        val pre = ans // 포인터를 움직이기전 현재 정답 저장

        // 왼쪽과 오른쪽을 탐색함
        moveToLeftSide()
        moveToRightSide()

        // 탐색을 했는데 정답이 갱신되지 않으면 break
        if (pre == ans) break
    }

    println(ans)
}

// 왼쪽 방향으로 탐색한다.
fun moveToLeftSide() {
    var max = 0L      // 왼쪽으로 탐색하며 얻을 수 있는 최대 점수
    var maxLoc = left // 최대 점수를 얻을 때의 포인터 위치
    var sum = 0L      // 이동하면서 누적되는 점수 합

    while (left >= 0) {
        sum += scores[left]
        if (ans + sum < 0) break // 기저 조건

        // 얻을 수 있는 최대 점수가 갱신되면
        if (max < sum) {
            max = sum         // 최대 점수 갱신
            maxLoc = left - 1 // 현재 포인터 위치 저장
        }
        left--
    }

    // 다음부터 왼쪽을 탐색할 때는 최대 점수를 얻은 위치의 '다음 지점' 부터 시작한다.
    // 따라서 maxLoc를 갱신할 때 -1을 해주는 것.
    left = maxLoc
    ans += max
}

// 오른쪽 방향으로 탐색한다.
// 각 라인의 의미는 왼쪽 방향 탐색과 동일
fun moveToRightSide() {
    var max = 0L
    var maxLoc = right
    var sum = 0L

    while (right < n) {
        sum += scores[right]
        if (ans + sum < 0) break

        if (max < sum) {
            max = sum
            maxLoc = right + 1
        }
        right++
    }

    right = maxLoc
    ans += max
}

fun main() {
    input()
    solve()
}

/*
import kotlin.math.max

var n = 0
var start = 0
lateinit var scores: IntArray
lateinit var dp: LongArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        start = it[1] - 1
    }
    scores = readLine().split(" ").map { it.toInt() }.toIntArray()
    dp = LongArray(n)
}

fun solve() {
    for (i in start - 1 downTo 0) {
        dp[i] = maxOf(
            dp[i + 1] + scores[i + 1],
            dp[i + 2]  + scores[i + 2],
            dp[i]
        )
    }
}

fun main() {
    input()
    solve()
}*/
