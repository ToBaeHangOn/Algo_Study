class Solution {
    lateinit var time: IntArray

    fun solution(book_time: Array<Array<String>>): Int {
        // 시간을 분으로 치환하여 접근할 것
        // 휴식 시간 10분 까지 고려해 배열 사이즈는 아래와 같음
        time = IntArray(24 * 60 + 10)

        book_time.forEach {
            val (start, end) = it.map { timestamp ->
                val (hour, minute) = timestamp.split(":").map { it.toInt() }
                toMinute(hour) + minute
            }

            // 누적합을 하기 위해 시작점에 +1 끝점에 -1
            time[start]++
            time[end + 10]--
        }

        // 누적합 진행
        for (i in 0 until time.lastIndex) {
            time[i + 1] += time[i]
        }

        return time.maxOf { it }
    }

    fun toMinute(hour: Int) = hour * 60
}