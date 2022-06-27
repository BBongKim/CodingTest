// Set의 subtract를 이용한 풀이이다.
import java.util.*

class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n
        val lostSet = lost.toSet() - reserve.toSet()
        val reserveSet = (reserve.toSet() - lost.toSet()).toMutableSet()
        
        val lostList = lostSet.sorted()
        val reserverList = ArrayList<Int>(reserveSet.sorted())
              
        for (lostStudent in lostList) {
            when {
                lostStudent - 1 in reserverList -> reserverList.remove(lostStudent - 1)
                lostStudent + 1 in reserverList -> reserverList.remove(lostStudent + 1)               
                else -> answer--
            }
        }
        return answer
    }
}