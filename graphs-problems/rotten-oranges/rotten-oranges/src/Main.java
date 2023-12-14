import java.util.*;
class Graph {

    private int[][] adjMatrix;

    public void addEdge(int source, int destination, int value) {
        adjMatrix[source][destination] = value;
        adjMatrix[destination][source] = value;
    }

    public void initializeGraph(int vertices) {
        adjMatrix = new int[vertices][vertices];
    }

    public void tostring() {
        System.out.print("   ");
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] visited = new int[image.length+1][image[0].length+1];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{sr, sc});
        visited[sr][sc] = 1;
        int initialColor = image[sr][sc];
        image[sr][sc] = color;

        int[] row = {1, -1, 0, 0};
        int[] col = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] pixel = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = pixel[0] + row[i];
                int y = pixel[1] + col[i];

                if (x < 0 || y < 0 || x >= image.length || y >= image[0].length ||
                        image[x][y] != initialColor || visited[x][y] == 1) {
                    continue;
                }

                image[x][y] = color;
                visited[x][y] = 1;
                q.add(new int[]{x, y});
            }
        }
        return image;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numberOfvertices = 5;
        Graph g = new Graph();
        g.initializeGraph(numberOfvertices);
        g.addEdge(0,1, 0);
        g.addEdge(1,3, 1);
        g.addEdge(2,4, 1);
        g.addEdge(0,2, 1);
        g.addEdge(3,1, 1);
        g.addEdge(1,0, 0);
        g.addEdge(2,4, 1);
        g.addEdge(3,1, 1);
        g.addEdge(2,2, 2);
        g.addEdge(3,2, 1);
        g.addEdge(1,3, 0);
        g.addEdge(4,1, 1);
        g.addEdge(0,4, 1);

        int[][] matrix = {{2, 1, 1}, {0, 0, 0}, {2, 0, 1}};

        printMatrix(matrix);
        matrix = floodFill(matrix, 1, 1, 2);
        System.out.println();
        printMatrix(matrix);
    }
}