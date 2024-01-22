import java.util.*;

public class Graph {

    Map<Integer, List<Node>> adjList;
    private int vertices;
    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new HashMap<>(vertices);
    }
    class Node{
        int value;
        ArrayList<Node> neighbors;
        public Node(int value) {
            this.value = value;
            neighbors = new ArrayList<>();
        }
    }

    private Node createNode(int value) {return new Node(value);}

    private void addVertex(int source) {adjList.put(source, new ArrayList<>());}

    public void addEdge(int src, int dest, boolean isDirected) {
        if (adjList.containsKey(src)) {
            adjList.get(src).add(createNode(dest));
        }else{
            addVertex(src);
            adjList.get(src).add(createNode(dest));
        }
        if (!isDirected) {
            if (adjList.containsKey(dest)) {
                adjList.get(dest).add(createNode(src));
            } else {
                addVertex(dest);
                adjList.get(dest).add(createNode(src));
            }
        }
    }

    public void printGraph() {
        Iterator<Map.Entry<Integer, List<Node>>> iterator = adjList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Node>> entry = iterator.next();
            int key = entry.getKey();
            List<Node> value = entry.getValue();
            System.out.print(key + ": ");
            for (Node node : value) {
                System.out.print(node.value + "  ");
            }
            System.out.println();
        }
    }

    public boolean containsCycle(int startingVertex) {
        boolean[] visited = new boolean[vertices + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[startingVertex] = true;
        queue.add(startingVertex);
        while (!queue.isEmpty()) {
            int popped = queue.poll();
            List<Node> neighbors = adjList.get(popped);
            for (Node neighbor : neighbors) {
                boolean isVisited = visited[neighbor.value];
                if (queue.contains(neighbor.value) && isVisited) {
                    System.out.println("The cycle detected at the vertices: " + popped + " -> " + neighbor.value);
                    return true;
                }
                if (!isVisited) {
                    visited[neighbor.value] = true;
                    queue.add(neighbor.value);
                }
            }
        }
        System.out.println("No cycle has been detected");
        return false;
    }
}
