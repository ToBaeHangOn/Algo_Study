import java.util.LinkedList

data class Point(
    val row: Int,
    val col: Int
)
data class Puzzle(
    val size: Int,
    var arr: Array<IntArray>
) {
    val row: Int get() = arr.size
    val col: Int get() = arr[0].size

    fun rotate() {
        val temp = Array(col) { IntArray(row) }

        for (i in 0 until row) {
            for (j in 0 until col) {
                temp[j][row - i - 1] = arr[i][j]
            }
        }

        arr = temp
    }

    fun isMatch(other: Puzzle): Boolean {
        if (size != other.size) return false
        if (row != other.row && row != other.col) return false

        for (count in 0 until 4) {
            if (row == other.row) {
                var flag = false
                for (i in 0 until row) {
                    val check = arr[i].contentEquals(other.arr[i])
                    if (!check) {
                        flag = true
                        break
                    }
                }
                if (!flag) return true
            }
            rotate()
        }

        return false
    }
}

class Solution {
    var r = 0
    var c = 0
    val puzzles = hashSetOf<Puzzle>()
    val emptyPuzzles = hashSetOf<Puzzle>()
    lateinit var visit: Array<BooleanArray>

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        r = game_board.size
        c = game_board[0].size

        extractPuzzle(true, game_board)
        extractPuzzle(false, table)

        var ans = 0
        puzzles.forEach { puzzle ->
            val target = emptyPuzzles.find { puzzle.isMatch(it) }
            target?.let {
                ans += it.size
                emptyPuzzles.remove(it)
            }
        }

        return ans
    }

    fun extractPuzzle(isEmptyPuzzle: Boolean, board: Array<IntArray>) {
        visit = Array(r) { BooleanArray(c) }
        for (row in 0 until r) {
            for (col in 0 until c) {
                val target = if (isEmptyPuzzle) 0 else 1
                if (visit[row][col] || board[row][col] != target) continue

                val points = bfs(row, col, board, target)
                val puzzleSet = if (isEmptyPuzzle) emptyPuzzles else puzzles

                puzzleSet.add(
                    Puzzle(
                        size = points.size,
                        arr = normalize(points)
                    )
                )
            }
        }
    }

    fun bfs(sr: Int, sc: Int, board: Array<IntArray>, target: Int): HashSet<Point> {
        val dir = arrayOf(
            arrayOf(1, 0),
            arrayOf(-1, 0),
            arrayOf(0, 1),
            arrayOf(0, -1)
        )
        val set = hashSetOf<Point>()
        val que = LinkedList<Int>().apply {
            add(sr)
            add(sc)
            visit[sr][sc] = true
            set.add(Point(sr, sc))
        }
        while (que.isNotEmpty()) {
            val row = que.poll()
            val col = que.poll()

            dir.forEach {
                val nr = row + it[0]
                val nc = col + it[1]

                if (nr !in 0 until r || nc !in 0 until c) return@forEach
                if (visit[nr][nc] || board[nr][nc] != target) return@forEach

                que.apply {
                    add(nr)
                    add(nc)
                    visit[nr][nc] = true
                    set.add(Point(nr, nc))
                }
            }
        }
        return set
    }

    fun normalize(points: HashSet<Point>): Array<IntArray> {
        val minRow = points.minOf { it.row }
        val maxRow = points.maxOf { it.row }
        val minCol = points.minOf { it.col }
        val maxCol = points.maxOf { it.col }

        val arr = Array(maxRow - minRow + 1) { IntArray(maxCol - minCol + 1) }
        points.forEach {
            arr[it.row - minRow][it.col - minCol] = 1
        }
        return arr
    }
}

fun main() {
//    val game_board = arrayOf(
//        intArrayOf(1,1,0,0,1,0),
//        intArrayOf(0,0,1,0,1,0),
//        intArrayOf(0,1,1,0,0,1),
//        intArrayOf(1,1,0,1,1,1),
//        intArrayOf(1,0,0,0,1,0),
//        intArrayOf(0,1,1,1,0,0)
//    )
//    val table = arrayOf(
//        intArrayOf(1,0,0,1,1,0),
//        intArrayOf(1,0,1,0,1,0),
//        intArrayOf(0,1,1,0,1,1),
//        intArrayOf(0,0,1,0,0,0),
//        intArrayOf(1,1,0,1,1,0),
//        intArrayOf(0,1,0,0,0,0)
//    )
    val game_board = arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(1,1,0),
        intArrayOf(1,1,1),
    )
    val table = arrayOf(
        intArrayOf(1,1,1),
        intArrayOf(1,0,0),
        intArrayOf(0,0,0),
    )
    println(
        Solution().solution(game_board, table)
    )
}