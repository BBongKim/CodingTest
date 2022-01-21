import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> ans = new ArrayList<>();

        for (int c : course) {
            // 모든 조합 구하기
            for(String o: orders) {
                boolean[] visited = new boolean[o.length()];
                combination(o, visited, 0, c);
            }

            // 가장 큰 수 구하기
            if (!map.isEmpty()) {
                int max = Collections.max(map.values());
                for (String key: map.keySet()) {
                    if(max > 1 && map.get(key) == max) ans.add(key);
                }
            }

            map.clear();
        }

        Collections.sort(ans);
        answer = ans.toArray(new String[ans.size()]);
        return answer;
    }

    public static void combination (String order, boolean[] visited, int s, int r) {
        if (r == 0) {
            String tmp = "";
            for (int i = 0; i < order.length(); i++) {
                if (visited[i]) tmp += order.charAt(i);
            }
            char[] key = tmp.toCharArray();
            Arrays.sort(key);
            tmp = new String(key);
            map.merge(tmp, 1, Integer::sum);
        } else {
            for (int i = s; i < order.length(); i++) {
                visited[i] = true;
                combination(order, visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};

        for (String s : solution(orders, course)) {
            System.out.println(s);
        }
    }
}