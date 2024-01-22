import java.util.*;

public class Graph {

    int vertices;
    Map<Vertex, List<Vertex>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new HashMap<>(vertices);
    }

    class Vertex{
        int value;
        List<Vertex> neighbors;
        Color color;
        public Vertex(int value) {
            this.value = value;
            neighbors = new ArrayList<>();
            this.color = Color.UNDEFINED;
        }
    }

    private Vertex createNewVertex(int value) {
        return new Vertex(value);
    }
    private boolean isCreated(int vertexValue) {
        return adjList.containsKey(vertexValue);
    }
    public void addVertexToGraph(Vertex newVertex) {
        adjList.put(newVertex, new ArrayList<>());
    }

    public void addEdge(int src, int dest, boolean isDirected) {
        if (isCreated(src)) {
            adjList.get(src).add(createNewVertex(dest));
        } else {
            Vertex newVertex = createNewVertex(src);
            addVertexToGraph(newVertex);
            adjList.get(newVertex).add(new Vertex(dest));
        }
        if (!isDirected) {
            if (isCreated(dest)) {
                adjList.get(dest).add(createNewVertex(src));
            } else {
                Vertex newVertex = createNewVertex(dest);
                addVertexToGraph(newVertex);
                adjList.get(newVertex).add(createNewVertex(src));
            }
        }
    }

    public void printGraph() {
        Iterator<Map.Entry<Vertex, List<Vertex>>> iterator = adjList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Vertex, List<Vertex>> entry = iterator.next();
            int key = entry.getKey().value;
            List<Vertex> value = entry.getValue();
            System.out.print(key + ": ");
            for (Vertex node : value) {
                System.out.print(node.value + "  ");
            }
            System.out.println();
        }
    }

    private List<Vertex> getNeighbors(int vertex) {
        return adjList.get(vertex);
    }

    private Color getOppositeColor(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    private boolean bipartiteDfs(Vertex vertex, Color currentColor, Color[] colored) {
        vertex.color = currentColor;
        for (Vertex neighbor : getNeighbors(vertex.value)) {
            if (colored[neighbor.value] == Color.UNDEFINED) {
                if (!bipartiteDfs(neighbor, getOppositeColor(currentColor), colored)) {
                    return false;
                }
            } else if (neighbor.color == currentColor) {
                return false;
            }
        }
        return true;
    }
    public boolean isBipartite() {
        Color[] colored = new Color[vertices + 1];
        for (Map.Entry<Vertex, List<Vertex>> entry : adjList.entrySet()) {
            boolean isBi = bipartiteDfs(entry.getKey(), Color.WHITE, colored);
            if (!isBi) {
                return false;
            }
        }
        return true;
    }
}
