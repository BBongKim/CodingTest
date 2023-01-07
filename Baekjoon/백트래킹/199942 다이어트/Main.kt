// 다이어트
// https://www.acmicpc.net/problem/19942

// 백트래킹

// 모든 조합을 다 고려한 후에, 최소 영양 조건을 만족하는지 확인하면 된다.
// 이 때, 같은 가격을 가질 때, 사전 순으로 배열을 출력해야 하는 것에 주의해야 한다.

data class Ingredient(val one: Int, val two: Int, val three: Int, val four: Int, val price: Int)

lateinit var visited: Array<Boolean>
var answer = Int.MAX_VALUE
var answerIndexes = emptyList<Int>()

fun solution(N: Int, minValues: List<Int>, ingredient: List<Ingredient>): Int {
    repeat(N + 1) { iter ->
        combination(N, minValues, ingredient, 0, iter)
    }
    return answer
}

fun combination(N: Int, minValues: List<Int>, ingredient: List<Ingredient>, start: Int, M: Int) {
    if (M == 0) {
        val indexes = mutableListOf<Int>()
        val selectedIngredients = mutableListOf<Ingredient>()
        for (i in 0 until N) {
            if (visited[i]) {
                indexes.add(i + 1)
                selectedIngredients.add(ingredient[i])
            }
        }
        findAnswer(minValues, indexes, selectedIngredients)
    } else {
        for (i in start until N) {
            if (!visited[i]) {
                visited[i] = true
                combination(N, minValues, ingredient, i + 1, M - 1)
                visited[i] = false
            }
        }
    }
}

fun findAnswer(minValues: List<Int>, indexes: List<Int>, ingredients: List<Ingredient>) {
    var totalOne = 0
    var totalTwo = 0
    var totalThree = 0
    var totalFour = 0
    var totalPrice = 0

    for (ingredient in ingredients) {
        totalOne += ingredient.one
        totalTwo += ingredient.two
        totalThree += ingredient.three
        totalFour += ingredient.four
        totalPrice += ingredient.price
    }

    if (totalOne >= minValues[0] && totalTwo >= minValues[1] && totalThree >= minValues[2] && totalFour >= minValues[3]) {
        if (totalPrice < answer) {
            answer = totalPrice
            answerIndexes = indexes.sorted()
        } else if (totalPrice == answer) {
            if (answerIndexes.isEmpty()) answerIndexes = indexes.sorted()
            else {
                val endIdx = minOf(answerIndexes.size, indexes.size)
                for (i in 0 until endIdx) {
                    if (indexes[i] < answerIndexes[i]) {
                        answerIndexes = indexes.sorted()
                        break
                    }
                    else if (indexes[i] > answerIndexes[i]) {
                        break
                    }
                }
            }
        }
    }
}

fun main() {
    val N = readln().toInt()
    val minValues = readln().split(" ").map { it.toInt() }
    val ingredients = mutableListOf<Ingredient>()
    visited = Array(N) { false }

    repeat(N) {
        val (one, two, three, four, price) = readln().split(" ").map { it.toInt() }
        ingredients.add(Ingredient(one, two, three, four, price))
    }

    val value = solution(N, minValues, ingredients)

    if (value == Int.MAX_VALUE) {
        println("-1")
    } else {
        println(value)
        answerIndexes.forEach { print("$it ") }
    }
}