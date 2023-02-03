import java.util.LinkedList

class Solution {
    var n = 0
    var m = 0
    lateinit var board: Array<IntArray>
    lateinit var visit: Array<BooleanArray>

    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )

    fun solution(maps: Array<String>): IntArray {
        n = maps.size
        m = maps[0].length

        visit = Array(n) { BooleanArray(m) }
        board = Array(n) { i ->
            IntArray(m) { j ->
                // 문자가 바다('X')이면 0으로 치환하며 그 외의 숫자는 Int형으로 변환한다
                if (maps[i][j].isDigit()) maps[i][j].digitToInt() else 0
            }
        }

        val ans = mutableListOf<Int>()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (!visit[i][j] && board[i][j] != 0) {
                    ans.add(bfs(i, j))
                }
            }
        }

        return if (ans.isEmpty()) intArrayOf(-1) else ans.sorted().toIntArray()
    }

    fun bfs(sr: Int, sc: Int): Int {
        val que = LinkedList<Int>().apply {
            add(sr)
            add(sc)
            visit[sr][sc] = true
        }

        var sum = board[sr][sc]
        while (que.isNotEmpty()) {
            val row = que.poll()
            val col = que.poll()

            dir.forEach {
                val nr = row + it[0]
                val nc = col + it[1]

                if (nr !in 0 until n || nc !in 0 until m) return@forEach
                if (visit[nr][nc] || board[nr][nc] == 0) return@forEach

                que.apply {
                    add(nr)
                    add(nc)
                    visit[nr][nc] = true

                    sum += board[nr][nc]
                }
            }
        }

        return sum
    }
}