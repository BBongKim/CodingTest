// Hash 문제
// 조합을 직접 구현해서 각 조합마다 모두 경우의수를 구하는게 답이다.
// 근데, 이렇게 하면 시간초과가 난다.
// 그래서 경우의 수 공식으로 (종류1의 개수 * 종류2의 개수 * 종류3의 개수)을 구하면 되는데, 이는 옷을 안입는 경우가 포함되어 있지 않다.
// 따라서, ((종류1의 개수 + 1) * (종류2의 개수 + 1) * (종류3의 개수 + 1))를 구하면된다.
// 근데, 옷은 무조건 하나는 입어야 되므로, 최종 값이 -1을 해주면된다.

import java.util.*

class Solution {
 
    fun solution(clothes: Array<Array<String>>): Int {       
        var answer = 0
        var temp = 1
        val list = ArrayList<String>()
        val map = HashMap<String, Int>()
        
        for (clothe in clothes) {
            if (map.containsKey(clothe[1])) {
                var tmp = map.get(clothe[1])!!
                map.put(clothe[1], tmp + 1)
            } else {
                list.add(clothe[1])
                map.put(clothe[1], 1)
            }
        }
        
        for (key in map.keys) {
            val tmp = map.get(key)!!
            temp *= (tmp + 1)
        }
        
        answer = temp - 1
                            
        return answer
    }
}