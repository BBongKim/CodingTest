/* 한번엔 못풀고 테스트 케이스 참고했음 */

import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    static class Job implements Comparable<Job> {
        int start, time;
        Job(int start, int time) {
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(Job o) {
            return this.time < o.time ? -1 : 1;
        }
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>();
        ArrayList<Job> list = new ArrayList<>();

        for(int[] j : jobs) {
            list.add(new Job(j[0], j[1]));
        }

        list.sort((o1, o2) -> {
            if (o1.start == o2.start) return o1.time < o2.time ? -1 : 1;
            else return o1.start < o2.start ? -1 : 1;
        });

        int idx = 0;
        int sum = 0;
        int prev_time = 0;
        pq.offer(list.get(idx++));

        boolean prev_intersect = false;

        while(!pq.isEmpty()) {
            Job cur = pq.poll();

            if(prev_intersect) {
                sum += prev_time + cur.time - cur.start;
                prev_time += cur.time;
            } else {
                sum += cur.time;
                prev_time = (cur.start + cur.time);
            }

            for(int i = idx; i < list.size(); i++) {
                Job j = list.get(i);
                if(cur.start <= j.start && j.start < prev_time) {
                    pq.offer(j);
                    idx++;
                    prev_intersect = true;
                }
            }

            if(idx < list.size() && !prev_intersect) {
                pq.offer(list.get(idx++));
            }
        }

        answer = sum / jobs.length;
        return answer;
    }

    public static void main(String[] args) {
        int[][] jobs = {{0, 10}, {4, 10}, {15, 2}, {5, 11}};

        System.out.println(solution(jobs));
    }
}