/*
 * Created by nahua on 2017-09-06.
 * PennX: SD1x Software Development Fundamentals
 * Homework Assignment 1: Whack A Mole
 */
import java.util.*;

public class WhackAMole {
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;
    int gridDimension;

    public WhackAMole(int numAttempts, int gridDimension) {
        this.attemptsLeft = numAttempts;
        this.moleGrid = new char[gridDimension][gridDimension];
        this.gridDimension = gridDimension;
        this.score = 0;

        // initialize the board with *
        for (int i = 0; i < this.gridDimension; i++) {
            for (int j = 0; j < this.gridDimension; j++) {
                moleGrid[i][j] = '*';
            }
        }
    }

    public void setMolesLeft(int numMoles) {
        this.molesLeft = numMoles;
    }

    public void setScore(int score) {
        this.score = score;
    }

    boolean place(int x, int y) {
        if (moleGrid[x][y] == 'M') {
            return false;
        }
        else if (moleGrid[x][y] == '*') {
            moleGrid[x][y] = 'M';
            this.molesLeft++;
            return true;
        } else {
            System.out.println(moleGrid[x][y]);
        }
        return false;
    }

    void whack(int x, int y) {
        if (moleGrid[x][y] != 'M') {
            attemptsLeft -= 1;

            System.out.println();
            System.out.println("Sorry, there is no mole here.");
            System.out.println();
        }
        else if (moleGrid[x][y] == 'M') {
            moleGrid[x][y] = 'W'; // record for whacked mole
            this.score++;
            this.attemptsLeft--;
            this.molesLeft--;

            System.out.println();
            System.out.println("Congratulations! You whacked a mole!");
            System.out.println();
        }
    }

    void printGridToUser() {
        for (int i = 0; i < this.gridDimension; i++) {
            for (int j = 0; j < this.gridDimension; j++) {
                if (moleGrid[i][j] == 'M') {
                    System.out.print(" " + "*" + " ");
                } else {
                    System.out.print(" " + moleGrid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    void printGrid() {
        for (int i = 0; i < this.gridDimension; i++) {
            for (int j = 0; j < this.gridDimension; j++) {
                System.out.print(" " + moleGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    int randInt(int range) {
        Random rand = new Random();
        return rand.nextInt(range);
    }

    void randomAssignMoles(int numMoles, int range) {
        while (molesLeft < numMoles) {
            int x = randInt(range);
            int y = randInt(range);

            place(x, y);
        }
    }

    void notice() {
        System.out.println("There are " + this.molesLeft + " moles left in the field.");
        System.out.println("You have " + this.attemptsLeft + " attempts left to whack the moles.");
        System.out.println("You score is " + this.score);
        System.out.println();
        System.out.println("Please enter '-1 -1' if you wish to give up.");
        System.out.println("Please enter '-2 -2' if you wish to see the board.");
        System.out.println();
    }

    public static void main(String[] args) {
        // Game setup
        WhackAMole game = new WhackAMole(50, 10);
        game.setMolesLeft(0);
        game.randomAssignMoles(10, 10);

        Scanner scanner = new Scanner(System.in);

        while ((game.attemptsLeft > 0) && (game.molesLeft > 0)) {
            game.notice();
            System.out.println("Please provide the x and y coordinates to whack the mole: ");

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (x == -1 && y == -1) {
                game.printGrid();
                System.out.println();
                System.out.println("There are " + game.molesLeft + " moles left.");
                System.out.println("Sorry that you give up now!");
                System.out.println("Your final score is " + game.score);
                break;
            }

            else if (x == -2 && y == -2) {
                game.printGridToUser();
            }

            else if (x >= 10 || x < 0 || y >= 10 || y < 0) {
                System.out.println();
                System.out.println("Sorry the coordinates might be out of bound. Try again.");
                System.out.println();
            }

            else {
                game.whack(x, y);
            }
        }
    }
}
