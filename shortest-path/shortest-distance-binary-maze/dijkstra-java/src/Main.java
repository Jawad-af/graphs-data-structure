import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static class Tuple {
        int row;
        int col;
        int distance = 0;

        public Tuple(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public static void shortestPath(int[][] matrix, int[] src, int[] dest) {
        int numRows = matrix.length;
        int numColumns = matrix[0].length;

        boolean[][] visited = new boolean[numRows][numColumns];
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(src[0], src[1], 0));
        int[][] distance = new int[numRows][numColumns];

        // Set initial distance values
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        visited[src[0]][src[1]] = true;
        distance[src[0]][src[1]] = 1;

        int[] rowNeighbors = {0, 0, 1, -1};
        int[] colNeighbors = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            Tuple current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = current.row + rowNeighbors[i];
                int newCol = current.col + colNeighbors[i];
                int newDistance = distance[current.row][current.col] + 1;

                if (isValid(numRows, numColumns, newRow, newCol) && !visited[newRow][newCol]
                        && matrix[newRow][newCol] == 1 && newDistance < distance[newRow][newCol]) {

                    distance[newRow][newCol] = newDistance;
                    queue.add(new Tuple(newRow, newCol, newDistance));
                    visited[newRow][newCol] = true;

                    // Check if the destination is reached
                    if (Arrays.equals(new int[]{newRow, newCol}, dest)) {
                        System.out.println("The shortest path equals " + distance[newRow][newCol]);
                        return;
                    }
                }
            }
        }
        System.out.println("THE PATH DOES NOT EXIST");
    }

    private static boolean isValid(int numRows, int numColumns, int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numColumns;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };

        int[] src = new int[]{0, 0};
        int[] dest = new int[]{2, 3};
        shortestPath(matrix, src, dest);
    }
}
