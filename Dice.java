import java.util.Random;

public class Dice {
    Random rand = new Random();
    public int color;             //0=red 1=yellow 2=green 3=blue 4=purple
    public int number;             //1-6
    public boolean status;
    public String display;

    public Dice() {
        this.color = rand.nextInt(5);
        this.number = (rand.nextInt(6) + 1);
        this.status = false;
        this.display = " ";
    }

    public static String NumtoColor(int colorNum) {
        if (colorNum == 0) {
            return "R";
        } else if (colorNum == 1) {
            return "Y";
        } else if (colorNum == 2) {
            return "G";
        } else if (colorNum == 3) {
            return "B";
        } else {
            return "P";
        }
    }
    public static String NumtoColor2 (int color) {
        if (color == 0) {
            return "\u001B[31m" + "Red" + "\u001B[0m";
        } else if (color == 1) {
            return "\u001B[33m" + "Yellow" + "\u001B[0m";
        } else if (color == 2) {
            return "\u001B[32m" + "Green" + "\u001B[0m";
        } else if (color == 3) {
            return "\u001B[34m" + "Blue" + "\u001B[0m";
        } else {
            return "\u001B[35m" + "Purple" + "\u001B[0m";
        }
    }
}