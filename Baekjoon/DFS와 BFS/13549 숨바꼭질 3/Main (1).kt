// BFS 문제
// 오래걸림

// 처음엔 DP 인줄 알았는데, 메모리가 안됨
// 이건 나중에서야 알았는데,
// 순간이동 > 뒤 > 앞 순 (가중치가 낮은 순)으로 큐에 삽입하면 답을 보장한다.
// 이는, 가중치가 0 또는 1인 그래프의 최단거리를 구하는 것과 같아서 0-1 BFS라고 한다.

import java.util.LinkedList

val MAX = 1_000_000
val move = intArrayOf(1, -1, 2)
val depth = IntArray(MAX) { 1e9.toInt() }

data class Pos(var value: Int, var depth: Int)

fun solution(N: Int, M: Int): Int {
    var tmp = Pos(N, 0)
    val queue = LinkedList<Pos>()

    if (N > M) return N - M
    if (N == M) return 0

    if (N == 0) {
        tmp.value += 1
        tmp.depth += 1
        depth[1] = 1
        depth[0] = 0
        queue.offer(Pos(tmp.value, tmp.depth))
    } else {
        queue.offer(Pos(tmp.value, tmp.depth))
        depth[tmp.value] = tmp.depth
    }

    while (tmp.value * 2 <= M) {
        tmp.value *= 2
        depth[tmp.value] = tmp.depth
        queue.offer(Pos(tmp.value, tmp.depth))
    }

    while (!queue.isEmpty()) {
        var cur = queue.poll()

        if (cur.value == M) {
            return depth[cur.value]
        }

        for (i in 0..2) {
            if (i == 2) {
                val next = Pos(cur.value * move[i], cur.depth)

                if (next.value >= MAX - 1 || next.value < 0 || depth[next.value] <= next.depth) continue

                queue.offer(next)
                depth[next.value] = next.depth
                continue
            }

            val next = Pos(cur.value + move[i], cur.depth + 1)

            if (next.value >= MAX - 1 || next.value < 0 || depth[next.value] <= next.depth) continue

            queue.offer(next)
            depth[next.value] = next.depth
        }
    }

    return -1
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    println(solution(N, M))
}