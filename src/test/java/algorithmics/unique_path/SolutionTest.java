package algorithmics.unique_path;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SolutionTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testUniquePathsIII(int[][] grid, int result) {
        assertThat(new Solution().uniquePathsIII(grid), equalTo(result));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}, 2),
                Arguments.of(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}, 4),
                Arguments.of(new int[][]{{0, 1}, {2, 0}}, 0)
        );
    }
}