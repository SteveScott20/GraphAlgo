import java.util.Scanner;

public class Initialization {
    private Scanner inputs;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    Initialization(){
        inputs = new Scanner(System.in);
    }

    void setStart(){
        System.out.println(Colors.RED+"ALL INPUTS MUST BE BETWEEN [1,25] it will break otherwise :("+Colors.RESET);
        System.out.println("Input a starting x-coordinate: ");
        startX = inputs.nextInt();
        System.out.println("Input a starting y-coordinate: ");
        startY = inputs.nextInt();
    }

    void setEnd(){
        System.out.println("Input a goal x-coordinate: ");
        endX = inputs.nextInt();
        System.out.println("Input a goal y-coordinate: ");
        endY = inputs.nextInt();
    }

    int getStartX(){
        return startX;
    }

    int getStartY(){
        return startY;
    }

    int getEndX(){
        return endX;
    }

    int getEndY(){
        return endY;
    }
}
