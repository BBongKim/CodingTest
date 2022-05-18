// 그래서, 다른 분 풀이로 0-1 BFS로 다시 풀어봤다.
// 일반 BFS랑 구조는 같지만, 다른점은 가중치가 낮은 노드를 먼저 큐에 넣는다는 것이다. 

import java.util.LinkedList

val MAX = 100_000
val visited = BooleanArray(MAX + 1) { false }

data class Pos(var value: Int, var depth: Int)

fun solution(N: Int, M: Int): Int {
    val queue = LinkedList<Pos>()

    if (N > M) return N - M
    if (N == M) return 0

    visited[N] = true
    queue.offer(Pos(N, 0))

    while (!queue.isEmpty()) {
        var cur = queue.poll()

        if (cur.value == M) {
            return cur.depth
        }

        if (cur.value * 2 <= MAX && !visited[cur.value * 2]) {
            val next = cur.value * 2
            visited[next] = true
            queue.offer(Pos(next, cur.depth))
        }

        if (cur.value - 1 >= 0 && !visited[cur.value - 1]) {
            val next = cur.value - 1
            visited[next] = true
            queue.offer(Pos(next, cur.depth + 1))
        }

        if (cur.value + 1 <= MAX && !visited[cur.value + 1]) {
            val next = cur.value + 1
            visited[next] = true
            queue.offer(Pos(next, cur.depth + 1))
        }
    }
    return -1
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    println(solution(N, M))
}