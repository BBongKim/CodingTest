// 시뮬레이션 문제
// 톱니 한번에 돌아가는거 착각해서 뻘짓했다 ㅠㅠ

import java.util.*
import kotlin.math.pow

var gear = Array(4) { ArrayDeque<Int>() }
var move = arrayOf(-1, 1)

data class Pos(val idx: Int, val dir: Int)

fun rotate(idx: Int, dir: Int) {

    val queue: Queue<Pos> = LinkedList()
    val visited = Array(4) { false }

    queue.offer(Pos(idx, dir))
    visited[idx] = true

    while (!queue.isEmpty()) {
        val cur = queue.poll()

        for (i in 0..1) {
            val next = cur.idx + move[i]

            if (0 > next || next >= 4 || visited[next]) continue

            if ((i == 0 && gear[cur.idx].elementAt(6) != gear[next].elementAt(2))
                || (i == 1 && gear[cur.idx].elementAt(2) != gear[next].elementAt(6))
            ) {
                if (cur.dir == -1) queue.offer(Pos(next, 1))
                else queue.offer(Pos(next, -1))
                visited[next] = true
            }
        }

        if (cur.dir == 1) {
            val tmp = gear[cur.idx].pollLast()
            gear[cur.idx].offerFirst(tmp)
        } else {
            val tmp = gear[cur.idx].pollFirst()
            gear[cur.idx].offerLast(tmp)
        }
    }
}

fun main() {
    repeat(4) {
        val tmp = readln()
        for (i in tmp.indices) {
            gear[it].offerLast(Character.getNumericValue(tmp[i]))
        }
    }

    val N = readln().toInt()

    repeat(N) {
        val (idx, dir) = readln().split(" ").map { it.toInt() }
        rotate(idx - 1, dir)
    }

    var sum = 0
    repeat(4) { sum += (2.0).pow(it).toInt() * gear[it].peek() }

    print(sum)
}