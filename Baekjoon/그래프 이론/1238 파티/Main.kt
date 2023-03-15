// 파티
// https://www.acmicpc.net/problem/1238

// 다익스트라

// 1부터 N번 노드까지, 다익스트라를 돌려준다.
// 그리고, X번 노드까지의 왕복거리 최댓값을 구해주면된다.

package Study11

import java.util.PriorityQueue

data class Edge(val idx: Int, val cost: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = this.cost - other.cost
}

val INFINI = 10e8.toInt()

object Main {
    fun solution(N: Int, M: Int, X: Int, map: List<List<Edge>>): Int {
        var max = -1

        val dis = Array(N + 1) { IntArray(N + 1) { INFINI } }

        for (i in 1..N) {
            dis[i] = dijkstra(N, i, map)
        }

        for (i in 1..N) max = maxOf(max, dis[i][X] + dis[X][i])

        return max
    }

    private fun dijkstra(N: Int, start: Int, map: List<List<Edge>>): IntArray {
        val distances = IntArray(N + 1) { INFINI }
        val queue = PriorityQueue<Edge>()
        distances[0] = 0
        distances[start] = 0

        queue.offer(Edge(start, 0))

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (distances[cur.idx] < cur.cost) continue

            for (next in map[cur.idx]) {
                val temp = distances[cur.idx] + next.cost

                if (temp < distances[next.idx]) {
                    distances[next.idx] = temp
                    queue.offer(Edge(next.idx, temp))
                }
            }
        }

        return distances
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, X) = readLine().split(" ").map { it.toInt() }
    val map = List(N + 1) { mutableListOf<Edge>() }

    repeat(M) {
        val (s, e, c) = readLine().split(" ").map { it.toInt() }
        map[s].add(Edge(e, c))
    }

    println(Main.solution(N, M, X, map))
}