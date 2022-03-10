// Combination 백트래킹을 통해서 풀었다.
// 자음에서 1개 모음에서 3개 이런식으로 뽑고 모든 결과를 합친 후, 정렬을 하였다.

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> answer = new ArrayList<>();
    static ArrayList<String> mo_list = new ArrayList<>();
    static ArrayList<String> ja_list = new ArrayList<>();
    static boolean[] visited_mo;
    static boolean[] visited_ja;
    static ArrayList<String> mo_result;
    static ArrayList<String> ja_result;

    static class Pair {
        int moum, jaum;

        Pair(int moum, int jaum) {
            this.moum = moum;
            this.jaum = jaum;
        }
    }

    public static void combination(ArrayList<String> list, ArrayList<String> result, boolean[] visited, int N, int start, int M) {
        if (M == 0) {
            int idx = 0;
            String tmp = "";
            for (String l : list) if (visited[idx++]) tmp += l;
            result.add(tmp);
        } else {
            for (int i = start; i < N; i++) {
                visited[i] = true;
                combination(list, result, visited, N, i + 1, M - 1);
                visited[i] = false;
            }
        }
    }

    public static void solution(int L, int C) {
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 1; i < L; i++) {
            if (L - i >= 2) pairs.add(new Pair(i, L - i));
        }

        for (Pair p : pairs) {
            visited_mo = new boolean[mo_list.size()];
            visited_ja = new boolean[ja_list.size()];
            mo_result = new ArrayList<>();
            ja_result = new ArrayList<>();

            combination(mo_list, mo_result, visited_mo, mo_list.size(), 0, p.moum);
            combination(ja_list, ja_result, visited_ja, ja_list.size(), 0, p.jaum);

            for (String m : mo_result) {
                String ans = "";
                for (String j : ja_result) {
                    ans = m + j;
                    char[] tmp = ans.toCharArray();
                    Arrays.sort(tmp);
                    answer.add(new String(tmp));
                }
            }
        }
        Collections.sort(answer);

        for (String a : answer) System.out.println(a);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            String s = st.nextToken();
            if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u")) mo_list.add(s);
            else ja_list.add(s);
        }

        solution(L, C);
    }
}
