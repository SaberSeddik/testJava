package algorithmics.unique_path;

/**
 * https://leetcode.com/problems/unique-paths-iii/
 * <p>
 * On a 2-dimensional grid, there are 4 types of squares:
 * <p>
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 * <p>
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 * <p>
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length * grid[0].length <= 20
 */
class Solution {
    public int uniquePathsIII(int[][] grid) {
        if (grid == null)
            return 0;
        int count = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                count += grid[i][j] == 0 ? 1 : 0;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return pathHelper(i, j, grid.length, grid[0].length, grid, count);
                }
            }
        }
        return 0;
    }

    private int pathHelper(int x, int y, int row, int col, int[][] grid, int count) {
        if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] == -1 || grid[x][y] == 3)
            return 0;

        if (grid[x][y] == 2) {
            return count == 0 ? 1 : 0;
        }

        int oldNo = grid[x][y];
        grid[x][y] = 3;

        int ways = 0;
        ways += pathHelper(x, y + 1, row, col, grid, count - 1);
        ways += pathHelper(x + 1, y, row, col, grid, count - 1);
        ways += pathHelper(x - 1, y, row, col, grid, count - 1);
        ways += pathHelper(x, y - 1, row, col, grid, count - 1);

        grid[x][y] = oldNo;
        return ways;
    }
}