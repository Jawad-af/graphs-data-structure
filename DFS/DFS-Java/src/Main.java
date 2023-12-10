import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int vertex, boolean[] visited, ArrayList<Integer> dfs) {

        visited[vertex] = true;
        dfs.add(vertex);
        for (Integer vx : graph.get(vertex)) {
            if (visited[vx] == false) {
                dfs(graph, vx, visited, dfs);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> createGraph(int v) {

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        int stop = 1;
        while (stop != 0) {

            System.out.println("Add an edge");
            System.out.println("Source: ");
            Scanner scanner = new Scanner(System.in);
            int source = scanner.nextInt();
            System.out.println("Destination: ");
            int destination = scanner.nextInt();

            adjList.get(source).add(destination);
            adjList.get(destination).add(source);

            System.out.println("Press 1 to continue or 0 to stop");
            stop = scanner.nextInt();
        }
        return adjList;
    }

    public static void printGraph(ArrayList<ArrayList<Integer>> graph) {
        System.out.println("Graph size = " + graph.size());
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("vertex " + i + ": ");
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(graph.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = createGraph(5);
        boolean[] visited = new boolean[6];
        ArrayList<Integer> dfs = new ArrayList<>();
        printGraph(graph);
        dfs(graph, 2, visited, dfs);
        for (Integer element : dfs) {
            System.out.println(element);
        }
    }
}