import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        // 1. Declare the missing indegree array here
        int[] indegree = new int[numCourses]; 
        
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] p : prerequisites) {
            int course = p[0];
            int prereq = p[1];
            
            graph[prereq].add(course);
            indegree[course]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        
        int count = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            count++;
            
            for (int l : graph[course]) {
                indegree[l]--;
                if (indegree[l] == 0) {
                    q.offer(l);
                }
            }
        }
        
        // 2. Add the missing return check to see if all courses can be completed
        return count == numCourses;
    }
}