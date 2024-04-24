import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
    }

    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + " ");
            for (String neighbor : adjacencyList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        visited.add(start);
        stack.push(start);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            System.out.print(vertex + " ");
            for (String neighbor : adjacencyList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }

    public List<String> shortestPath(String start, String end) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(end)) {
            return null;
        }

        Map<String, String> parentMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                break;
            }
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path.size() > 1 ? path : null;
    }

    public static void main(String[] args) {
        Graph cityGraph = new Graph();

        // Adding vertices
        cityGraph.addVertex("A");
        cityGraph.addVertex("B");
        cityGraph.addVertex("C");
        cityGraph.addVertex("D");
        cityGraph.addVertex("E");
        cityGraph.addVertex("F");
        cityGraph.addVertex("G");
        cityGraph.addVertex("H");
        cityGraph.addVertex("I");
        cityGraph.addVertex("J");

        // Adding edges
        cityGraph.addEdge("A", "B");
        cityGraph.addEdge("A", "C");
        cityGraph.addEdge("A", "D");
        cityGraph.addEdge("B", "E");
        cityGraph.addEdge("C", "F");
        cityGraph.addEdge("D", "G");
        cityGraph.addEdge("E", "H");
        cityGraph.addEdge("F", "I");
        cityGraph.addEdge("G", "J");
        cityGraph.addEdge("H", "J");
        cityGraph.addEdge("I", "J");
        cityGraph.addEdge("J", "A");
        cityGraph.addEdge("J", "B");
        cityGraph.addEdge("J", "C");

        // Testing BFS and DFS
        System.out.println("BFS Traversal:");
        cityGraph.bfs("A");
        System.out.println("DFS Traversal:");
        cityGraph.dfs("A");

        // Finding shortest path
        String start = "A";
        String end = "J";
        List<String> shortestPath = cityGraph.shortestPath(start, end);
        if (shortestPath != null) {
            System.out.println("Shortest Path from " + start + " to " + end + ":");
            for (String landmark : shortestPath) {
                System.out.print(landmark + " -> ");
            }
            System.out.println("Destination reached.");
        } else {
            System.out.println("No path exists from " + start + " to " + end);
        }
    }
}
