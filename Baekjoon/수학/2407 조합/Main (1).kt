// 수학 문제

// 99 45 같은 값을 넣어보면 Long 범위를 훨씬 초과함
// 따라서, BigInteger 를 사용하면 쉽게 구할 수 있다.

import java.math.BigInteger

fun solution(N: Int, M: Int): BigInteger {
    val set = HashSet<Int>()
    var value = BigInteger("1")
    var divisor = BigInteger("1")
    var n = N
    var m = M

    repeat(M) {
        set.add(n--)
    }

    repeat(M) {
        if (m != 1 && set.contains(m)) set.remove(m)
        else divisor = divisor.multiply(m.toBigInteger())
        m--
    }

    for (num in set) {
        value = value.multiply(num.toBigInteger())
    }

    return value.divide(divisor)
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    val answer = solution(N, M)

    println(answer)
}