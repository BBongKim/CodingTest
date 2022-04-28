// 수학 문제
// 검색함 ㅋㅋ
// 분할 정복을 이용하여 거듭 제곱을 O(logN)의 시간복잡도로 구할 수 있다.

fun pow(base: Int, exp: Int, div: Int): Long {
    if (exp == 0) return 1

    val tmp = pow(base, exp / 2, div)
    val n = tmp * tmp % div

    return if (exp % 2 == 0) {
        n
    } else {
        n * base % div
    }
}

fun main() {
    var list = readln().split(" ").map { it.toInt() }

    print(pow(list[0], list[1], list[2]))
}