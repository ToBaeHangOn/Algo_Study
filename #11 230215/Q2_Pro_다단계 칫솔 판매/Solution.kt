class Solution {
    var n = 0
    lateinit var people: Array<String>
    lateinit var parentOf: HashMap<String, String>
    lateinit var result: HashMap<String, Int>

    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        n = enroll.size
        people = enroll
        parentOf = hashMapOf()
        result = hashMapOf()

        // 나를 참여시킨 추천인을 기록해둠
        // 만약 없으면 null 값이 저장됨
        referral.forEachIndexed { i, parent ->
            if (parent == "-") return@forEachIndexed

            val child = people[i]
            parentOf[child] = parent
        }

        // 판매원의 수익을 바탕으로 분배 시작
        seller.forEachIndexed { i, name ->
            val sales = amount[i] * 100 // 판매액은 판매량 * 100 원
            share(name, sales)          // 분배 시작
        }

        return people.map { result[it] ?: 0 }.toIntArray()
    }

    fun share(child: String?, sales: Int) {
        child ?: return // 현재 사람이 null이면 종료

        val sharing = sales / 10 // 분배할 금액
        if (sharing == 0) {
            // 분배할 금액이 1원 미만이면 전부 내꺼
            result[child] = (result[child] ?: 0) + sales
        } else {
            // 그렇지 않으면 분배 금액을 뺀 만큼이 내꺼
            result[child] = (result[child] ?: 0) + sales - sharing
            share(parentOf[child], sharing) // 나머지 공유(재귀 호출)
        }
    }
}