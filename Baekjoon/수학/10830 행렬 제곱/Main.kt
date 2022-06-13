// 수학 문제
// 곱셈 문제와 같은 분할 정복 문제이다.
// 지수를 계속 1이 될 때까지 2씩 나눈다.
// ex.) 2^4 = 2^2 * 2^2 = (2^1 * 2^1) * (2^1 * 2^1)

// 지수가 1인 경우 1000으로 안나눠지는 걸 방지하기 위해, 처음 입력값부터 1000으로 나눈 값으로 초기화한다.

lateinit var mat: Array<IntArray>

fun pow(mat: Array<IntArray>, exp: Long): Array<IntArray> {
    if (exp == 1L) {
        return mat
    }

    val temp = pow(mat, exp / 2)

    return if (exp % 2 == 1L) {
        mul(mul(temp, temp), mat)
    } else mul(temp, temp)
}

fun mul(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
    val temp = Array(a.size) { IntArray(a.size) { 0 } }

    for (i in a.indices) {
        for (j in a.indices) {
            for (k in a.indices) {
                temp[i][j] += a[i][k] * b[k][j]
                temp[i][j] %= 1000
            }
        }
    }

    return temp
}

fun main() {
    val (N, B) = readln().split(" ").map { it.toLong() }

    mat = Array(N.toInt()) { IntArray(N.toInt()) { 0 } }

    repeat(N.toInt()) { i ->
        mat[i] = readln().split(" ").map { it.toInt().mod(1000) }.toIntArray()
    }

    for (m in pow(mat, B)) {
        for (num in m) {
            print("$num ")
        }
        println()
    }
}