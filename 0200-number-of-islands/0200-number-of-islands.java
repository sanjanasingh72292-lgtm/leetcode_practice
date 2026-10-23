import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islandCount++;
                    grid[r][c] = '0'; // mark visited immediately
                    
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{r, c});

                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int currR = cell[0];
                        int currC = cell[1];

                        for (int[] dir : directions) {
                            int nextR = currR + dir[0];
                            int nextC = currC + dir[1];

                            if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols && grid[nextR][nextC] == '1') {
                                grid[nextR][nextC] = '0';
                                queue.offer(new int[]{nextR, nextC});
                            }
                        }
                    }
                }
            }
        }

        return islandCount;
    }
}