import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void bfsAlgorithm(int v, ArrayList<ArrayList<Integer>> adjList) {

        boolean[] visited = new boolean[adjList.size()];
        for (boolean element : visited) {
            element = false;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.println(vertex);
            for (Integer i : adjList.get(vertex)) {
                if (visited[i] == false) {
                    visited[i] = true;
                    queue.add(i);
                }
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
        printGraph(graph);
        bfsAlgorithm(2, graph);
    }
}