class Solution {
    val map = hashMapOf<String, Int>()
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        orders.forEach { order ->
            dfs(0, order.toCharArray().sorted().joinToString(""), "")
        }

        val ans = mutableListOf<String>()
        course.forEach { size ->
            // size 만큼의 메뉴로 구성된 코스를 필터링
            with (map.keys.filter { it.length == size }) {
                ans.addAll(this.filter {
                    // 필터링한 코스메뉴 중 2명 이상이 가장 많이 주문한 것 추출
                    map[it]!! > 1 && map[it]!! == this.maxOf { map[it]!! }
                })
            }
        }
        return ans.sorted().toTypedArray()
    }

    fun dfs(k: Int, order: String, course: String) {
        if (k == order.length) {
            map[course] = (map[course] ?: 0) + 1
            return
        }

        dfs(k + 1, order, buildString { append(course).append(order[k]) })
        dfs(k + 1, order, course)
    }
}

fun main() {
    println(
        Solution().solution(
            orders = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
            course = intArrayOf(2,3,4)
        ).contentToString()
    )
}