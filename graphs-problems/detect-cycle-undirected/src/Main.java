public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, false);graph.addEdge(0, 2, false);
        graph.addEdge(1, 3, false);
        graph.addEdge(2, 4, false);
        graph.addEdge(2, 5, false);
        graph.addEdge(3, 6, false);
        graph.addEdge(5, 6, false);

        graph.printGraph();
        graph.containsCycle(0);

        Graph graph2 = new Graph(6);
        graph2.addEdge(0, 1, false);graph.addEdge(0, 2, false);
        graph2.addEdge(1, 3, false);
        graph2.addEdge(2, 4, false);
        graph2.addEdge(2, 5, false);
        graph2.addEdge(3, 6, false);

        graph2.printGraph();
        graph2.containsCycle(0);

        Graph graph3 = new Graph(6);
        graph3.addEdge(0, 1, false);
        graph3.addEdge(0, 2, false);
        graph3.addEdge(1, 3, false);
        graph3.addEdge(2, 4, false);
        graph3.addEdge(2, 5, false);
        graph3.addEdge(3, 6, false);
        graph3.addEdge(5, 4, false);

        graph3.printGraph();
        graph3.containsCycle(0);

        Graph graph4 = new Graph(9);
        graph4.addEdge(0, 1, false);
        graph4.addEdge(0, 2, false);
        graph4.addEdge(1, 3, false);
        graph4.addEdge(2, 4, false);
        graph4.addEdge(2, 5, false);
        graph4.addEdge(3, 6, false);
        graph4.addEdge(5, 7, false);
        graph4.addEdge(7, 8, false);
        graph4.addEdge(8, 9, false);
        graph4.addEdge(9, 3, false);

        graph4.printGraph();
        graph4.containsCycle(0);
    }
}