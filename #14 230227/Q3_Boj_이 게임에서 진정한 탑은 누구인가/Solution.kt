import java.util.LinkedList

/**
 * 잭스의 행동
 * 0. 아무 것도 안함
 * 1. 공격
 * 2. 스킬(반격)
 */
enum class JAX(val text: String) {
    NONE(""), ATTACK("attack"), COUNTER_STRIKE("counter strike"),
}

/**
 * 피오라의 행동
 * 0. 아무 것도 안함
 * 1. 공격
 * 2. 응수 중 (공격을 막는 중)
 * 3. 응수 상태가 끝나고 공격
 */
enum class FIORA {
    NONE, ATTACK, RIPOSTE, RIPOSTE_ATTACK
}

var jax_hp = 0
var fiora_hp = 0
var jax_damage = 0
var jax_skill_damage = 0
var fiora_damage = 0
var fiora_skill_damage = 0

var min_frame = 301
var max_jax_hp = 0

lateinit var fioraActions: Array<FIORA>
lateinit var dp: Array<Array<IntArray>>
/** 3차원 dp
 * dp[x][y][z] = a
 * 잭스가 x프레임에 y체력을 갖는 피오라 상대로 z번째 행동을 하고자 할 때 a체력을 갖고 있다.
 */

// 잭스의 행동을 기록할 스택 (Int: 프레임, JAX: 잭스의 행동)
val ans = LinkedList<Pair<Int, JAX>>()
val jaxActions = LinkedList<Pair<Int, JAX>>()

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        jax_hp = it[0]
        fiora_hp = it[1]
    }
    readLine().split(" ").map { it.toInt() }.let {
        jax_damage = it[0]
        jax_skill_damage = it[1]
        fiora_damage = it[2]
        fiora_skill_damage = it[3]
    }

    // 피오라의 행동을 배열의 형태로 기록해둔다.
    fioraActions = Array(301 + 15) { FIORA.NONE }
    repeat(readLine().toInt()) {
        val (frame, action) = readLine().split(" ")
        when (action) {
            "attack" -> fioraActions[frame.toInt() + 4] = FIORA.ATTACK

            "riposte" -> {
                fioraActions.fill(
                    FIORA.RIPOSTE,
                    frame.toInt(),
                    frame.toInt() + 9
                )
                fioraActions[frame.toInt() + 9] = FIORA.RIPOSTE_ATTACK
            }
        }
    }

    dp = Array(min_frame + 1) { Array(fiora_hp + 1) { IntArray(3) { Int.MIN_VALUE } } }
}

fun solve() {
    // 0프레임에서부터 전투가 시작된다.
    dfs(0, jax_hp, fiora_hp)

    if (ans.isEmpty()) {
        println("NO")
        return
    }

    val sb = StringBuilder()
    sb.appendLine("YES")

    while (ans.isNotEmpty()) {
        val (frame, action) = ans.poll()
        sb.append(frame).append(' ').appendLine(action.text)
    }

    print(sb)
}

/**
 * frame   : 현재 프레임
 * jax_hp  : 현재 잭스의 체력
 * fiora_hp: 현재 피오라의 체력
 */
fun dfs(
    frame: Int,
    jax_hp: Int,
    fiora_hp: Int
): Any = when {
    // case 1: 현재 프레임이 이전에 정답을 갱신했던 프레임을 초과한다. -> 더 이상 탐색할 가치가 없다.
    frame > min_frame -> {}

    // case 2: 현재 피오라의 체력이 0이하 -> 전투를 이겼다 -> 정답을 갱신한다.
    fiora_hp <= 0 -> when {
        // 3-1: 이전 보다 더 빠르게 이긴 경우
        frame < min_frame -> {
            min_frame = frame
            max_jax_hp = jax_hp
            updateAnswer()
        }
        // 3-2: 이전과 동일한 빠르기로 이겼지만 더 많은 체력을 남기고 이긴 경우
        jax_hp > max_jax_hp -> {
            max_jax_hp = jax_hp
            updateAnswer()
        }
        // 3-3: 이전과 동일한 빠르기로 이겼지만 이전과 체력이 같거나 적게 남은 경우 -> 정답이 될 수 없다.
        else -> {}
    }

    // case 3: 아직 전투가 끝나지 않았다. -> 이어서 탐색한다
    // 잭스가 특정 프레임에서 할 수 있는 행위는 크게 3가지다.
    // 1. 아무것도 안하거나
    // 2. 공격하거나
    // 3. 스킬쓰거나
    else -> {
        none(frame, jax_hp, fiora_hp)
        attack(frame, jax_hp, fiora_hp)
        counterStrike(frame, jax_hp, fiora_hp)
    }
}

