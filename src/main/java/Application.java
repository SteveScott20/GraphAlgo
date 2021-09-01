import java.util.List;

public class Application {

    public static void main(String[] args) {
        Initialization init = new Initialization();
        init.setStart();
        init.setEnd();
        Vertex start = new Vertex(init.getStartX(),init.getStartY());
        Vertex end = new Vertex(init.getEndX(), init.getEndY());
        Graph testGraph = new Graph(25, init.getStartX(), init.getStartY(), init.getEndX(), init.getEndY());
        Vertex.setGridSize(25);
        Pathfinder testPathfinder = new Pathfinder(testGraph.getAdjMatrix(), testGraph.getVertices(), start.identifier(), end.identifier());
        List<Vertex> testPath = testPathfinder.uniformCostSearch();
        if (testPath == null) {
            System.out.println(Colors.RED + "Oh no, there is no path from start to end. Unlucky!" + Colors.RESET);
        }
        else {
            testPathfinder.printPath(25, testPath);
        }
    }
}
