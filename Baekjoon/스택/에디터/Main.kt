// 에디터
//https://www.acmicpc.net/problem/1406

// Stack or (LinkedList + ListIterator)

// LinkedList를 이용할 때 삽입/삭제가 무조건 O(1)이 아니라는 함정..!
// 왜냐하면, index로 바로 접근하지 못하기 때문에 접근하는데 비용이 별도로 든다.

import java.util.*

fun main() {
    val input = readln()
    val str = LinkedList<Char>()
    var m = readln().toInt()

    for (char in input) {
        str.add(char)
    }

    val iterator = str.listIterator()

    while (iterator.hasNext()) {
        iterator.next()
    }

    while (m-- > 0) {
        val tmp = readln()

        // split()이 생각보다 시간을 많이 잡아 먹는구나...

        when (tmp[0]) {
            'P' -> {
                val char = tmp[2]
                iterator.add(char)
            }

            'L' -> {
                if (iterator.hasPrevious()) iterator.previous()
            }

            'D' -> {
                if (iterator.hasNext()) iterator.next()
            }

            'B' -> {
                if (iterator.hasPrevious()) {
                    iterator.previous()
                    iterator.remove()
                }
            }
        }
    }
    val sb = StringBuilder()

    for (char in str) {
        sb.append(char)
    }

    print(sb.toString())
}