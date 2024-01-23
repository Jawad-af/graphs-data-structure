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

    class Edge {
        int u;
        int v;
        public Edge(int u, int v){
            this.u = u;
            this.v = v;
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

    private void makeSet(Node node) {

    }

    /*
    << PSEUDOCODE >>

    kruskal(graph):
    A = empty
    for each v in G.V
        makeSet(v)
    sort w(u,v) in G.E// where w is the weight between u and v
    for each (u,v) in G.E
        if(findSet(u) != findSet(v))
            A = A U {(u,v)}
            Union(u,v)
    return A
     */
    public void kruskalAlgorithm() {
        List<Edge> mst = new ArrayList<>();
        for (Map.Entry<Integer, List<Node>> entry : adjList.entrySet()) {
            int key = entry.getKey();
            List<Node> neighbors = entry.getValue();
            for (Node neighbor : neighbors) {

            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addEdge(0, 1, false);
        graph.addEdge(0, 2, false);
        graph.addEdge(1, 3, false);
        graph.addEdge(2, 4, false);
        graph.addEdge(2, 5, false);
        graph.addEdge(3, 6, false);
        graph.addEdge(5, 7, false);
        graph.addEdge(7, 8, false);
        graph.addEdge(8, 9, false);
        graph.addEdge(9, 3, false);

        graph.printGraph();

        Set<Node> set = new LinkedHashSet<>();
        Set<Node> set2 = new LinkedHashSet<>();
        Node node1 = graph.createNode(2);
        Node node2 = graph.createNode(7);
        Node node3 = graph.createNode(9);
        Node node4 = graph.createNode(1);
        Node node5 = graph.createNode(3);
        Node node6 = graph.createNode(0);
        Node node7 = graph.createNode(-1);
        set.add(node1);
        set.add(node2);
        set.add(node3);
        set.add(node4);

        set2.add(node5);
        set2.add(node6);
        set2.add(node7);

        Set<Set<Node>> linkedSets = new LinkedHashSet<>();
        linkedSets.add(set);
        linkedSets.add(set2);

        Iterator<Set<Node>> iterator2 = linkedSets.stream().iterator();

        while (iterator2.hasNext()) {
            Set<Node> temp = iterator2.next();
            for (Node node : temp) {
                System.out.print(node.value + " ");
            }
        }

        System.out.println(linkedSets.);

//        Iterator<Node> iterator = set.iterator();
//        while (iterator.hasNext()) {
//            Node node = iterator.next();
//            System.out.println(node.value);
//        }
    }
}