// 숫자 카드 나누기
// https://school.programmers.co.kr/learn/courses/30/lessons/135807

// GCD

// 한 배열 내에서 최대 공약수를 구해준 다음에, 반대편 배열에서 공약수가 없는지 확인한다.

class Solution {
    
    var answer = 0
    
    fun gcd(p: Int, q: Int) : Int {
        if (q == 0) return p
        return gcd(q, p % q)
    }
    
    fun getGCD(arr: IntArray): Int {
        var GCD = 0
        for (a in arr) {
            GCD = gcd(GCD, a)
        }
        return GCD
    }
    
    fun check(g: Int, arr: IntArray) {
        for (a in arr) {
            if (a % g == 0) return
        }
        
        answer = maxOf(g, answer)
    }
    
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        arrayA.sort()
        arrayB.sort()
        
        if (arrayA contentEquals arrayB) return answer
         
        val g1 = getGCD(arrayA)
        val g2 = getGCD(arrayB)
        
        check(g1, arrayB)
        check(g2, arrayA)
  
        return answer
    }
}