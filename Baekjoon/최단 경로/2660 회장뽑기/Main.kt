// 회장뽑기
// https://www.acmicpc.net/problem/2660

// 최단 거리 문제

// 다익스트라 사용
// 각 사람마다(1번부터 N번까지) 다른 사람들과의 최단 거리를 구함.
// 각각 구한 최단 거리 중, 가장 최단 거리가 회장 후보임.

import java.util.PriorityQueue

data class Node(val index: Int, val dist: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.dist - other.dist
    }
}

fun solution(N: Int, graph: List<List<Node>>) {
    val answerList = mutableListOf<Int>()

    for (i in 1 .. N) {
        val queue = PriorityQueue<Node>()
        val distance = Array(N + 1) { Int.MAX_VALUE }

        queue.add(Node(i, 0))
        distance[i] = 0

        while(queue.isNotEmpty()) {
            val cur = queue.poll()

            if (distance[cur.index] < cur.dist) continue

            for (friend in graph[cur.index]) {
                val temp = distance[cur.index] + friend.dist

                if (temp < distance[friend.index]) {
                    distance[friend.index] = temp
                    queue.offer(Node(friend.index, temp))
                }
            }
        }

        val a = distance.filterIndexed { index, _ ->  index != 0}.maxOf { it }
        answerList.add(a)
    }

    val min = answerList.minOf { it }

    val candidate = mutableListOf<Int>()
    var count = 0

    for (idx in 0 until N) {
        if (min == answerList[idx]) {
            candidate.add(idx + 1)
            count++
        }
    }

    println("$min $count")
    candidate.forEach { print("$it ") }
}

fun main() {
    val N = readln().toInt()
    val graph = List(N + 1) { mutableListOf<Node>() }

    while(true) {
        val (a, b) = readln().split(" ").map { it.toInt() }

        if (a == -1 && b == -1) break
        graph[a].add(Node(b, 1))
        graph[b].add(Node(a, 1))
    }

    solution(N, graph)
}