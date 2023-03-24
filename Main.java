import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
public class Main {
    public static Dice[][] startGame(Dice[][] gameBoard) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameBoard[i][j] = new Dice();
                gameBoard[i][j].number = 10;
                gameBoard[i][j].color = 10;
            }
        }
        gameBoard[1][1].display = "R";
        gameBoard[1][1].color = 0;
        gameBoard[1][3].display = "B";
        gameBoard[1][3].color = 3;
        gameBoard[1][5].display = "Y";
        gameBoard[1][5].color = 1;
        gameBoard[2][1].display = "4";
        gameBoard[2][1].number = 4;
        gameBoard[2][2].display = "P";
        gameBoard[2][2].color = 4;
        gameBoard[2][3].display = "3";
        gameBoard[2][3].number = 3;
        gameBoard[2][4].display = "G";
        gameBoard[2][4].color = 2;
        gameBoard[2][5].display = "2";
        gameBoard[2][5].number = 2;
        gameBoard[3][2].display = "1";
        gameBoard[3][2].number = 1;
        gameBoard[3][4].display = "4";
        gameBoard[3][4].number = 4;
        gameBoard[4][3].display = "6";
        gameBoard[4][3].number = 6;
//        {"x", "x", "x", "x", "x", "x", "x"}
//        {"x", "R", " ", "B", " ", "Y", "x"}
//        {"x", "4", "P", "3", "G", "2", "x"}
//        {"x", " ", "1", " ", "4", " ", "x"}
//        {"x", " ", " ", "6", " ", " ", "x"}
//        {"x", "x", "x", "x", "x", "x", "x"}
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println('\n' +  "           Welcome to Sagrada!");
        System.out.println("     Check the README file for rules" + '\n');

        return gameBoard;
    }
    public static void endGame(boolean winCondition) {
        if (winCondition) {
            System.out.println();
            System.out.println("0     0   0000    0    0      0       0   0   0    0");
            System.out.println(" 0   0   0    0   0    0      0       0   0   0 0  0");
            System.out.println("   0     0    0   0    0      0   0   0   0   0  0 0");
            System.out.println("   0     0    0   0    0       0 0  00    0   0   00");
            System.out.println("   0      0000     0000          0  0     0   0    0");
        } else {
            System.out.println();
            System.out.println("0     0    0000    0    0       0         0000     00000   000000");
            System.out.println(" 0   0    0    0   0    0       0        0    0   0        0");
            System.out.println("   0      0    0   0    0       0        0    0    0000    00000");
            System.out.println("   0      0    0   0    0       0        0    0        0   0");
            System.out.println("   0       0000     0000        000000    0000    00000    000000");
        }
    }
    public static void roundTrackPrint(Dice[] roundTrack) {
        System.out.print('\n' + "|");
        for (int i = 0; i < 40; i++) {
            if (roundTrack[i].status) {
                if (roundTrack[i].color == 0){
                    System.out.print("\u001B[31m" + roundTrack[i].display + "\u001B[0m");
                } else if (roundTrack[i].color == 1) {
                    System.out.print("\u001B[33m" + roundTrack[i].display + "\u001B[0m");
                } else if (roundTrack[i].color == 2) {
                    System.out.print("\u001B[32m" + roundTrack[i].display + "\u001B[0m");
                } else if (roundTrack[i].color == 3) {
                    System.out.print("\u001B[34m" + roundTrack[i].display + "\u001B[0m");
                } else if (roundTrack[i].color == 4) {
                    System.out.print("\u001B[35m" + roundTrack[i].display + "\u001B[0m");
                }
            } else {
                if (i % 4 == 0) {
                    System.out.print(" " + roundTrack[i].display);
                } else if ((i + 1) % 4 == 0) {
                    System.out.print(" ");
                }
            }

            if ((i + 1) % 4 == 0) {
                System.out.print("|");
            }
        }
        System.out.println('\n');
    }
    public static void boardPrint(Dice[][] gameBoard) {
        System.out.println("    -------------------------------");
        for (int i = 1; i < 5; i++) {
            System.out.print("    |");
            for (int j = 1; j < 6; j++) {
                if (gameBoard[i][j].status) {
                    if (gameBoard[i][j].color == 0){
                        System.out.print("  " + "\u001B[31m" + gameBoard[i][j].display + "\u001B[0m" + "  |");
                    } else if (gameBoard[i][j].color == 1) {
                        System.out.print("  " + "\u001B[33m" + gameBoard[i][j].display + "\u001B[0m" + "  |");
                    } else if (gameBoard[i][j].color == 2) {
                        System.out.print("  " + "\u001B[32m" + gameBoard[i][j].display + "\u001B[0m" + "  |");
                    } else if (gameBoard[i][j].color == 3) {
                        System.out.print("  " + "\u001B[34m" + gameBoard[i][j].display + "\u001B[0m" + "  |");
                    } else {
                        System.out.print("  " + "\u001B[35m" + gameBoard[i][j].display + "\u001B[0m" + "  |");
                    }
                } else {
                    System.out.print("  " + gameBoard[i][j].display + "  |");
                }
            }
            System.out.println('\n' + "    -------------------------------");
        }
    }
    public static int moveCheck(Dice[][] gameBoard, int coordx, int coordy, int color, int number) {         //check for invalid adjacent numbers and colors
        if (gameBoard[coordy][coordx].status) {
            return 1;
        } else if ((gameBoard[coordy + 1][coordx].number == number) || (gameBoard[coordy][coordx + 1].number == number) ||
                    (gameBoard[coordy - 1][coordx].number == number) || (gameBoard[coordy][coordx - 1].number == number)) {
            return 2;
//            if (!Objects.equals(gameBoard[coordy][coordx].display, " ")) {
//                //System.out.println(gameBoard[coordy + 1][coordx].number+", "+number);
//                return 2;
//            }
        } else if ((gameBoard[coordy + 1][coordx].color == color) || (gameBoard[coordy][coordx + 1].color == color) ||
                    (gameBoard[coordy - 1][coordx].color == color) || (gameBoard[coordy][coordx - 1].color == color)) {
            return 3;
//            if (!Objects.equals(gameBoard[coordy][coordx].display, " ")) {
//                //System.out.println(gameBoard[coordy + 1][coordx].color + "," + color);
//                return 3;
//            }
        } else if (!Objects.equals(gameBoard[coordy][coordx].display, " ")) {
            if ((gameBoard[coordy][coordx].number != number) && (gameBoard[coordy][coordx].number != 10)) {
                //System.out.println(gameBoard[coordy][coordx].number+", " +number);
                return 4;
            } else if ((gameBoard[coordy][coordx].color != color) && (gameBoard[coordy][coordx].color != 10)) {
                //System.out.println(gameBoard[coordy][coordx].color+", " +color);
                return 5;
            }
        } else {
            return 0;
        }
        return 0;
    }
    public static boolean winCondition(Dice[][] gameBoard, Dice[] roundTrack, int playerScore, int[] pubObj) {
        int trackCount = 0;
        int[] tempScore = new int[2];
        for (int i = 0; i < 40; i++) {
            if (roundTrack[i].status) {
                trackCount += roundTrack[i].number;
            }
        }

        System.out.println(playerScore);
        for (int k = 0; k < 2; k ++) {
            int temp1 = 0;
            int temp2 = 0;
            if (pubObj[k] == 0) {
                for (int i = 1; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {
                        if (gameBoard[i][j].status && (gameBoard[i][j].number == 1)){
                            temp1 += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 2)) {
                            temp2 += 1;
                        }
                    }
                }
                tempScore[k] = Math.min(temp1, temp2) * 2;
            } else if (pubObj[k] == 1) {
                for (int i = 1; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {
                        if (gameBoard[i][j].status && (gameBoard[i][j].number == 3)){
                            temp1 += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 4)) {
                            temp2 += 1;
                        }
                    }
                }
                tempScore[k] = Math.min(temp1, temp2) * 2;
            } else if (pubObj[k] == 2) {
                for (int i = 1; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {
                        if (gameBoard[i][j].status && (gameBoard[i][j].number == 5)){
                            temp1 += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 6)) {
                            temp2 += 1;
                        }
                    }
                }
                tempScore[k] = Math.min(temp1, temp2) * 2;
            } else if (pubObj[k] == 3) {    //(4) Color Variety
                int[] tempRow = new int[5];
                for (int i = 1; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {
                        if (gameBoard[i][j].status && (gameBoard[i][j].color == 0)){
                            tempRow[0] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].color == 1)){
                            tempRow[1] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].color == 2)){
                            tempRow[2] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].color == 3)){
                            tempRow[3] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].color == 4)){
                            tempRow[4] += 1;
                        }
                    }
                }
                tempScore[k] = Math.min(Math.min(Math.min(Math.min(tempRow[0], tempRow[1]), tempRow[2]), tempRow[3]), tempRow[4]) * 4;
            } else if (pubObj[k] == 4) {    //(4) Column Shade Variety
                int[] tempColumn = new int[4];
                for (int j = 1; j < 6; j++) {
                    if (gameBoard[1][j].status && gameBoard[2][j].status && gameBoard[3][j].status && gameBoard[4][j].status) {
                        tempColumn[0] = gameBoard[1][j].number;
                        tempColumn[1] = gameBoard[2][j].number;
                        tempColumn[2] = gameBoard[3][j].number;
                        tempColumn[3] = gameBoard[4][j].number;
                        if (repeatCheck(tempColumn)) {
                            tempScore[k] += 1;
                        }
                    }
                }
                tempScore[k] *= 4;
            } else if (pubObj[k] == 5) {    //(5) Shade Variety
                int[] tempNum = new int[6];
                for (int i = 1; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {
                        if (gameBoard[i][j].status && (gameBoard[i][j].number == 1)){
                            tempNum[0] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 2)){
                            tempNum[1] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 3)){
                            tempNum[2] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 4)){
                            tempNum[3] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 5)){
                            tempNum[4] += 1;
                        } else if (gameBoard[i][j].status && (gameBoard[i][j].number == 6)){
                            tempNum[5] += 1;
                        }
                    }
                }
                tempScore[k] = Math.min(Math.min(Math.min(Math.min(Math.min(tempNum[0], tempNum[1]), tempNum[2]), tempNum[3]), tempNum[4]), tempNum[5]) * 5;
            } else if (pubObj[k] == 6) {    //(5) Column Color Variety
                int[] tempColumn = new int[4];
                for (int j = 1; j < 6; j++) {
                    if (gameBoard[1][j].status && gameBoard[2][j].status && gameBoard[3][j].status && gameBoard[4][j].status) {
                        tempColumn[0] = gameBoard[1][j].color;
                        tempColumn[1] = gameBoard[2][j].color;
                        tempColumn[2] = gameBoard[3][j].color;
                        tempColumn[3] = gameBoard[4][j].color;
                         if (repeatCheck(tempColumn)) {
                             tempScore[k] += 1;
                         }
                    }
                }
                tempScore[k] *= 5;
            } else if (pubObj[k] == 7) {    //(5) Row Shade Variety
                int[] tempRow = new int[5];
                for (int i = 1; i < 5; i++) {
                    if (gameBoard[i][1].status && gameBoard[i][2].status && gameBoard[i][3].status && gameBoard[i][4].status && gameBoard[i][5].status) {
                        tempRow[0] = gameBoard[i][1].number;
                        tempRow[1] = gameBoard[i][2].number;
                        tempRow[2] = gameBoard[i][3].number;
                        tempRow[3] = gameBoard[i][4].number;
                        tempRow[4] = gameBoard[i][5].number;
                        if (repeatCheck(tempRow)) {
                            tempScore[k] += 1;
                        }
                    }
                }
                tempScore[k] *= 5;
            } else if (pubObj[k] == 8) {    //(6) Row Color Variety
                int[] tempRow = new int[5];
                for (int i = 1; i < 5; i++) {
                    if (gameBoard[i][1].status && gameBoard[i][2].status && gameBoard[i][3].status && gameBoard[i][4].status && gameBoard[i][5].status) {
                        tempRow[0] = gameBoard[i][1].color;
                        tempRow[1] = gameBoard[i][2].color;
                        tempRow[2] = gameBoard[i][3].color;
                        tempRow[3] = gameBoard[i][4].color;
                        tempRow[4] = gameBoard[i][5].color;
                        if (repeatCheck(tempRow)) {
                            tempScore[k] += 1;
                        }
                    }
                }
                tempScore[k] *= 6;
            } else {
                boolean[][] tempBool = new boolean[6][7];
                int temp = 0;
                for (int i = 1; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {

                        temp = diagonal(tempBool, i, j, gameBoard);
                        if (temp != 1) {
                            tempScore[k] += temp;
                        }

                    }
                }

            }
        }
        playerScore += Math.max(tempScore[0], tempScore[1]);
        System.out.println(playerScore);
        for (int i = 0; i < 8; i++) {
            System.out.println('\n');
        }
        System.out.println("Final Roundtrack and Gameboard:");
        roundTrackPrint(roundTrack);
        boardPrint(gameBoard);
        System.out.println("Points from private objective: " + (playerScore - Math.max(tempScore[0], tempScore[1])) + ". Points from public objective: " + Math.max(tempScore[0], tempScore[1]) + ".");
        System.out.println("Your total score: " + playerScore + ".");
        System.out.println("Roundtrack total: " + trackCount + ".");
        return trackCount < playerScore;
    }
    public static int diagonal (boolean[][] tempBool, int i, int j, Dice[][] gameBoard) {
        int counter = 1;
        if (!tempBool[i][j] && gameBoard[i][j].status) {
            tempBool[i][j] = true;
            if (gameBoard[i][j].color == gameBoard[i + 1][j + 1].color){
                counter += diagonal(tempBool, i + 1, j + 1, gameBoard);
            }
            if (gameBoard[i][j].color == gameBoard[i - 1][j + 1].color){
                counter += diagonal(tempBool, i - 1, j + 1, gameBoard);
            }
            if (gameBoard[i][j].color == gameBoard[i + 1][j - 1].color){
                counter += diagonal(tempBool, i + 1, j - 1, gameBoard);
            }
            if (gameBoard[i][j].color == gameBoard[i - 1][j - 1].color){
                counter += diagonal(tempBool, i - 1, j - 1, gameBoard);
            }
        }
        return counter;
    }
    public static boolean repeatCheck (int[] arr) {
        Set<Integer> nums = new HashSet<>();
        for (int n : arr) {
            if (nums.contains(n)) {
                return false;
            }
            nums.add(n);
        }
        return true;
    }
    public static Dice[] arrayResize(Dice[] handDice, int target, int target2) {
        Dice[] newArr = new Dice[handDice.length];
        for (int i = 1, j = 1; i < handDice.length; i++) {
            if ((i != target) && (i != target2)) {
                newArr[j] = handDice[i];
                j++;
            }
        }
        return newArr;
    }
    public static void pubObj(int[] pubObj) {
        System.out.print("Public objectives: ");
        for (int i = 0; i < 2; i++) {
            if (pubObj[i] == 0) {
                System.out.print("(2) Light Shades");
            } else if (pubObj[i] == 1) {
                System.out.print("(2) Medium Shades");
            } else if (pubObj[i] == 2) {
                System.out.print("(2) Deep Shades");
            } else if (pubObj[i] == 3) {
                System.out.print("(4) Color Variety");
            } else if (pubObj[i] == 4) {
                System.out.print("(4) Column Shade Variety");
            } else if (pubObj[i] == 5) {
                System.out.print("(5) Shade Variety");
            } else if (pubObj[i] == 6) {
                System.out.print("(5) Column Color Variety");
            } else if (pubObj[i] == 7) {
                System.out.print("(5) Row Shade Variety");
            } else if (pubObj[i] == 8) {
                System.out.print("(6) Row Color Variety");
            } else {
                System.out.print("(#) Color Diagonals");
            }
            if (i == 0) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();

        // Dice[] diceBag = new Dice[90]
        // dicebag[0-17]=g, math.rand(5);
        //pick dice: diceBag.pop(math.rand(dicebag.length)

        Dice[][] gameBoard = new Dice[6][7];
        Dice[] roundTrack = new Dice[40];
        int[] pubObj = new int[2];
        int coordx, coordy;
        int choice = 0;
        int choice2 = 0;
        int tempChoice = 0;
        int playerScore = 0;
        int obj1 = rand.nextInt(5);
        int obj2 = rand.nextInt(5);
        pubObj[0] = rand.nextInt(10);
        pubObj[1] = rand.nextInt(10);
        if (obj2 == obj1 && obj2 == 4) {
            obj2 -= 1;
        } else if (obj2 == obj1) {
            obj2 += 1;
        }
        if (pubObj[1] == pubObj[0] && pubObj[1] == 9) {
            pubObj[1] -= 1;
        } else if (pubObj[1] == pubObj[0]) {
            pubObj[1] += 1;
        }

        for (int i = 0; i < 40; i ++) {
            roundTrack[i] = new Dice();
            roundTrack[i].display = Integer.toString(i / 4 + 1);
        }

        gameBoard = startGame(gameBoard);

        for (int i = 0; i < 10; i++) {
            Dice[] handDice = new Dice[5];
            Dice[] tempDice = new Dice[5];
            //ArrayList<Dice> tempDice = new ArrayList<Dice>();

            if (i != 0) {
                for (int k = 0; k < 6; k++) {
                    System.out.println('\n');
                }
            }
            System.out.println("Turn " + (i + 1) + " of 10:");
            pubObj(pubObj);
            System.out.println("Private objectives: " + Dice.NumtoColor2(obj1) + ", " + Dice.NumtoColor2(obj2));
            roundTrackPrint(roundTrack);
            boardPrint(gameBoard);

            for (int k = 0; k < 2; k++) {
                if (k == 0) {
                    choice = 0;
                    choice2 = 0;
                    System.out.print("The four dice you rolled are: ");
                    for (int j = 1; j < 5; j++) {
                        handDice[j] = new Dice();
                        handDice[j].status = true;
                        handDice[j].display = Integer.toString(handDice[j].number);
                        System.out.print(Dice.NumtoColor(handDice[j].color) + "" + (handDice[j].number) + " ");
                    }
                    System.out.println();
                    do {
                        System.out.print("Enter choice of first die (1-4), or enter 0 to skip: ");
                        choice = userInput.nextInt();
                    } while (choice > 4 || choice < 0);
                    if (choice != 0) {
                        tempChoice = choice;
                    }
                } else {
                    if (choice != 0) {
                        System.out.print("The three remaining dice are: ");
                        for (int j = 1; j < 5; j++) {
                            if (j != choice) {
                                System.out.print(Dice.NumtoColor(handDice[j].color) + "" + (handDice[j].number) + " ");
                            }
                        }
                        System.out.println();
                        do {
                            System.out.print("Enter choice of second die (1-3), or enter 0 to skip: ");
                            choice2 = userInput.nextInt();
                        } while (choice2 > 4 || choice2 < 0);
                        if (choice2 != 0) {
                            if (choice2 >= choice) {
                                choice2++;
                            }
                            tempChoice = choice2;
                        }
                    }
                }

                if ((k == 0 && choice > 0) || (k == 1 && choice2 > 0)) {
                    boolean j = false;
                    do {
                        do {
                            System.out.print("Enter x coordinates (1-5): ");
                            coordx = userInput.nextInt();
                        } while (coordx > 5 || coordx < 1);
                        do {
                            System.out.print("Enter y coordinates (1-4): ");
                            coordy = 5 - userInput.nextInt();
                        } while (coordy > 4 || coordy < 1);

                        if (moveCheck(gameBoard, (coordx), (coordy), handDice[tempChoice].color, (handDice[tempChoice].number)) == 1) {
                            System.out.println("You already placed a die there! Choose another spot for die " + Dice.NumtoColor(handDice[tempChoice].color) + "" + (handDice[tempChoice].number) + ":");
                        } else if (moveCheck(gameBoard, (coordx), (coordy), handDice[tempChoice].color, (handDice[tempChoice].number)) == 2) {
                            System.out.println("Cannot place die next to an adjacent number! Choose another spot for die " + Dice.NumtoColor(handDice[tempChoice].color) + "" + (handDice[tempChoice].number) + ":");
                        } else if (moveCheck(gameBoard, (coordx), (coordy), handDice[tempChoice].color, (handDice[tempChoice].number)) == 3) {
                            System.out.println("Cannot place die next to an adjacent color! Choose another spot for die " + Dice.NumtoColor(handDice[tempChoice].color) + "" + (handDice[tempChoice].number) + ":");
                        } else if (moveCheck(gameBoard, (coordx), (coordy), handDice[tempChoice].color, (handDice[tempChoice].number)) == 4) {
                            System.out.println("Cannot place die of that number on that spot! Choose another spot for die " + Dice.NumtoColor(handDice[tempChoice].color) + "" + (handDice[tempChoice].number) + ":");
                        } else if (moveCheck(gameBoard, (coordx), (coordy), handDice[tempChoice].color, (handDice[tempChoice].number)) == 5) {
                            System.out.println("Cannot place die of that color on that spot! Choose another spot for die " + Dice.NumtoColor(handDice[tempChoice].color) + "" + (handDice[tempChoice].number) + ":");
                        } else {
                            j = true;
                        }
                    } while (!j);
                    gameBoard[coordy][coordx] = handDice[tempChoice];
                    if (handDice[tempChoice].color == obj1 || handDice[tempChoice].color == obj2) {
                        playerScore += handDice[tempChoice].number;
                    }
                }

                if (k == 0) {
                    boardPrint(gameBoard);
                } else {
                    tempDice = arrayResize(handDice, choice, choice2);
                }
            }

            if (choice == 0 && choice2 == 0) {
                roundTrack[i * 4] = tempDice[1];
                roundTrack[i * 4 + 1] = tempDice[2];
                roundTrack[i * 4 + 2] = tempDice[3];
                roundTrack[i * 4 + 3] = tempDice[4];
            } else if ((choice == 0) || (choice2 == 0)) {
                roundTrack[i * 4] = tempDice[1];
                roundTrack[i * 4 + 2] = tempDice[2];
                roundTrack[i * 4 + 3] = tempDice[3];
            } else {
                roundTrack[i * 4] = tempDice[1];
                roundTrack[i * 4 + 1] = tempDice[2];
            }
        }
        endGame(winCondition(gameBoard, roundTrack, playerScore, pubObj));
    }
}