// 해시 문제
// 그냥 정렬해서 풀었다.

data class Song(val idx: Int, val plays: Int, val genres: String)

class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = ArrayList<Int>()
        val gpList = ArrayList<Song>()
        val songList = ArrayList<Song>()
        val map = HashMap<String, Int>()
        
        for (i in genres.indices) {
            val song = Song(i, plays[i], genres[i])
            if (map.containsKey(song.genres)) {
                val tmp = map.get(song.genres)!! + song.plays
                map.put(song.genres, tmp)
            } else {
                map.put(song.genres, song.plays)
            }
            songList.add(song)
        }
        
        songList.sortWith(compareByDescending <Song> { it.plays }.thenBy { it.idx })
        
        for (key in map.keys) {
            gpList.add(Song(0, map.get(key)!!, key))
        }
        
        gpList.sortByDescending { it.plays }
        
        for (gp in gpList) {
            var cnt = 0
            for (song in songList) {
                if (cnt >= 2) break
                if (gp.genres == song.genres) {
                    answer.add(song.idx)
                    cnt++
                }
            }
        }
        
        return answer.toIntArray()
    }
}