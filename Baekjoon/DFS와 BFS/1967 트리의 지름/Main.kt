// 그래프 문제
// 저번에 똑같은 문제를 풀었었다. 그땐, N이 최대 10만이었음
// 그냥 그때랑 똑같이 다익스트라로 풀었다.
// N 값이 작아서 DFS로 하는게 속도는 더 빠른것 같다.

import java.util.PriorityQueue

data class Node(val index: Int, val dist: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.dist - other.dist
    }
}

var map: ArrayList<ArrayList<Node>> = ArrayList()
var answer = 0
var max = -1
var idx = 0

fun dijkstra(N: Int, start: Int): Int {
    val distance = IntArray(N + 1) { 1e9.toInt() }
    distance[start] = 0

    val queue = PriorityQueue<Node>()
    queue.offer(Node(start, 0))

    while(!queue.isEmpty()) {
        val cur = queue.poll()

        if (distance[cur.index] < cur.dist) continue

        for (node in map[cur.index]) {
            val tmp = distance[cur.index] + node.dist

            if (tmp < distance[node.index]) {
                distance[node.index] = tmp
                queue.offer(Node(node.index, tmp))

                if (distance[node.index] > max) {
                    max = distance[node.index]
                    answer = max
                    idx = node.index
                }
            }
        }
    }

    return idx
}

fun main() {
    val N = readln().toInt()

    repeat(N + 1) {
        map.add(ArrayList())
    }

    repeat(N - 1) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }

        map[a].add(Node(b, c))
        map[b].add(Node(a, c))
    }

    val node = dijkstra(N, 1)
    dijkstra(N, node)
    println(answer)

}