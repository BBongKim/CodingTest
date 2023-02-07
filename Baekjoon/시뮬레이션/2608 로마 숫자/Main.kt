// 로마 숫자
// https://www.acmicpc.net/problem/2608

// 구현 문제

// 변환을 편리하게 하기 위해서, 가능한 로마자를 모두 해시맵에 저장해두었다.

// 1. 문자를 숫자로 변환할 때는, 앞에서부터 한글자씩 변환하면된다. (만약, 두글자가 가능한 경우라면, 2글자로 변환)
// 2. 숫자를 문자로 변환할 때는, 큰 수부터 나머지의 몫만큼 문자를 붙여준다. (만약, 두글자라면 2글자 조건에 맞게 변환)

// IV, IX, XL, XC, CD, CM 는 한번밖에 사용하지 못한다는 것과
// (IV - IX) (XL - XC) (CD - CM)는 같이 쓸 수 없는 조건을 잘 처리해주면다.

package Study06

val symbolsMap = linkedMapOf(
    "M" to 1000,
    "CM" to 900,
    "D" to 500,
    "CD" to 400,
    "C" to 100,
    "XC" to 90,
    "L" to 50,
    "XL" to 40,
    "X" to 10,
    "IX" to 9,
    "V" to 5,
    "IV" to 4,
    "I" to 1,
)

var intAnswer = 0
var romeAnswer = ""

object Main {
    fun solution(a: String, b: String) {
        val value1 = convertToInt(a)
        val value2 = convertToInt(b)
        val sum = value1 + value2
        val rome = convertToRome(sum)

        intAnswer = sum
        romeAnswer = rome
    }

    private fun convertToInt(str: String): Int {
        var sum = 0
        var idx = 0

        while (idx < str.length) {
            if (idx == str.lastIndex) {
                sum += symbolsMap[str[idx++].toString()]!!
                continue
            }

            val curValue = symbolsMap[str[idx].toString()]!!
            val nextValue = symbolsMap[str[idx + 1].toString()]!!

            if (curValue < nextValue) {
                val compound = str[idx].toString() + str[idx + 1].toString()
                sum += symbolsMap[compound]!!
                idx += 2
            } else {
                sum += curValue
                idx++
            }
        }

        return sum
    }

    private fun convertToRome(num: Int): String {
        var left = num

        val sb = StringBuilder()

        var withI = false
        var withX = false
        var withC = false

        for (pair in symbolsMap) {
            val share = left / pair.value
            if (share <= 0) continue

            if (pair.key.length >= 2) {
                if (pair.key[0] == 'I' && withI) continue
                if (pair.key[0] == 'X' && withX) continue
                if (pair.key[0] == 'C' && withC) continue

                sb.append(pair.key)
                left -= pair.value

                if (pair.key[0] == 'I') withI = true
                if (pair.key[0] == 'X') withX = true
                if (pair.key[0] == 'C') withC = true

                continue
            }

            repeat(share) {
                sb.append(pair.key)
            }

            left -= share * pair.value
        }

        return sb.toString()
    }
}

fun main() {
    val a = readln()
    val b = readln()

    Main.solution(a, b)

    println(intAnswer)
    println(romeAnswer)
}