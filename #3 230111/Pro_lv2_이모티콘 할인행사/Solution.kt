import kotlin.math.max

data class Emoticon(
    var rate: Int = 10,
    val price: Int
) {
    // 할인이 적용된 실가격
    val actualPrice: Int
        get() = (price * (1 - rate / 100.0)).toInt()
}

data class User(
    val minRate: Int,
    val maxPrice: Int
) {
    /**
     * @return Pair<Int, Int>
     * 페어의 첫번째 항 -> 가입 여부 (0 or 1)
     * 페어의 두번째 항 -> 지불 금액
     *
     * priceSum : 기준 할인율 이상인 이모티콘들의 실가격 합
     * case 1 -> priceSum이 기준 가격 이상이면 Pair(1, 0)
     * case 2 -> priceSum이 기준 가격 미만이면 Pair(0, priceSum)
     */
    fun choose(emoticons: List<Emoticon>): Pair<Int, Int> {
        val priceSum = emoticons.filter { it.rate >= minRate }.sumOf { it.actualPrice }
        return if (priceSum >= maxPrice) 1 to 0 else 0 to priceSum
    }
}

class Solution {
    var nEmoticon = 0
    var answer = intArrayOf(0, 0)
    lateinit var users: List<User>
    lateinit var emoticons: List<Emoticon>

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        this.nEmoticon = emoticons.size

        // 주어진 정보를 객체 리스트로 변환
        this.users = List(users.size) { User(users[it][0], users[it][1]) }
        this.emoticons = List(nEmoticon) { Emoticon(price = emoticons[it])}

        dfs(0)

        return answer
    }

    // k번째 이모티콘의 할인율을 조정한다.
    fun dfs(k: Int) {
        // 모든 이모티콘을 고려했다면 정답 갱신
        if (k == nEmoticon) {
            var nJoin = 0
            var pay = 0

            users.forEach {
                val result = it.choose(emoticons)

                nJoin += result.first
                pay += result.second
            }

            updateAnswer(nJoin, pay)
            return
        }

        // 현재 보고 있는 이모티콘의 할인율을 모두 적용해보며 탐색한다.
        listOf(10, 20, 30, 40).forEach { rate ->
            emoticons[k].rate = rate
            dfs(k + 1)
        }
    }

    fun updateAnswer(nJoin: Int, pay: Int) = when {
        answer[0] < nJoin -> answer = intArrayOf(nJoin, pay)

        answer[0] == nJoin -> answer[1] = max(pay, answer[1])

        else -> {}
    }
}