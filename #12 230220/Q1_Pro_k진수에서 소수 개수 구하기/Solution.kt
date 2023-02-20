import kotlin.math.sqrt

class Solution {
    val checked = hashMapOf<Long, Boolean>()

    fun solution(n: Int, k: Int): Int {
        return n.toString(k) // n을 k진법으로 변환한 것을
            .split("0")      // 0을 기준으로 토큰 분리하고
            .count { it.isNotEmpty() && isPrime(it.toLong()) } // 분리한 토큰이 소수인 것들의 개수
    }

    fun isPrime(num: Long): Boolean = when {
        num == 1L -> false

        checked[num] != null -> checked[num]!!

        else -> (2..sqrt(num.toDouble()).toInt()).none {
            num % it == 0L
        }.also { checked[num] = it }
    }
}