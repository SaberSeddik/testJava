package algorithmics.unique_path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class Solution {

    public int uniquePathsIII(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        List<Case> cases = new ArrayList<>();
        Case startCase = null, endCase = null;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    startCase = new Case(i, j);
                } else if (grid[i][j] == 2) {
                    endCase = new Case(i, j);
                    cases.add(endCase);
                } else if (grid[i][j] == 0) {
                    cases.add(new Case(i, j));
                }
            }
        }
        return pathWorks(new CaseAndNextOnes(startCase, cases), endCase);
    }

    private static int pathWorks(CaseAndNextOnes currentCase, Case destination) {
        if (currentCase.currentCase.equals(destination) && currentCase.nextCases.isEmpty()) {
            return 1;
        }
        int workingPaths = 0;
        for (CaseAndNextOnes caseAndNextOnes : nextOf(currentCase)) {
            workingPaths += pathWorks(caseAndNextOnes, destination);
        }
        return workingPaths;
    }

    private static List<CaseAndNextOnes> nextOf(CaseAndNextOnes currentCaseAndNext) {
        List<CaseAndNextOnes> result = new ArrayList<>();
        Case currentCase = currentCaseAndNext.currentCase;
        {
            Case topCase = new Case(currentCase.x - 1, currentCase.y);
            if (currentCaseAndNext.nextCases.contains(topCase)) {
                List<Case> nextCases = new ArrayList<>(currentCaseAndNext.nextCases);
                nextCases.remove(topCase);
                result.add(new CaseAndNextOnes(topCase, nextCases));
            }
        }
        {
            Case bottomCase = new Case(currentCase.x + 1, currentCase.y);
            if (currentCaseAndNext.nextCases.contains(bottomCase)) {
                List<Case> nextCases = new ArrayList<>(currentCaseAndNext.nextCases);
                nextCases.remove(bottomCase);
                result.add(new CaseAndNextOnes(bottomCase, nextCases));
            }
        }
        {
            Case leftCase = new Case(currentCase.x, currentCase.y - 1);
            if (currentCaseAndNext.nextCases.contains(leftCase)) {
                List<Case> nextCases = new ArrayList<>(currentCaseAndNext.nextCases);
                nextCases.remove(leftCase);
                result.add(new CaseAndNextOnes(leftCase, nextCases));
            }
        }
        {
            Case rightCase = new Case(currentCase.x, currentCase.y + 1);
            if (currentCaseAndNext.nextCases.contains(rightCase)) {
                List<Case> nextCases = new ArrayList<>(currentCaseAndNext.nextCases);
                nextCases.remove(rightCase);
                result.add(new CaseAndNextOnes(rightCase, nextCases));
            }
        }
        return result;
    }


    private static class Case {
        final int x;
        final int y;

        Case(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Case aCase = (Case) o;
            return x == aCase.x &&
                    y == aCase.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class CaseAndNextOnes {
        final Case currentCase;
        final List<Case> nextCases;

        CaseAndNextOnes(Case currentCase, List<Case> nextCase) {
            this.currentCase = currentCase;
            this.nextCases = nextCase;
        }
    }
}
