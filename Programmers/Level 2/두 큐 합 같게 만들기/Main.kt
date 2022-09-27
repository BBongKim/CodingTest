import java.util.*

// 두 큐 합 같게 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/118667

fun solution(queue1: IntArray, queue2: IntArray): Int {
    var cnt = 0

    val startLength = queue1.size
    val MAX_REPEAT = startLength * 3

    val q1 = LinkedList<Int>()
    val q2 = LinkedList<Int>()
    q1.addAll(queue1.toList())
    q2.addAll(queue2.toList())

    var sumOfQueue1 = q1.sumOf { it.toLong() }
    var sumOfQueue2 = q2.sumOf { it.toLong() }
    val target = (sumOfQueue1 + sumOfQueue2) / 2

    if (((sumOfQueue1 + sumOfQueue2) % 2) != 0L) return -1

    while (cnt < MAX_REPEAT) {
        if (sumOfQueue1 == target && sumOfQueue2 == target) break

        if (sumOfQueue1 < target) {
            val num = q2.poll()
            q1.offer(num)
            sumOfQueue2 -= num
            sumOfQueue1 += num
            cnt++
        } else if (sumOfQueue2 < target) {
            val num = q1.poll()
            q2.offer(num)
            sumOfQueue1 -= num
            sumOfQueue2 += num
            cnt++
        }
    }

    return if (cnt == MAX_REPEAT) -1 else cnt
}

fun main() {
    val queue1 = intArrayOf(1, 1, 1, 1, 1)
    val queue2 = intArrayOf(1, 1, 1, 9, 1)

    val ans = solution(queue1, queue2)

    println(ans)
}