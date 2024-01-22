public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, false);
        graph.addEdge(0, 2, false);
        graph.addEdge(1, 3, false);
        graph.addEdge(2, 4, false);
        graph.addEdge(2, 5, false);
        graph.addEdge(3, 6, false);
        graph.addEdge(5, 6, false);

        graph.printGraph();
        System.out.println(graph.isBipartite());
    }
}