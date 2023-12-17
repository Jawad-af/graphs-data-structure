package graph;

import java.util.*;

public class Graph {
    private List<PriorityQueue<Node>> adjList;

    public Graph(int v) {
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new PriorityQueue<>(Comparator.comparingInt(node -> node.weight)));
        }
    }

    public void addEdge(int src, int des, int weight) {
        adjList.get(src).add(new Node(des, weight));
        adjList.get(des).add(new Node(src, weight));
    }

    public int[] dijkstraFindShortestPath(int src) {
        int[] dist = new int[adjList.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(src, 0));
        dist[src] = 0;

        while (!minHeap.isEmpty()) {
            Node extracted = minHeap.poll();

            for (Node adjacent : adjList.get(extracted.vertex)) {
                int currentNode = adjacent.vertex;
                int newDistance = extracted.weight + adjacent.weight;

                if (newDistance < dist[currentNode]) {
                    minHeap.add(new Node(currentNode, newDistance));
                    dist[currentNode] = newDistance;
                }
            }
        }

        return dist;
    }

    private static class Node {
        private int vertex;
        private int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 1);


    }
}
