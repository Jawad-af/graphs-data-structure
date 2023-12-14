public class Main {

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();

        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
        g.addEdge(4, 2, true);
        g.addEdge(5, 7, true);
        g.addEdge(6, 7, true);
        g.addEdge(5, 6, true);

        System.out.println(g.toString());

        System.out.println("The number of provinces: " + g.countProvinces());
    }
}
