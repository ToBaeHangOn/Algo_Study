import kotlin.math.max

var n = 0
var root: Node? = null

data class Node(
    val value: Int,
    var left: Node? = null,
    var right: Node? = null,
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    readLine().split(" ").map { it.toInt() }.forEach { insert(it) }
}

fun solve() {
    println(getLength(root))
}

fun insert(value: Int) {
    root?.let {
        insert(it, value)
    } ?: run {
        root = Node(value)
    }
}

fun insert(parent: Node, value: Int) {
    if (parent.value < value) {
        parent.right?.let {
            insert(it, value)
        } ?: run {
            parent.right = Node(value)
        }
    } else {
        parent.left?.let {
            insert(it, value)
        } ?: run {
            parent.left = Node(value)
        }
    }
}

fun getLength(node: Node?): Int {
    node?.let {
        val left = getLength(it.left)
        val right = getLength(it.right) + 1
        println("node = ${node.value} | $left | $right")
        return max(left, right)
    } ?: return 0
}

fun main() {
    input()
    solve()
}