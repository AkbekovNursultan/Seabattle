import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> players = new ArrayList<String>();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        gameplay(players, scores);
        endGame(players, scores);
    }
    static void gameplay(ArrayList<String> players, ArrayList<Integer> scores){
        while(true){
            int shots = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Player name:");
            players.add(sc.nextLine());
            Random ran = new Random();
            int[][] field = new int[7][7];
            for(int i = 0; i < 7;i++){
                for(int j = 0; j < 7;j++){
                    field[i][j] = 0;
                }
            }
            //putCarrier(ran, field);
            putCruiser(ran, field);
            //putCruiser(ran, field);
            for(int i = 0; i < 4; i++){
                //putBoat(ran, field);
            }
            for(int i = 0; i < 7; i++){
                for(int j = 0; j < 7;j++){

                    System.out.print(field[j][i] + "  ");

                }
                System.out.println();
            }
            boolean gameEnded = gameover(field);
            while(gameEnded == false){
                System.out.println("Enter coordinates: Example X Y");
                int xShot = sc.nextInt()-1;
                int yShot = sc.nextInt()-1;
                while(xShot < 0|| xShot > 6 || yShot > 6 || yShot < 0){
                    System.out.println("Invalid input.");
                    xShot = sc.nextInt()-1;
                    yShot = sc.nextInt()-1;
                }
                clearScreen();
                if(field[xShot][yShot] == -5 || field[xShot][yShot] == -1){
                    System.out.println("Already shot here.");
                    for(int i = 0; i < 7; i++){
                        for(int j = 0; j < 7;j++){
                            if(field[j][i] == -1){
                                System.out.print("O" + "   ");
                            }
                            else if(field[j][i] == -5){
                                System.out.print("x" + "   ");
                            }
                            else{
                                System.out.print("*" + "   ");
                            }
                        }
                        System.out.println();
                    }
                    continue;
                }
                if(field[xShot][yShot] == 5){
                    field[xShot][yShot] = -5;
                }
                else if(field[xShot][yShot] == 1 || field[xShot][yShot] == 0){
                    field[xShot][yShot] = -1;
                }
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j < 7;j++){
                        if(field[j][i] == -1){
                            System.out.print("O" + "   ");
                        }
                        else if(field[j][i] == -5){
                            System.out.print("x" + "   ");
                        }
                        else{
                            System.out.print("*" + "   ");
                        }
                    }
                    System.out.println();
                }
                shots += 1;
                gameEnded = gameover(field);
            }
            scores.add(shots);
            System.out.println("Congratulations! You have destroyed every ship!\nPlay again?");
            sc.nextLine();
            String answer = sc.nextLine().trim().toLowerCase();
            if(answer.equals("yes")){
                clearScreen();
            }
            else if(answer.equals("no")){
                break;
            }
        }
    }
    static void putCarrier(Random ran, int[][] field){
        boolean shipIsVertical = ran.nextBoolean();
        ArrayList<Integer> x1Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> y1Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> x2Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> y2Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> x3Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> y3Coordinates = new ArrayList<Integer>();
        if(shipIsVertical == true){
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < 7; i++) {
                    if(field[i][j] == 0 && field[i][j+1] == 0 && field[i][j+2] == 0){
                        x1Coordinates.add(i);
                        y1Coordinates.add(j);
                        x2Coordinates.add(i);
                        y2Coordinates.add(j+1);
                        x3Coordinates.add(i);
                        y3Coordinates.add(j+2);
                    }
                }
            }
        }
        else if(shipIsVertical == false){
            for (int j = 0; j < 7; j++) {
                for (int i = 0; i < 5; i++) {
                    if(field[i][j] == 0 && field[i+1][j] == 0 && field[i+2][j] == 0){
                        x1Coordinates.add(i);
                        y1Coordinates.add(j);
                        x2Coordinates.add(i+1);
                        y2Coordinates.add(j);
                        x3Coordinates.add(i+2);
                        y3Coordinates.add(j);
                    }
                }
            }
        }
        int chosenVariant = ran.nextInt(x1Coordinates.size());
        field[x1Coordinates.get(chosenVariant)][y1Coordinates.get(chosenVariant)] = 5;
        field[x2Coordinates.get(chosenVariant)][y2Coordinates.get(chosenVariant)] = 5;
        field[x3Coordinates.get(chosenVariant)][y3Coordinates.get(chosenVariant)] = 5;
        occupyAround(field, x1Coordinates.get(chosenVariant), y1Coordinates.get(chosenVariant), shipIsVertical, 3);
    }
    static void putBoat(Random ran, int[][] field){
        boolean shipIsVertical = true;
        ArrayList<Integer> xCoordinates = new ArrayList<Integer>();
        ArrayList<Integer> yCoordinates = new ArrayList<Integer>();
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 7; i++) {
                if(field[i][j] == 0){
                    xCoordinates.add(i);
                    yCoordinates.add(j);
                }
            }
        }
        int chosenVariant = ran.nextInt(xCoordinates.size());
        field[xCoordinates.get(chosenVariant)][yCoordinates.get(chosenVariant)] = 5;
        occupyAround(field, xCoordinates.get(chosenVariant), yCoordinates.get(chosenVariant), shipIsVertical, 1);
    }
    static void putCruiser(Random ran, int[][] field){
        boolean shipIsVertical = ran.nextBoolean();
        ArrayList<Integer> x1Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> y1Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> x2Coordinates = new ArrayList<Integer>();
        ArrayList<Integer> y2Coordinates = new ArrayList<Integer>();
        if(shipIsVertical == true){
            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < 7; i++) {
                    if(field[i][j] == 0 && field[i][j+1] == 0){
                        x1Coordinates.add(i);
                        y1Coordinates.add(j);
                        x2Coordinates.add(i);
                        y2Coordinates.add(j+1);
                    }
                }
            }
        }
        else if(shipIsVertical == false){
            for (int j = 0; j < 7; j++) {
                for (int i = 0; i < 6; i++) {
                    if(field[i][j] == 0 && field[i+1][j] == 0){
                        x1Coordinates.add(i);
                        y1Coordinates.add(j);
                        x2Coordinates.add(i+1);
                        y2Coordinates.add(j);
                    }
                }
            }
        }
        int chosenVariant = ran.nextInt(x1Coordinates.size());
        field[x1Coordinates.get(chosenVariant)][y1Coordinates.get(chosenVariant)] = 5;
        field[x2Coordinates.get(chosenVariant)][y2Coordinates.get(chosenVariant)] = 5;
        occupyAround(field, x1Coordinates.get(chosenVariant), y1Coordinates.get(chosenVariant), shipIsVertical, 2);
    }
    static void occupyAround(int[][] field, int x, int y, boolean shipIsVertical, int shipSize){
        if(shipIsVertical == true){
            //Sides
            if(x > 0){
                for(int i = 0; i < shipSize; i++){
                    field[x-1][y+i] = 1;
                }
            }
            if(x < 6){
                for(int i = 0; i < shipSize; i++){
                    field[x+1][y+i] = 1;
                }
            }
            if(y > 0){
                field[x][y-1] = 1;
            }
            if(y < 7 - shipSize){
                field[x][y+shipSize] = 1;
            }
            //Corners
            if(x > 0 && y > 0){
                field[x-1][y-1] = 1;
            }
            if(x > 0 && y < 7 - shipSize){
                field[x-1][y + shipSize] = 1;
            }
            if(x < 6 && y > 0){
                field[x+1][y-1] = 1;
            }
            if(x < 6 && y < 7 - shipSize){
                field[x+1][y+shipSize] = 1;
            }
        }
        else if(shipIsVertical == false){
            //Sides
            if(x > 0){
                field[x-1][y] = 1;
            }
            if(x < 7 -shipSize){
                field[x+shipSize][y] = 1;
            }
            if(y > 0){
                for(int i = 0;i < shipSize; i++){
                    field[x+i][y-1] = 1;
                }
            }
            if(y < 6){
                for(int i = 0;i < shipSize; i++){
                    field[x+i][y+1] = 1;
                }
            }
            //Corners
            if(x > 0 && y > 0){
                field[x-1][y-1] = 1;
            }
            if(x > 0 && y < 6){
                field[x-1][y+1] = 1;
            }
            if(x < 7 - shipSize && y > 0){
                field[x+shipSize][y-1] = 1;
            }
            if(x < 7 - shipSize && y < 6){
                field[x+shipSize][y+1] = 1;
            }
        }
    }
    static void checkShip(){

    }
    static boolean gameover(int[][] field){
        boolean end = true;
        for(int i = 0; i < 7;i++){
            for(int j = 0; j < 7;j++){
                if(field[i][j] == 5){
                    end = false;
                }
            }
        }
        return end;
    }
    static void endGame(ArrayList<String> players,ArrayList<Integer> scores){
        System.out.println("Scoreboard:");
        for(int i = 0; i < players.size(); i++){
            System.out.println(players.get(i) + " - " + scores.get(i));
        }

    }
    static void clearScreen(){
        for(int i = 0; i < 5; i++){
            System.out.println();
        }
    }
}