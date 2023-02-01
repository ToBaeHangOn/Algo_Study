var n = 0
var size = 0
lateinit var signals: Array<String>
lateinit var nums: Array<String>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    signals = readLine().chunked(n / 5).toTypedArray()
    nums = Array(10) { "" }
    size = signals[0].length
}

fun solve() {
    init()

    var i = 0
    val sb = StringBuilder()

    while (i < size) {
        val bit = signals[0][i]

        if (bit == '#') {
            val extracted = extract(i)
            sb.append(extracted)

            i = if (extracted == 1) i + 2 else i + 4
        } else i++
    }

    println(sb)
}

/**
 * 미리 각 숫자를 문자열의 형태로 저장함
 */
fun init() {
    nums[0] = "###\n#.#\n#.#\n#.#\n###\n"
    nums[1] = "#\n#\n#\n#\n#\n"
    nums[2] = "###\n..#\n###\n#..\n###\n"
    nums[3] = "###\n..#\n###\n..#\n###\n"
    nums[4] = "#.#\n#.#\n###\n..#\n..#\n"
    nums[5] = "###\n#..\n###\n..#\n###\n"
    nums[6] = "###\n#..\n###\n#.#\n###\n"
    nums[7] = "###\n..#\n..#\n..#\n..#\n"
    nums[8] = "###\n#.#\n###\n#.#\n###\n"
    nums[9] = "###\n#.#\n###\n..#\n###\n"
}

fun extract(index: Int): Int {
    // 특이 케이스인 1의 일치성 확인
    if (isNumOne(index)) return 1

    // 1이 아니라면 숫자를 추출하여 일치되는 것을 찾음
    return nums.indexOf(
        buildString {
            signals.forEach {
                appendLine(
                    it.substring(index, index + 3)
                )
            }
        }
    )
}

/**
 * 특이 케이스: 숫자 1
 * 1. 현재 인덱스가 마지막 인덱스 (예외 처리)
 * 2. 다음 인덱스가 비어있음 (4를 제외한 다른 숫자와의 차이점)
 * 3. 숫자 1과 일치 (숫자 4와의 차이점)
 */
fun isNumOne(index: Int): Boolean = (index == size - 1 || signals[0][index + 1] == '.') &&
    nums[1] == buildString {
        signals.forEach {
            appendLine(it[index])
        }
    }

fun main() {
    input()
    solve()
}