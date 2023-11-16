import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        int[][] field = new int[7][7];
        for(int i = 0; i < 7;i++){
            for(int j = 0; j < 7;j++){
                field[i][j] = 0;
            }
        }
        putBoat(ran, field);
        putBoat(ran, field);
        putBoat(ran, field);
        putBoat(ran, field);
        putCruiser(ran,field);
        putCruiser(ran,field);
        putCarrier(ran, field);
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7;j++){
                System.out.print(field[j][i] + "  ");
            }
            System.out.println();
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
        occupyAround(field, x1Coordinates.get(chosenVariant), y1Coordinates.get(chosenVariant), shipIsVertical, 1);
        System.out.println(shipIsVertical);
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
        System.out.println(shipIsVertical);
    }
    static void occupyAround(int[][] field, int x, int y, boolean shipIsVertical, int shipSize){

    }
}