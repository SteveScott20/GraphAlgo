import java.util.*;

public class Pathfinder {
    private Map<Integer, List<Vertex>> adjMatrix;
    private Map<Integer, Vertex> vertices;
    private int start;
    private int end;

    Pathfinder(Map<Integer, List<Vertex>> adjMatrix, Map<Integer, Vertex> vertices, int start, int end){
        this.adjMatrix = adjMatrix;
        this.vertices = vertices;
        this.start = start;
        this.end = end;
    }

    //Does Depth First Search to traverse a graph
    List<Vertex> depthFirstSearch(){
        Integer current;
        List<Vertex> currentPath = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            current = stack.pop();
            vertices.get(current).visit();
            currentPath.add(vertices.get(current));
            if(current == end){
                return currentPath;
            }
            if(adjMatrix.get(current) != null){
                for (Vertex v: adjMatrix.get(current)) {
                    if(!vertices.get(v.identifier()).isVisited()){
                        stack.push(v.identifier());
                    }
                }
            }
            adjMatrix.remove(current);
        }
        return null;
    }

    //Does Breadth First Search to traverse a graph
    List<Vertex> breadthFirstSearch(){
        Integer current;
        List<Vertex> currentPath = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            current = queue.removeFirst();
            if (!vertices.get(current).isVisited()) {
                vertices.get(current).visit();
                currentPath.add(vertices.get(current));
                if (current == end) {
                    return currentPath;
                }
                if (adjMatrix.get(current) != null) {
                    for (Vertex v : adjMatrix.get(current)) {
                        if (!vertices.get(v.identifier()).isVisited()) {
                            queue.add(v.identifier());
                        }
                    }
                }
                adjMatrix.remove(current);
            }
        }
        return null;
    }

    //Implements Uniform Cost Search to set the minimum distances and parent vertices for paths leading to starting vertex
    List<Vertex> uniformCostSearch(){
        Integer current; //the current vertex identifier
        LinkedList<Integer> queue = new LinkedList<>();
        vertices.get(start).setDistance(0); //Setting starting vertex distance to 0
        queue.add(start);
        while(!queue.isEmpty()) {
            current = queue.removeFirst(); //get first element in the queue
            if (!vertices.get(current).isVisited()) { //check if the vertex has been visited already
                vertices.get(current).visit(); //visit the vertex
                if (current == end) { //check if vertex is the goal
                    return getShortestPath(vertices.get(end)); //return path if it is the goal
                }
                for (Vertex v : adjMatrix.get(current)) { //for each neighbor of current vertex
                    if (vertices.get(v.identifier()).getDistance() > vertices.get(current).getDistance()+1) { //check if current vertex can provide shorter path than existing
                        List<Integer> temp = Arrays.asList(v.identifier()); //removeAll needs Collection parameter
                        queue.removeAll(temp); //remove all previous instances of the neighbor in the queue
                        vertices.get(v.identifier()).setDistance(vertices.get(current).getDistance()+1); //set the distance to 1 + parents distance
                        vertices.get(v.identifier()).setParent(current); //set parent to current vertex
                        queue.add(v.identifier()); //add it to the queue
                    }
                }
                adjMatrix.remove(current);
            }
        }
        return null;
    }

    //Once parent vertices are set, recursively call to get shortest path until starting node is reached.
    List<Vertex> getShortestPath(Vertex target){
        List<Vertex> shortestPath = new ArrayList();
        shortestPath.add(target);
        if(vertices.get(target.getParent()) != null){
            shortestPath.addAll(getShortestPath(vertices.get(target.getParent())));
        }
        return shortestPath;
    }

    void printPath(int gridSize, List<Vertex> path){
        for (int i = gridSize; i > 0; i--) {
            System.out.println(""); //new line
            for (int j = 1; j <= gridSize; j++) {
                if(!vertices.containsKey(j*gridSize+i)){
                    System.out.print(Colors.RED + "X " + Colors.RESET);
                }
                else if(path.contains(vertices.get(j*gridSize+i))){
                    System.out.print(Colors.BLUE + "0 " + Colors.RESET);
                }
                else if(vertices.get(j*gridSize+i).isVisited()){
                    System.out.print(Colors.YELLOW + "0 " + Colors.RESET);
                }
                else {
                    System.out.print("0 ");
                }
            }
        }
    }

}
