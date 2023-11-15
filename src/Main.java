import java.util.Scanner;
import java.util.Random;
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
        putCarrier(ran, field);
        putSubmarine(ran, field);
        putSubmarine(ran, field);
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7;j++){
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }


    }
    static void putCarrier(Random ran, int[][] field){
        boolean shipIsVertical = ran.nextBoolean();
        int x = ran.nextInt(6);
        int y = ran.nextInt(6);
        if(shipIsVertical){
            if(y < 3){
                field[x][y] += 1;
                field[x][y+1] += 1;
                field[x][y+2] += 1;
            }
            else if(y >= 3){
                field[x][y] += 1;
                field[x][y-1] += 1;
                field[x][y-2] += 1;
            }
        } else if (!shipIsVertical) {
            if(x < 3){
                field[x][y] += 1;
                field[x+1][y] += 1;
                field[x+2][y] += 1;
            }
            else if(x >= 3){
                field[x][y] += 1;
                field[x+1][y] += 1;
                field[x+2][y] += 1;
            }
        }
    }
    static void putSubmarine(Random ran, int[][] field){
        boolean shipIsVertical = ran.nextBoolean();
        boolean itIsImpossible = true;
        while(itIsImpossible){
            int x = ran.nextInt(6);
            int y = ran.nextInt(6);
            if(shipIsVertical){
                if(y < 2){
                    field[x][y] += 1;
                    field[x][y+1] += 1;
                }
                else if(y >= 2){
                    field[x][y] += 1;
                    field[x][y-1] += 1;
                }
            } else if (!shipIsVertical) {
                if(x < 2){
                    field[x][y] += 1;
                    field[x+1][y] += 1;
                }
                else if(x >= 2){
                    field[x][y] += 1;
                    field[x-1][y] += 1;
                }
            }
            for(int i = 0; i < 7;i++){
                for(int j = 0; j < 7;j++){
                    if(field[i][j] < 2) {
                        itIsImpossible = false;
                    }
                    else if(field[i][j] >= 2){
                        itIsImpossible = true;
                        break;
                    }
                }
                if(itIsImpossible == true){
                    break;
                }
            }
            if(itIsImpossible == true && shipIsVertical){
                if(y < 2){
                    field[x][y] -= 1;
                    field[x][y+1] -= 1;
                }
                else if(y >= 2){
                    field[x][y] -= 1;
                    field[x][y-1] -= 1;
                }
            }
            else if(itIsImpossible == true && !shipIsVertical){
                if(x < 2){
                    field[x][y] -= 1;
                    field[x+1][y] -= 1;
                }
                else if(x >= 2){
                    field[x][y] -= 1;
                    field[x-1][y] -= 1;
                }
            }
        }
    }
}