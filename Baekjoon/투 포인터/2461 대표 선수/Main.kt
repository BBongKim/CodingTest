// 대표 선수
// https://www.acmicpc.net/problem/2461

// 힙, 투 포인터

// 첫 풀이
// 1. 각 학급 배열을 정렬한 후, 학급마다 포인터를 두었다.
// 2. 먼저, 최대값과 최소값을 구해주고(복잡도 N), 최소값이 있는 학급의 인덱스를 저장했다.
// 3. 그 다음부터, 최대값과 최소값의 차이를 구해주고, 최소값의 학급 포인터를 증가시켰다.
// 2~3을 계속 반복하다가, 한 학급의 포인터가 M 보다 커지면 리턴했다.

// 풀이 자체는 문제가 없었지만, 최대 최소값을 구할 때 N번씩 걸리다보니 여기서 시간초과가 난것같다.
// 근데 이상하게, 자바로는 풀리더라....

// 두번째 풀이
// 그래서, 다른 풀이를 보고 Heap을 사용해서 풀었다.
// Heap add()가 logN 이기 때문에 풀리는 것 같다.

package Study13

import java.util.PriorityQueue

object Main4 {
    data class Item(val idx: Int, val value: Int) : Comparable<Item> {
        override fun compareTo(other: Item): Int = this.value - other.value
    }

    fun solution(N: Int, M: Int, map: Array<ArrayDeque<Int>>): Int {
        val heap = PriorityQueue<Item>()
        var gap = Int.MAX_VALUE
        var max = 0

        for (i in 0 until N) {
            heap.add(Item(i, map[i].first()))
            max = maxOf(map[i].first(), max)
        }

        map[heap.peek().idx].removeFirst()

        while (heap.isNotEmpty()) {
            val minItem = heap.poll()

            gap = minOf(max - minItem.value, gap)

            if (map[minItem.idx].isEmpty()) break

            val next = map[minItem.idx].removeFirst()
            max = maxOf(next, max)
            heap.add(Item(minItem.idx, next))
        }

        return gap
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val map = Array(N) { ArrayDeque(readLine().split(" ").map { it.toInt() }.sorted()) }

    println(Main4.solution(N, M, map))
}
