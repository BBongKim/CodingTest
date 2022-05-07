import java.util.*

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0

        val q1 = LinkedList<Long>()
        val q2 = LinkedList<Long>()

        var sum1 = 0L
        var sum2 = 0L
        var total = 0L
        var target = 0L

        for (i in queue1.indices) {
            q1.offer(queue1[i].toLong())
            sum1 += queue1[i]

            q2.offer(queue2[i].toLong())
            sum2 += queue2[i]
        }

        total = sum1 + sum2

        if ((total % 2) != 0L) return -1

        target = total / 2

        if (sum1 == sum2) return 0

        while (sum1 != sum2) {
            if (q1.isEmpty() || q2.isEmpty() || sum1 == 0L || sum2 == 0L) return -1

            if (sum1 > sum2) {
                val tmp = q1.poll()
                sum2 += tmp
                sum1 -= tmp
                q2.offer(tmp)
            } else {
                val tmp = q2.poll()
                sum1 += tmp
                sum2 -= tmp
                q1.offer(tmp)
            }

            answer++
        }

        return answer
    }
}

fun main() {
    val sol = Solution()

    val queue1 = intArrayOf(1, 1)
    val queue2 = intArrayOf(1, 5)

    println(sol.solution(queue1, queue2))
}