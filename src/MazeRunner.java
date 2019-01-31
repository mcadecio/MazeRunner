import java.util.Scanner;

public class MazeRunner {
    Maze myMap = new Maze();
    Scanner in = new Scanner(System.in);

    public void intro(){
        System.out.println("Welcome to Maze Runner!\n" +
                "Here is your current position:");
        myMap.printMap();
        while (!myMap.didIWin()) {
            String direction;
            do {
                direction = userMove();
            } while (direction == null);
            move(direction);
            myMap.printMap();
        }

    }

    public String userMove(){
        System.out.println("Where would you like to move?" +
                " (R, L, U, D)");
        String toReturn = in.next();
        if (!toReturn.matches("[RLUD]")) return null;
        else if (!canI(toReturn)){
            System.out.println("Sorry, you've hit a wall");
            return null;
        }
        return toReturn;
    }

    public boolean canI(String s){
        if (s.equals("R")){
           return myMap.canIMoveRight();
        }else if (s.equals("L")) {
            return myMap.canIMoveLeft();
        }else if (s.equals("D")) {
            return myMap.canIMoveDown();
        }else
            return myMap.canIMoveUp();
    }
    public void move(String d){
        if (d.equals("R")){
            myMap.moveRight();
        }else if (d.equals("L")) {
            myMap.moveLeft();
        }else if (d.equals("D")) {
            myMap.moveDown();
        }else
            myMap.moveUp();
    }

    public static void main(String[] args){
        MazeRunner player = new MazeRunner();
        player.intro();
    }
}
