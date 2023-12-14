import java.util.*;

public class Graph<T> {

    // in order to store the edges we will use a hashmap
    private final Map<T, List<T>> map = new HashMap<>();

    public int countProvinces() {
        boolean[] visited = new boolean[map.size() + 1];
        int numberOfProvinces = 0;
        if (map.keySet().isEmpty()) {
            return 0;
        } else {
            for (T key : map.keySet()) {
                if(!visited[(Integer) key]){
                    visited = BST(key, visited);
                    numberOfProvinces++;
                }
            }
        }
        return numberOfProvinces;
    }

    private boolean[] BST(T key, boolean[] visited) {

        Queue<T> q = new LinkedList<>();
        q.add(key);
        visited[(Integer)key] = true;
        while (!q.isEmpty()) {
            T deq = q.poll();
            for (int i = 0; i < map.get(deq).size(); i++) {
                T vertex = map.get(deq).get(i);
                if (!visited[(Integer) vertex]) {
                    visited[(Integer) vertex] = true;
                    q.add(vertex);
                }
            }
        }
        return visited;
    }

    public void addVertex(T source) {
        map.put(source, new LinkedList<>());
    }

    public void addEdge(T source, T destination, boolean isDirected) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }

        map.get(source).add(destination);
        if (!isDirected) {
            map.get(destination).add(source);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T vertex : map.keySet()) {
            builder.append(vertex.toString() + ": ");
            for (T adj : map.get(vertex)) {
                builder.append(adj.toString() + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }
}

