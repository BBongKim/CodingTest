// 1461 - 도서관

// 그리디 문제

// 1. 음수 / 양수 배열 2개로 나눈다.
// 2. 가장 멀리 있는 책은 M개의 책을 들고 한번만 가면 됨으로, 거리를 더하고, 책 배열에서 삭제해준다.
// 3. 남은 책들은 M개 마다, 왕복 거리를 계속 더해준다.

import kotlin.math.abs

fun solution(N: Int, M: Int, books: List<Int>): Int {
    var answer = 0
    val sortedBooks = books.sorted()

    if (sortedBooks.size == 1) return sortedBooks[0]

    var negativeBooks = sortedBooks.filter { it < 0 }.map { abs(it) }
    var positiveBooks = sortedBooks.filter { it > 0 }.reversed()

    var hasNegativeOneWay = false

    if (negativeBooks.isEmpty()) hasNegativeOneWay = false
    else if (positiveBooks.isEmpty()) hasNegativeOneWay = true
    else if (negativeBooks.first() > positiveBooks.first()) hasNegativeOneWay = true

    if (hasNegativeOneWay && negativeBooks.isNotEmpty()) {
        answer += negativeBooks.first()
        negativeBooks = negativeBooks.drop(M)
    } else if (hasNegativeOneWay.not() && positiveBooks.isNotEmpty()) {
        answer += positiveBooks.first()
        positiveBooks = positiveBooks.drop(M)
    }

    var count = 1
    for (book in negativeBooks) {
        if (count > M) count = 1
        if (count++ == 1) answer += (book * 2)
    }

    count = 1
    for (book in positiveBooks) {
        if (count > M) count = 1
        if (count++ == 1) answer += (book * 2)
    }

    return answer
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val books = readln().split(" ").map { it.toInt() }

    println(solution(N, M, books))
}