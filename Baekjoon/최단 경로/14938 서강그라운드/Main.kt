// 서강그라운드
// https://www.acmicpc.net/problem/14938

// 최단거리 문제

// 다익스트라를 이용했다.
// 1. 출발지에서 다른 노드까지의 최단거리를 다익스트라를 이용하여, 모두 구한다.
// 2. 최단 거리가 M 이하인 노드의 아이템 개수를 모두 더하여, 얻을 수 있는 아이템을 구한다.
// 3. 출발지를 1부터 N까지 반복하여, 아이템의 최대 개수를 구한다.

package Study04

import java.util.PriorityQueue

data class Node(val index: Int, val dist: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.dist - other.dist
    }
}

object Main2 {
    fun solution(N: Int, M: Int, items: List<Int>, map: List<List<Node>>): Int {
        var max = -1

        for (i in 1..N) {
            val distance = Array(N + 1) { Int.MAX_VALUE }
            val queue = PriorityQueue<Node>()

            queue.offer(Node(i, 0))
            distance[i] = 0

            while (queue.isNotEmpty()) {
                val cur = queue.poll()

                if (distance[cur.index] < cur.dist) continue

                for (next in map[cur.index]) {
                    val temp = distance[cur.index] + next.dist

                    if (temp < distance[next.index]) {
                        distance[next.index] = temp
                        queue.offer(Node(next.index, temp))
                    }
                }
            }

            var sum = 0
            for (index in 1..N) {
                if (distance[index] <= M) sum += items[index - 1]
            }

            max = maxOf(sum, max)
        }

        return max
    }
}

fun main() {
    val (N, M, R) = readln().split(" ").map { it.toInt() }
    val items = readln().split(" ").map { it.toInt() }
    val map = List(N + 1) { mutableListOf<Node>() }

    repeat(R) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        map[a].add(Node(b, c))
        map[b].add(Node(a, c))
    }

    println(Main2.solution(N, M, items, map))
}