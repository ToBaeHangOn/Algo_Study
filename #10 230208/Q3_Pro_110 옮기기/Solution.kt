import java.util.LinkedList

class Solution {
    var count = 0 // 110의 개수
    var bits = "" // 주어진 s의 원소 (비트 문자열)
    val stack = LinkedList<Char>() // "110"을 제외한 비트를 삽입할 스택

    fun solution(s: Array<String>): Array<String> {
        val ans = Array(s.size) { "" }

        s.forEachIndexed { i, bits ->
            init(bits)         // 1. 초기화
            removeTargets()    // 2. 110 제거
            ans[i] = convert() // 3. 변환 후 정답 갱신
        }

        return ans
    }

    fun init(bits: String) {
        count = 0
        this.bits = bits
        stack.clear()
    }

    fun removeTargets() = bits.forEach { bit ->
        when {
            // case 1 : 현재 비트가 1이다 -> 단순 삽입
            bit == '1' -> stack.add(bit)

            // case 2 : 현재 비트가 0이지만 앞선 두 비트가 11이 아니다 -> 삽입
            !isValid() -> stack.add(bit)

            // case 3 : 현재 비트를 포함해 110이 발견되었다 -> 앞의 두 비트 11 제거 및 count 증가
            else -> {
                stack.pollLast()
                stack.pollLast()

                count++
            }
        }
    }

    // stack의 최근 두 비트가 11인지 확인
    fun isValid() = stack.takeLast(2).joinToString("") == "11"

    fun convert(): String {
        // 스택에 마지막 0의 다음 위치에 count 만큼 110을 추가
        stack.addAll(
            stack.lastIndexOf('0') + 1,
            List(count) { listOf('1', '1', '0') }.flatten()
        )
        return stack.joinToString("")
    }
}

/*
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
}*/
