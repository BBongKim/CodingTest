class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""

        val map = Array(4) { Array(2) { 0 } }

        for (i in survey.indices) {
            surv(survey[i], choices[i], map)
        }

        for (i in map.indices) {

            val tmp = if (map[i][0] >= map[i][1]) 0 else 1

            when (i) {
                0 -> {
                    answer += if (tmp == 0) 'R' else 'T'
                }
                1 -> {
                    answer += if (tmp == 0) 'C' else 'F'
                }
                2 -> {
                    answer += if (tmp == 0) 'J' else 'M'
                }
                3 -> {
                    answer += if (tmp == 0) 'A' else 'N'
                }
            }
        }

        return answer
    }
}

fun surv(survey: String, choices: Int, map: Array<Array<Int>>) {
    when (choices) {
        1 -> { score(survey[0], map, 3) }
        2 -> { score(survey[0], map, 2) }
        3 -> { score(survey[0], map, 1) }
        4 -> { }
        5 -> { score(survey[1], map, 1) }
        6 -> { score(survey[1], map, 2) }
        7 -> { score(survey[1], map, 3) }
    }
}

fun score(c: Char, map: Array<Array<Int>>, s: Int) {
    when (c) {
        'R' -> { map[0][0] += s }
        'T' -> { map[0][1] += s }
        'C' -> { map[1][0] += s }
        'F' -> { map[1][1] += s }
        'J' -> { map[2][0] += s }
        'M' -> { map[2][1] += s }
        'A' -> { map[3][0] += s }
        'N' -> { map[3][1] += s }
    }
}

fun main() {
    val sol = Solution()

    val survey = arrayOf("AN", "CF", "MJ", "RT", "NA")
    val choices = intArrayOf(5, 3, 2, 7, 5)

    println(sol.solution(survey, choices))
}