// 강의실 2
// https://www.acmicpc.net/problem/1379

// 그리디, 정렬, 우선순위 큐

// 강의 리스트는 강의 시작순으로 오름차순.
// 우선순위 큐는 강의 종료 순으로 오름차순.
// 우선순위의 종료 시간보다 강의 시간이 더 빠른 경우 (queue.peek().e > lecture.s) -> 그냥 큐에 삽입
// 더 늦은 경우 -> 큐를 poll()하고 큐에 삽입

package Study17

import java.util.PriorityQueue

data class Lecture(val i: Int, val s: Int, val e: Int) : Comparable<Lecture> {
    override fun compareTo(other: Lecture): Int = this.e - other.e
}

object Main {
    fun solution(N: Int, lectures: List<Lecture>) {
        val queue = PriorityQueue<Lecture>()
        val map = HashMap<Int, Int>()
        var cnt = 0

        for (lecture in lectures) {
            if (queue.isNotEmpty() && lecture.s >= queue.peek().e) {
                map[lecture.i] = map[queue.peek().i]!!
                queue.poll()
            } else {
                cnt++
                map[lecture.i] = cnt
            }

            queue.offer(lecture)
        }

        println(cnt)

        for (i in 1..N) println(map[i])
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    val lectures = List(N) {
        val (i, s, e) = readLine().split(" ").map { it.toInt() }
        Lecture(i, s, e)
    }.sortedBy { it.s }

    Main.solution(N, lectures)
}