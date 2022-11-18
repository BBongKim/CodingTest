// 최소 스패닝 트리
// https://www.acmicpc.net/problem/1197

// 사이클을 확인해서, 사이클이 없을 때만 간선을 트리에 포함시킨다.

data class Edge(val a: Int, val b: Int, val cost: Int)

lateinit var parent: Array<Int>

fun findParent(x: Int): Int {
    if (x != parent[x]) parent[x] = findParent(parent[x])
    return parent[x]
}

fun unionParent(a: Int, b: Int) {
    val pa = findParent(a)
    val pb = findParent(b)

    if (pa < pb) parent[pb] = pa
    else parent[pa] = pb
}

fun solution(V: Int, E: Int, edges: List<Edge>): Int {
    var sum = 0
    val edgeList = edges.sortedBy { it.cost }

    for (edge in edgeList) {
        if (findParent(edge.a) != findParent(edge.b)) {
            sum += edge.cost
            unionParent(edge.a, edge.b)
        }
    }

    return sum
}

fun main() {
    val (V, E) = readln().split(" ").map { it.toInt() }
    val edges = mutableListOf<Edge>()
    parent = Array(V + 1) { it }

    repeat(E) {
        val (a, b, cost) = readln().split(" ").map { it.toInt() }
        edges.add(Edge(a, b, cost))
    }

    println(solution(V, E, edges))
}