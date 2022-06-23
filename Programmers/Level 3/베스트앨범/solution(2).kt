// 함수형으로 깔끔하게 짠 코드가 있어서 남겨봤다.
// 1. groupBy를 통해 장르별로 인덱스를 나눈다. {classic=[0, 2, 3], pop=[1, 4]}
// 2. 리스트로 만든다. (Pair자료형의 리스트로 바뀜) [(classic, [0,2,3]), (pop, [1,4])]
// 3. second(인덱스 리스트)의 합으로 내림차순 정렬함 [(pop, [1,4]), (classic, [0,2,3])]
// 4. Pair의 second만 map으로 뽑아서 각 노래의 재생수로 내림차순 정렬, 그리고 2개씩만 뽑음 [[4, 1], [3, 0]]
// 5. 1차원 배열로 flatten [4, 1, 3, 0]
// 6. IntArray로 변환해서 결과 반환

class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices
                .groupBy { genres[it] }
                .toList()
                .sortedByDescending { it.second.sumOf { plays[it] } }
                .map { it.second.sortedByDescending { plays[it] }.take(2) }
                .flatten()
                .toIntArray()
        
    }
}