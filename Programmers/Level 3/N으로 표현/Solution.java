import java.util.HashMap;
import java.util.HashSet;

class Solution {
    static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

    public static int solution(int N, int number) {

        if(N == number) return 1;

        // 1개짜리
        HashSet<Integer> n = new HashSet<>();
        n.add(N);
        map.put(1, n);

        for(int i = 1; i < 9; i++) {
            HashSet<Integer> tmp = new HashSet<>();
            String s = "";
            for (int l = 0; l < i; l++) {
                s += Integer.toString(N);
            }
            if (Integer.parseInt(s) == number) return i;
            tmp.add(Integer.parseInt(s));
            for(int k = 1; k < i; k++) {
                for (int p = 0; p < 4; p++) {
                    for(int n1 : map.get(k)) {
                        for (int n2 : map.get(i-k)) {
                            int ans = -1;
                            int ans2 = -1;
                            if(p == 0) {
                                ans = n1 + n2;
                                tmp.add(ans);
                            }
                            else if (p == 1) {
                                ans = n1 - n2;
                                ans2 = n2 - n1;
                                tmp.add(ans);
                                tmp.add(ans2);
                            }
                            else if (p == 2) {
                                ans = n1 * n2;
                                tmp.add(ans);
                            }
                            else if (p == 3 && n1 != 0 && n2 != 0){
                                ans = n1 / n2;
                                ans2 = n2 / n1;
                                tmp.add(ans);
                                tmp.add(ans2);
                            }
                            if(ans == number || ans2 == number) return i;
                        }
                    }
                }
                map.put(i, tmp);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(5,5555));
    }
}