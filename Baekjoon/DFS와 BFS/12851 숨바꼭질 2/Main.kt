// BFS 문제
// 1 -> 4일 때
// 1 -> 1 + 1 -> 2 * 2
// 1 -> 1 * 2 -> 2 * 2 가 서로 다른 경로인게 함정이다.

// 큐에 넣기 바로 전에 방문 체크를 해버리면 위와 같은 경로를 찾지 못한다.
// 따라서, 큐에서 pop을 한 시점에서 방문 체크를 한다.

import java.util.LinkedList

data class Pos(val cnt: Int, val value: Int)

lateinit var visited: BooleanArray
val MAX = 100_000
var first = true

fun solution(N: Int, K: Int): IntArray {
    val array = intArrayOf(0, 0)

    val queue = LinkedList<Pos>()

    queue.offer(Pos(0, N))

    while (!queue.isEmpty()) {
        val cur = queue.poll()

        visited[cur.value] = true

        if (cur.value == K && array[1] != 0 && cur.cnt == array[0]) array[1]++

        if (cur.value == K && array[1] == 0) {
            array[0] = cur.cnt
            array[1]++
            continue
        }

        if (cur.value * 2 <= MAX && !visited[cur.value * 2]) {
            queue.offer(Pos(cur.cnt + 1, cur.value * 2))
        }
        if (cur.value + 1 <= MAX && !visited[cur.value + 1]) {
            queue.offer(Pos(cur.cnt + 1, cur.value + 1))
        }
        if (cur.value - 1 >= 0 && !visited[cur.value - 1]) {
            queue.offer(Pos(cur.cnt + 1, cur.value - 1))
        }
    }

    return array
}

fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }
    visited = BooleanArray(MAX + 1) { false }

    for (a in solution(N, K)) println(a)
}