// 구슬 찾기
// https://www.acmicpc.net/problem/2617

// BFS

// 그래프 문제라는 것은 알았는데, 구체적인 아이디어가 떠오르지 않았다 ㅠㅠ

// 핵심은 중간이 될 수 없는 노드라면,
// 1. 자기보다 큰 노드가 (N+1)/2 개보다 많거나
// 2. 자기보다 작은 노드가 (N+1)/2 개보다 많다는 것이다.

// 1 2 3 4 5 를 생각해보자.
// 중간값인 3은 자기보다 큰 노드가 2개 작은 노드가 2개이다.
// 중간 값이 아닌 1은 자기보다 작은 노드가 4개나 있다..

// 이렇게, 주어진 조건 중에서 확실하게 판변할 수 있는 것의 개수를 세는 것이 문제의 핵심이다.


package Study06

object Main3 {
    fun solution(N: Int, M: Int, map1: List<List<Int>>, map2: List<List<Int>>): Int {
        var answer = 0

        for (i in 1..N) {
            val flag1 = traverse(N, i, map1)
            val flag2 = traverse(N, i, map2)

            if (flag1 || flag2) answer++
        }

        return answer
    }

    private fun traverse(N: Int, start: Int, map: List<List<Int>>): Boolean {
        val visited = Array(N + 1) { false }
        val queue = ArrayDeque<Int>()
        var cnt = 0

        visited[start] = true
        queue.addLast(start)

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            for (next in map[cur]) {
                if (visited[next]) continue

                cnt++
                visited[next] = true
                queue.addLast(next)
            }
        }

        return cnt >= (N + 1) / 2
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    val map1 = List(N + 1) { mutableListOf<Int>() }
    val map2 = List(N + 1) { mutableListOf<Int>() }

    repeat(M) {
        val (heavy, light) = readln().split(" ").map { it.toInt() }
        map1[heavy].add(light)
        map2[light].add(heavy)
    }

    println(Main3.solution(N, M, map1, map2))
}