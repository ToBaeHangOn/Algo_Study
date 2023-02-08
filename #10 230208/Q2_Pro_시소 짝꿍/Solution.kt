class Solution {
    // weight, count 쌍으로 저장할 HashMap
    val map = hashMapOf<Int, Long>()
    val rates = listOf(1 to 2, 2 to 3, 3 to 4)

    fun solution(weights: IntArray): Long {
        weights.map { it * 6 }.forEach { map[it] = (map[it] ?: 0) + 1 }

        var ans = 0L
        map.forEach { (weight, count) ->
            // 같은 몸무게인 사람들의 쌍의 개수 누적
            ans += count * (count - 1) / 2

            // 시소 짝꿍이 될 수 있는 무게를 탐색하고 쌍의 개수 누적
            rates.forEach {
                ans += count * (map[weight * it.second / it.first] ?: 0)
            }
        }

        return ans
    }
}