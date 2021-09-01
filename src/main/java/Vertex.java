import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int xCoordinate;
    private int yCoordinate;
    private boolean visited;
    private int distance;
    private int parent;
    private static int gridSize;

    Vertex(int xCoordinate, int yCoordinate){ //Grid coordinates
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.visited = false;
        this.distance = Integer.MAX_VALUE;
    }

    int getX(){
        return xCoordinate;
    }

    int getY(){
        return yCoordinate;
    }

    int identifier() { return xCoordinate*gridSize+yCoordinate; }

    void visit(){
        visited = true;
    }

    boolean isVisited(){
        return visited;
    }

    void setDistance(int distance){
        this.distance = distance;
    }

    int getDistance(){
        return distance;
    }

    void setParent(int parent) { this.parent = parent; }

    int getParent() { return parent; }

    static void setGridSize(int gridSize){ Vertex.gridSize = gridSize; }

}
