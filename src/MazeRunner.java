import java.util.Scanner;

public class MazeRunner {
    Maze myMap = new Maze();
    Scanner in = new Scanner(System.in);

    public void intro(){
        System.out.println("Welcome to Maze Runner!\n" +
                "Here is your current position:");
        myMap.printMap();
    }

    public String userMove(){
        System.out.println("Where would you like to move?" +
                " (R, L, U, D)");
        String toReturn = in.next();
        if (!canI(toReturn)){
            System.out.println("Sorry, you've hit a wall");
            return null;
        }
        return toReturn;
    }

    public boolean canI(String s){
        try {
            if (s.equals("Q")) {
                System.exit(-1);
                return false;
            } else if (myMap.isThereAPit(s)) {
                return navigatePit(s);
            } else if (s.equals("R")) {
                return myMap.canIMoveRight();
            } else if (s.equals("L")) {
                return myMap.canIMoveLeft();
            } else if (s.equals("D")) {
                return myMap.canIMoveDown();
            } else
                return myMap.canIMoveUp();
        }catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void move(String d){
        switch (d){
            case "R":
                myMap.moveRight();
                break;
            case "L":
                myMap.moveLeft();
                break;
            case "D":
                myMap.moveDown();
                break;
            case "U":
                myMap.moveUp();
                break;
        }
    }
    public void movesMessage( int moves){
        switch (moves){
            case 50 :
                System.out.println("Warning: You have made 50 moves, " +
                        "you have 50 remaining before the maze exit closes");
                break;
            case 75 :
                System.out.println("Alert! You have made 75 moves," +
                        " you only have 25 moves left to escape.");
                break;
            case 90 :
                System.out.println("DANGER! You have made 90 moves, " +
                        "you only have 10 moves left to escape!!");
                break;
            case 100 :
                System.out.println("Oh no! You took too long to escape, " +
                        "and now the maze exit is closed FOREVER >:[");
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                System.exit(0);
        }
    }

    private boolean navigatePit(String dir){
        System.out.println("Watch out! There's a pit ahead, jump it?");
        if (in.next().startsWith("y")){
            myMap.jumpOverPit(dir);
            return true;
        } else
            return false;
    }

    public static void main(String[] args){
        MazeRunner player = new MazeRunner();
        player.intro();
        int playerMoves = 0;
        while (!player.myMap.didIWin()) {
            String direction;
            do {
                direction = player.userMove();
                player.movesMessage(++playerMoves);
            } while (direction == null);
            player.move(direction);
            player.myMap.printMap();
        }
        System.out.println("Congratulations, you made it out alive!" +
                "\nAnd you did it in " + playerMoves + " moves");
    }
}
