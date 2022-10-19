// 강의실 배정
// https://www.acmicpc.net/problem/11000

// 그리디, 우선순위 큐

// 첫번째 강의 이후로,
// 시작 시간이 빠른 강의순으로
// 종료 시간이 빠른 강의와 같은 강의실 사용이 가능한지를 비교한다.


import java.util.*

data class Lecture(val s: Int, val e: Int)

fun solution(N: Int, lectureList: List<Lecture>): Int {
    val list = lectureList.sortedBy { it.s }
    val queue = PriorityQueue<Int>()

    queue.offer(list[0].e)

    for (idx in 1 until list.size) {
        queue.offer(list[idx].e)
        if (queue.first() <= list[idx].s) queue.poll()
    }

    return queue.size
}

fun main() {
    val N = readln().toInt()
    val lectureList = mutableListOf<Lecture>()

    repeat(N) {
        val (s, e) = readln().split(" ").map { it.toInt() }
        lectureList.add(Lecture(s, e))
    }

    println(solution(N, lectureList))
}