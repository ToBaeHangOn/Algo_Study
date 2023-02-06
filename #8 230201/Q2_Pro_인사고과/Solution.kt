class Solution {
    fun solution(scores: Array<IntArray>): Int {
        val target = scores[0] // 첫번째 정보가 완호의 점수
        var maxEvaluation = 0  // 루프를 돌며 최대 동료 평가 점수를 갱신
        var rank = 1

        /**
         * 주어진 scores를 다음과 같이 정렬 후 순회한다.
         * 1. 근무 태도 점수의 내림차 순
         * 2. 동료 평가 점수의 오름차 순
         */
        scores.sortedWith(compareBy({ -it[0] }, { it[1] })).forEach { score ->
            if (score[1] >= maxEvaluation) {
                maxEvaluation = score[1]
            }
            // 최대 동료 평가 점수가 갱신 되지 않는 다면 해당 사람은 인센티브 자격이 없다.
            else {
                if (score.contentEquals(target)) return -1 // 그 사람이 완호라면 -1
                return@forEach // continue
            }

            // 현재 사람이 완호 보다 총합 점수가 높으면 완호의 순위는 하락한다.
            if (score.sum() > target.sum()) rank++
        }

        return rank
    }
}

/**
data class Person(
    val id: Int,
    val attitude: Int = 0,
    val evaluation: Int = 0,
) {
    val sum: Int
        get() = attitude + evaluation

    override fun equals(other: Any?): Boolean = id == (other as Person).id
}

class Solution {
    fun solution(scores: Array<IntArray>): Int {
        /**
         * mapIndexed -> scores를 Person 리스트로 변환
         * filter     -> 인텐티브를 받을 수 있는 Person 추출
         * sortedWith -> 점수합의 내림차순, 이후 id의 오름차순으로 정렬
         * indexOf    -> 이후 최종적으로 완호의 인덱스 추출
         * let        -> 인덱스가 -1 이면 완호가 filter에서 걸러짐 / 그렇지 않으면 인덱스 + 1이 곧 석차와 같음
         */
        return scores
            .mapIndexed { id, info -> Person(id, info[0], info[1]) }
            .filter { person -> scores.none { it[0] > person.attitude && it[1] > person.evaluation } }
            .sortedWith(compareBy({ -it.sum }, { it.id }))
            .indexOf(Person(id = 0))
            .let { if (it < 0) -1 else it + 1 }
    }
}
 */