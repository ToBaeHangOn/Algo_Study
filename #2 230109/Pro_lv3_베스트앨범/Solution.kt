import java.util.LinkedList

data class Music(
    val id: Int,
    val genre: String,
    val play: Int
)

data class Genre(
    val name: String,
) {
    var sum: Int = 0
    var first: Music? = null
    var second: Music? = null

    fun addMusic(music: Music) {
        sum += music.play

        when {
            first == null -> first = music

            first!!.play < music.play -> {
                second = first
                first = music
            }

            second == null -> second = music

            second!!.play < music.play -> second = music
        }
    }
}

class Solution {
    val map = hashMapOf<String, Genre>()
    val order = LinkedList<Int>()

    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        for (i in genres.indices) {
            val music = Music(i, genres[i], plays[i])
            val genre = map[genres[i]] ?: Genre(genres[i])

            genre.addMusic(music)
            map[genres[i]] = genre
        }

        map.values.sortedBy { -it.sum }.forEach { genre ->
            genre.first?.let { order.add(it.id) }
            genre.second?.let { order.add(it.id) }
        }

        return order.toIntArray()
    }
}

fun main() {
    println(
        Solution().solution(
            genres = arrayOf("classic", "pop", "classic", "classic", "pop"),
            plays = intArrayOf(500, 600, 150, 800, 2500)
        ).contentToString()
    )
}