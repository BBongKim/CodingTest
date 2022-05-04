// 다익스트라 문제
// 그냥 기본 다익스트라 구현인데, 코틀린으로 해봤다

import java.util.*

var N = 0
var M = 0
var map = ArrayList<ArrayList<Node>>()
lateinit var dist: Array<Int>

data class Node(var index: Int, var dist: Int) : Comparable<Node> {
    override fun compareTo(o1: Node): Int {
        return this.dist - o1.dist
    }
}

fun solution(s: Int, e: Int): Int {
    dist = Array(N + 1) { 1e9.toInt() }
    dist[s] = 0

    val queue = PriorityQueue<Node>()

    queue.offer(Node(s, 0))

    while (!queue.isEmpty()) {
        val cur = queue.poll()

        if (dist[cur.index] < cur.dist) continue

        for (node in map[cur.index]) {
            val tmp = dist[cur.index] + node.dist

            if (dist[node.index] > tmp) {
                dist[node.index] = tmp
                queue.offer(Node(node.index, tmp))
            }
        }
    }

    return dist[e]
}

fun main() {
    N = readln().toInt()
    M = readln().toInt()

    repeat(N + 1) { map.add(ArrayList()) }

    repeat(M) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        map[a].add(Node(b, c))
    }

    val (s, e) = readln().split(" ").map { it.toInt() }

    print(solution(s, e))
}