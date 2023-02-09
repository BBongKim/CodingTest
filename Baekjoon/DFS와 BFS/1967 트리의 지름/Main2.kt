// 트리의 지름
// https://www.acmicpc.net/problem/1967

// DFS

// 예전에는 다익스트라를 비슷하게 써서 풀었다. (두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재해서 괜찮았다.)
// DFS로 푸는 법은 비교적 간단하다.

// 1. 루트에서 가장 거리가 멀리 있는 노드를 찾는다.
// 2. 그 노드에서 가장 멀리 있는 노드와의 거리를 구한다.


package Study06

data class Node(val idx: Int, val cost: Int)

lateinit var list: MutableList<Int>

var startNodeIdx = 0
var max = -1

object Main4 {
    fun solution(N: Int, map: List<List<Node>>): Int {
        val visited = Array(N + 1) { false }

        visited[1] = true
        dfs(Node(1, 0), map, visited, 0)

        visited.fill(false)

        visited[startNodeIdx] = true
        dfs(Node(startNodeIdx, 0), map, visited, 0)

        return max
    }

    private fun dfs(node: Node, map: List<List<Node>>, visited: Array<Boolean>, acc: Int) {
        var isLeaf = true

        for (next in map[node.idx]) {
            if (visited[next.idx]) continue
            visited[node.idx] = true
            isLeaf = false
            dfs(next, map, visited, acc + next.cost)
            visited[node.idx] = false
        }

        if (isLeaf && acc > max) {
            max = acc
            startNodeIdx = node.idx
        }
    }
}

fun main() {
    val N = readln().toInt()
    val map = List(N + 1) { mutableListOf<Node>() }

    repeat(N - 1) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        map[a].add(Node(b, c))
        map[b].add(Node(a, c))
    }

    println(Main4.solution(N, map))
}