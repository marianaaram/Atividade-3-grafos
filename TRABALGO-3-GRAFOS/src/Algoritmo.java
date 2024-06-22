import java.util.*;

public class Algoritmo {

class MaxFlow {
    private int vertices;
    private int[][] capacity;
    private int[][] flow;

    public MaxFlow(int vertices) {
        this.vertices = vertices;
        this.capacity = new int[vertices][vertices];
        this.flow = new int[vertices][vertices];
    }

    public void addEdge(int from, int to, int capacity) {
        this.capacity[from][to] = capacity;
    }

    private boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && capacity[u][v] - flow[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[sink];
    }

    public int edmondsKarp(int source, int sink) {
        int maxFlow = 0;
        int[] parent = new int[vertices];

        while (bfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
    
}
