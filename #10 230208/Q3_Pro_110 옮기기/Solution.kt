import java.util.LinkedList

class Solution {
    val stack = LinkedList<Char>() // 변환 결과를 저장할 스택
    val ans = LinkedList<String>()

    fun solution(s: Array<String>): Array<String> {
        s.forEach { bits ->
            init()
            convert(bits)
        }

        return ans.toTypedArray()
    }

    fun init() = stack.clear()

    fun convert(bits: String) {
        bits.forEach { bit ->
            when {
                // case 1: 스택이 비어있거나 현재 비트가 1 -> 스택에 비트 삽입
                stack.isEmpty() || bit == '1' -> stack.add(bit)

                // case 2: 비트가 0 이지만 110은 아님 -> 스택에 비트 삽입
                bit == '0' && !isValid() -> stack.add(bit)

                // case 3: 비트가 0이고 110이 된다 -> 변환 수행
                else -> {
                    // 앞서 비트 1 두개를 꺼냄
                    stack.pollLast()
                    stack.pollLast()

                    // 스택에 0이 나올때 까지 꺼냄
                    var count = 0
                    while (stack.isNotEmpty() && stack.peekLast() == '1') {
                        stack.pollLast()
                        count++
                    }

                    // 110삽입 후 1비트를 꺼낸 만큼 재삽입
                    stack.addAll(listOf('1', '1', '0'))
                    stack.addAll(List(count) { '1' })
                }
            }
        }

        ans.add(stack.joinToString(""))
    }

    // 스택의 최근 두 비트가 모두 1인지 체크하는 함수
    fun isValid() = with(stack.takeLast(2)) {
        return@with size == 2 && all { it == '1' }
    }
}