import java.util.*;
import java.lang.Math;

public class Graph {
    private Map<Integer, Vertex> vertices = new HashMap<>();
    private Map<Integer, List<Vertex>> adjMatrix = new HashMap<>();
    private int gridSize;
    private int startX;
    private int startY;
    private int goalX;
    private int goalY;

    Graph(int gridSize, int startX, int startY, int goalX, int goalY){
        this.gridSize = gridSize;
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
        initializeVertices();
        initializeAdjMatrix();
    }

    void initializeVertices(){
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if(Math.random() > .25|| (i == startX && j == startY) || (i == goalX && j == goalY)){ //randomly exclude certain vertices, but always include start and end vertices
                    vertices.put(i*gridSize+j, new Vertex(i, j));
                }
            }
        }
    }

    void initializeAdjMatrix(){
        int x;
        int y;
        for(var entry : vertices.entrySet()){
            x = entry.getValue().getX();
            y = entry.getValue().getY();
            List<Vertex> neighbors = new ArrayList<>();
            if(vertices.containsKey((x-1)*gridSize+y) && x-1>0){
                neighbors.add(vertices.get((x-1)*gridSize+y)); //west neighbor
            }
            if(vertices.containsKey((x+1)*gridSize+y) && x<gridSize){
                neighbors.add(vertices.get((x+1)*gridSize+y)); //east neighbor
            }
            if(vertices.containsKey(x*gridSize+y-1) && y-1>0){
                neighbors.add(vertices.get(x*gridSize+y-1)); //south neighbor
            }
            if(vertices.containsKey(x*gridSize+y+1) && y<gridSize){
                neighbors.add(vertices.get(x*gridSize+y+1)); //north neighbor
            }
            adjMatrix.put(x*gridSize+y, neighbors);
        }
    }

    Map<Integer, List<Vertex>> getAdjMatrix(){
        return adjMatrix;
    }

    Map<Integer, Vertex> getVertices() { return vertices; }

//    public Graph(String[] vertices){
//        initializeVertices(Arrays.asList(vertices));
//        putVertices();
//
//    }
//
//
//
//    public void initializeVertices(List<String> vertices){
//        vertices.forEach(str -> this.vertices.add(new Vertex(str)));
//    }
//
//    public void putVertices(){
//        vertices.forEach(vertex -> adjMatrix.put(vertex, new ArrayList<>()));
//    }

//    public void addNeighbors(){
//        vertices.forEach(vertex -> adjMatrix.computeIfPresent())
//
//    }
//
//    public String getVerticesNames(){
//        StringBuilder result = new StringBuilder("");
//        vertices.forEach(vertex -> result.append(vertex.getName()+", "));
//        return result.toString();
//    }

}
