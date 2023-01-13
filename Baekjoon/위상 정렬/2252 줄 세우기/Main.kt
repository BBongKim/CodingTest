// 줄 세우기
// https://www.acmicpc.net/problem/2252

// 위상 정렬

// 노드가 32000개이기 때문에, 인접 행렬 사용 불가능

package Study03

object Main3 {
    fun solution(N: Int, M: Int, map: Array<MutableList<Int>>, degrees: Array<Int>): List<Int> {
        val answerList = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()

        for (i in 1 .. N) {
            if (degrees[i] == 0) queue.addLast(i)
        }

        while(queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            answerList.add(cur)

            for (dest in map[cur]) {
                degrees[dest]--
                if (degrees[dest] <= 0) queue.addLast(dest)
            }
        }

        return answerList
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val map = Array(N + 1) { mutableListOf<Int>() }
    val degrees = Array(N + 1) { 0 }

    repeat(M) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        map[a].add(b)
        degrees[b]++
    }

    Main3.solution(N, M, map, degrees).forEach { print("$it ") }
}