import kotlin.math.max

class Solution {
    var ans = 0
    lateinit var dungeons: Array<IntArray>
    lateinit var visit: BooleanArray

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        this.dungeons = dungeons
        this.visit = BooleanArray(dungeons.size)

        dfs(0, k)

        return ans
    }

    fun dfs(count: Int, remain: Int) {
        ans = max(ans, count)

        for (i in dungeons.indices) {
            val (limit, use) = dungeons[i]
            if (visit[i] || remain < limit) continue

            visit[i] = true
            dfs(count + 1, remain - use)
            visit[i] = false
        }
    }
}