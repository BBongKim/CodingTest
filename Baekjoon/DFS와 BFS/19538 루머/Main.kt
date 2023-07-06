// 루머
// https://www.acmicpc.net/problem/19538

// BFS

// 처음에는 BFS 시뮬레이션으로 구현했는데, 시간초과가 났다.

// 최대한 while문 1개로 구현하는 방법을 찾아보니.
// 이웃 노드를 방문할 때마다, 자신이 루머를 믿는 사람임을 알려주면 쉽게 구현할 수 있다.

lateinit var map: List<List<Int>>
lateinit var initPeople: List<Int>
lateinit var times: IntArray
lateinit var isBeliever: BooleanArray
lateinit var believerCount: IntArray

object Main2 {
    fun solution(map: List<List<Int>>, initPeople: List<Int>) {
        val queue = ArrayDeque<Int>()
        queue.addAll(initPeople)

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            for (person in map[cur]) {
                believerCount[person]++

                if (believeRumor(person) && !isBeliever[person]) {
                    queue.addLast(person)
                    times[person] = times[cur] + 1
                    isBeliever[person] = true
                }
            }
        }
    }

    private fun believeRumor(i: Int): Boolean {
        return believerCount[i] >= map[i].size.toDouble() / 2
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    times = IntArray(N) { -1 }
    isBeliever = BooleanArray(N) { false }
    believerCount = IntArray(N) { 0 }

    map = List(N) { readLine().split(" ").map { it.toInt() - 1 }.dropLast(1) }

    val M = readLine().toInt()
    initPeople = readLine().split(" ").map { it.toInt() - 1 }

    for (person in initPeople) {
        times[person] = 0
        isBeliever[person] = true
    }

    Main2.solution(map, initPeople)

    for (time in times) print("$time ")
}