/**
 * 첫 번째 행동 -> 아무것도 하지 않는다.
 */
fun none(frame: Int, jax_hp: Int, fiora_hp: Int) {
    // 현재 프레임에 피오라가 공격을 한다면 잭스의 체력은 닳는다.
    val hp = jax_hp - when (fioraActions[frame]) {
        FIORA.NONE, FIORA.RIPOSTE -> 0

        FIORA.ATTACK -> fiora_damage

        FIORA.RIPOSTE_ATTACK -> fiora_skill_damage
    }

    // 기저 조건
    // 1. 잭스의 체력이 0이하가 된다.
    // 2. 이전에 탐색한 기록보다 체력이 더 많이 잃었다. -> 잭스의 체력은 무조건 많아야 이득이므로
    if (hp <= dp[frame][fiora_hp][0] || hp <= 0) return
    dp[frame][fiora_hp][0] = hp

    dfs(
        frame = frame + 1,
        jax_hp = hp,
        fiora_hp = fiora_hp,
    )
}

/**
 * 두 번째 행동 -> 공격한다.
 */
fun attack(frame: Int, jax_hp: Int, fiora_hp: Int) {
    // 공격 후 5프레임 만큼 지나며 해당 5프레임 동안 피오라의 공격이 있다면 잭스의 체력은 닳는다.
    val nextFrame = frame + 5
    var hp = jax_hp
    for (i in frame until nextFrame) {
        hp -= when (fioraActions[i]) {
            FIORA.NONE, FIORA.RIPOSTE -> 0

            FIORA.ATTACK -> fiora_damage

            FIORA.RIPOSTE_ATTACK -> fiora_skill_damage
        }
    }
    // 기저 조건 (none() 함수와 동일)
    if (hp <= dp[frame][fiora_hp][1] || hp <= 0) return
    dp[frame][fiora_hp][1] = hp

    // 잭스 행동 기록에 추가한 후 탐색을 이어간다.
    jaxActions.addLast(frame to JAX.ATTACK)
    dfs(
        frame = nextFrame,
        jax_hp = hp,
        fiora_hp = fiora_hp - if (fioraActions[nextFrame - 1] == FIORA.RIPOSTE) 0 else jax_damage
        // 잭스가 공격을 했기 때문에 피오라는 '응수 중'이 아니면 체력이 닳는다.
    )
    jaxActions.pollLast()
}

/**
 * 마지막 행동 -> 스킬(반격)
 */
fun counterStrike(frame: Int, jax_hp: Int, fiora_hp: Int) {
    // 공격 후 15프레임 만큼 지나며 마지막 프레임에서 피오라의 공격이 있다면 잭스의 체력이 닳는다.
    val nextFrame = frame + 15
    val hp = jax_hp - when (fioraActions[nextFrame - 1]) {
        FIORA.NONE, FIORA.RIPOSTE -> 0

        FIORA.ATTACK -> fiora_damage

        FIORA.RIPOSTE_ATTACK -> fiora_skill_damage
    }
    // 기저 조건 (none() 함수와 동일)
    if (hp <= dp[frame][fiora_hp][2] || hp <= 0) return
    dp[frame][fiora_hp][2] = hp

    // 잭스 행동 기록에 추가한 후 탐색을 이어간다.
    jaxActions.addLast(frame to JAX.COUNTER_STRIKE)
    dfs(
        frame = nextFrame,
        jax_hp = hp,
        fiora_hp = fiora_hp - if (fioraActions[nextFrame - 1] == FIORA.RIPOSTE) 0 else jax_skill_damage
        // 잭스가 공격을 했기 때문에 피오라는 '응수 중'이 아니면 체력이 닳는다.
    )
    jaxActions.pollLast()
}

fun updateAnswer() = ans.apply {
    clear()
    addAll(jaxActions)
}

fun main() {
    input()
    solve()
}