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