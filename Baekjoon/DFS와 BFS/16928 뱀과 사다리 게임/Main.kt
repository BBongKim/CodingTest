// 뱀과 사다리 게임
// https://www.acmicpc.net/problem/16928

// BFS

// 처음에는 사다리만 고려하면 될 줄 알고, DP로 접근했다.
// 근데, 뱀을 고려하지 않아서 틀린 것 같다.
// 질문 게시판을 봐도, DP는 방향성이 한쪽으로 일정할 때 사용하는 것이 좋아보인다.

// 그래서, BFS로 접근했다.
// 다음 위치에 뱀이나 사다리가 있으면, 큐에 뱀이나 사다리를 타고 나서의 위치를 넣어주었다.

package Study10

import java.util.TreeMap

data class Pos(val idx: Int, val cnt: Int)

object Main4 {
    fun solution(N: Int, M: Int, items: TreeMap<Int, Int>): Int {
        val visited = BooleanArray(101) { false }
        val queue = ArrayDeque<Pos>()

        queue.addLast(Pos(1, 0))

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            if (cur.idx == 100) return cur.cnt

            for (i in 1..6) {
                val next = cur.idx + i

                if (next > 100) continue
                if (visited[next]) continue

                if (items.containsKey(next)) {
                    visited[next] = true
                    visited[items[next]!!] = true
                    queue.addLast(Pos(items[next]!!, cur.cnt + 1))
                } else {
                    visited[next] = true
                    queue.add(Pos(next, cur.cnt + 1))
                }
            }
        }

        return -1
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val items = TreeMap<Int, Int>()

    repeat(N + M) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        items[s] = e
    }

    println(Main4.solution(N, M, items))
}