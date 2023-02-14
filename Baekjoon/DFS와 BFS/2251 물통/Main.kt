// 물통
// https://www.acmicpc.net/problem/2251

// BFS

// 처음에 그냥 6가지 경우의 수만 구하면 풀릴줄 알았는데,
// 오답이 나와서 결국 모든 경우의 수를 구할 필요가 있다고 생각하게 되었다.
// 근데, 아이디어가 갑자기 안떠올라서 풀이봄..
// 역시 답을 아니까 쉬워보임..ㅠㅠ

// A -> B,C
// B -> A,C
// C -> A,B
// 로 물을 옮겼을 때의 상태를 큐에 넣어 BFS로 탐색하면 된다.

// 보통 BFS 방문처리를 큐에 삽입할 때 해주는데, 중복 상태가 많이 나오지 않아서 그런지,
// 큐에서 꺼내는 시점에 방문처리를 해도 시간 초과가 나지 않았다.

package Study07

data class State(val a: Int, val b: Int, val c: Int)

object Main3 {
    fun solution(A: Int, B: Int, C: Int): List<Int> {
        val answer = mutableSetOf<Int>()
        val visited = Array(201) { Array(201) { Array(201) { false } } }
        val queue = ArrayDeque<State>()

        queue.addLast(State(0, 0, C))

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            if (cur.a == 0) answer.add(cur.c)

            if (visited[cur.a][cur.b][cur.c]) continue

            visited[cur.a][cur.b][cur.c] = true

            if (cur.c + cur.a <= A) queue.addLast(State(cur.c + cur.a, cur.b, 0))
            else queue.addLast(State(A, cur.b, cur.c - (A - cur.a)))

            // 2. C -> B
            if (cur.c + cur.b <= B) queue.addLast(State(cur.a, cur.c + cur.b, 0))
            else queue.addLast(State(cur.a, B, cur.c - (B - cur.b)))

            // 3. B -> A
            if (cur.b + cur.a <= A) queue.addLast(State(cur.a + cur.b, 0, cur.c))
            else queue.addLast(State(A, cur.b - (A - cur.a), cur.c))

            // 4. B -> C
            if (cur.b + cur.c <= C) queue.addLast(State(cur.a, 0, cur.c + cur.b))
            else queue.addLast(State(cur.a, cur.b - (C - cur.c), C))

            // 5. A -> B
            if (cur.a + cur.b <= B) queue.addLast(State(0, cur.a + cur.b, cur.c))
            else queue.addLast(State(cur.a - (B - cur.b), B, cur.c))

            // 6. A -> C
            if (cur.a + cur.c <= C) queue.addLast(State(0, cur.b, cur.c + cur.a))
            else queue.addLast(State(cur.a - (C - cur.c), cur.b, C))
        }

        return answer.toList().sorted()
    }
}

fun main() {
    val (A, B, C) = readln().split(" ").map { it.toInt() }

    Main3.solution(A, B, C).forEach { print("$it ") }
}