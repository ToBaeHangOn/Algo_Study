import kotlin.math.abs
import kotlin.math.min

class Solution {

    lateinit var nums: List<Int>
    lateinit var dp: Array<Array<IntArray>>
    var n = 0
    var ans = Int.MAX_VALUE

    fun solution(numbers: String): Int {
        n = numbers.length
        nums = "0$numbers".map { it.digitToInt() }
        dp = Array(n + 1) { Array(10) { IntArray(10) { Int.MAX_VALUE } } }
        /**
         * dp[i][left][right] -> i번째 수를 누를 때의 점수를 의미하며 왼손과 오른손이 각각 left, right에 있음.
         */

        nums.forEachIndexed { i, num ->
            if (i == 0) {
                // 시작할 때 왼손과 오른손은 각각 4와 6에 있으며 점수는 0이다.
                dp[0][4][6] = 0
                return@forEachIndexed
            }
            // left, right -> 이전 수(i - 1번째 수)를 누를 때 손의 위치라 가정하며 순회
            for (left in 0 until 10) {
                for (right in 0 until 10) {
                    // 조건 1 : 왼손과 오른손의 위치가 같으면 가치가 없다.
                    if (left == right) continue

                    // 조건 2 : left와 right의 위치에서 i - 1번째 수를 누르는 경우가 없으면 가치가 없다.
                    if (dp[i - 1][left][right] == Int.MAX_VALUE) continue

                    // 왼손으로 i번째 수를 누른다.
                    dp[i][num][right] = min(
                        dp[i][num][right],
                        dp[i - 1][left][right] + left.move(num)
                    )
                    // 오른손으로 i번째 수를 누른다.
                    dp[i][left][num] = min(
                        dp[i][left][num],
                        dp[i - 1][left][right] + right.move(num)
                    )
                }
            }
        }

        return dp.last().minOf { it.minOf { it } }
    }
}

/**
 * 이하 가중치를 구하기 위한 코드
 */
data class Point(
    val row: Int,
    val col: Int,
)

// 클래스 Int의 확장 함수로 정의해 사용하기 쉽도록 함
fun Int.toPoint() = Point(
    row = if (this == 0) 3 else (this - 1) / 3,
    col = if (this == 0) 1 else (this - 1) % 3
)

fun Int.move(other: Int): Int {
    val from = this.toPoint()
    val to = other.toPoint()

    return when {
        from == to -> 1

        from.row == to.row -> 2 * abs(from.col - to.col)

        from.col == to.col -> 2 * abs(from.row - to.row)

        abs(from.row - to.row) == abs(from.col - to.col) -> 3 * abs(from.row - to.row)

        abs(from.row - to.row) < abs(from.col - to.col) -> 5

        else -> 3 + 2 * (abs(from.row - to.row) - 1)
    }
}