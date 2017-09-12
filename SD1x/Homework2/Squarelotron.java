/*
 * Created by nahua on 2017-09-08
 * PennX: SD1x Software Development Fundamentals
 * Homework Assignment 2: Squarelotron
 * Implement the following methods with Test-Driven Development:
 * - Squarelotron upsideDownFlip(int ring)
 * - Squarelotron mainDiagonalFlip(int ring)
 * - void rotateRight(int numOfTurns)
 */


public class Squarelotron {
    
    // instance variables
    int[][] squarelotron;
    int size;

    
    // constructor with parameter (int n)
    public Squarelotron(int n) {
        this.size = n;
        this.squarelotron = new int[n][n];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                squarelotron[i][j] = i * size + j + 1;
            }
        }
    }
    
    
    // constructor with parameter (int[] array)
    public Squarelotron(int[] array) {
        this.size = (int) Math.sqrt(array.length);
        this.squarelotron = new int[size][size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squarelotron[i][j] = array[k];
                k++;
            }
        }
    }
    
    
    // return 2d squarelotron as an 1d array
    public int[] matrix2vec() {
        int[] arrayCopy = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arrayCopy[i * size + j] = squarelotron[i][j];
            }
        }
        return arrayCopy;
    }

    
    Squarelotron upsideDownFlip(int ring) {
        int[] flipped = new int[size * size];
        int[] original = matrix2vec();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == ring - 1 || i == size - ring) && (j >= ring - 1 && j <= size - ring) || (j == ring - 1 || j == size - ring) && (i >= ring - 1 && i <= size - ring)) {
                    flipped[i * size + j] = original[(size - i - 1) * size + j];
                } else {
                    flipped[i * size + j] = original[i * size + j];
                }
            }
        }
        Squarelotron flippedSquarelotron = new Squarelotron(flipped);
        return flippedSquarelotron;
    }

    
    Squarelotron mainDiagonalFlip(int ring) {
        int[] diagonal = new int[size * size];
        int[] original = matrix2vec();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == ring - 1 || i == size - ring) && (j >= ring - 1 && j <= size - ring) || (j == ring - 1 || j == size - ring) && (i >= ring - 1 && i <= size - ring)) {
                    diagonal[i * size + j] = original[j * size + i];
                } else {
                    diagonal[i * size + j] = original[i * size + j];
                }
            }
        }
        Squarelotron diagonalFlipped = new Squarelotron(diagonal);
        return diagonalFlipped;
    }

    
    public void rotateRightOnce(){
        for(int k = 0; k < size/2; k++){
            int[] savedCopy = new int[size - 2 * k];
            int n = 0;
            for(int j = k; j < size - k; j++){
                savedCopy[n] = squarelotron[k][j];
                n++;
            }
            for(int j = k; j < size - k; j++){
                squarelotron[k][j] = squarelotron[size - 1 - j][k];
            }
            for(int i = k; i < size - k; i++){
                squarelotron[i][k] = squarelotron[size - 1 - k][i];
            }
            for(int j = k; j < size - k; j++){
                squarelotron[size - 1 - k][j] = squarelotron[size - 1 - j][size - 1 - k];
            }
            n = 0;
            for(int i = k; i < size - k; i++){
                squarelotron[i][size - 1 - k] = savedCopy[n];
                n++;
            }
        }
    }


    public void rotateRight(int numberOfTurns) {
        if(size == 1){
            squarelotron[0][0] = squarelotron[0][0];
        }
        else if(numberOfTurns % 4 == 1 || numberOfTurns % 4 == -3){
            rotateRightOnce();
        }
        else if(numberOfTurns % 4 == 2 || numberOfTurns % 4 == -2){
            rotateRightOnce();
            rotateRightOnce();
        }
        else if(numberOfTurns % 4 == 3 || numberOfTurns % 4 == -1){
            rotateRightOnce();
            rotateRightOnce();
            rotateRightOnce();
        }
        else{
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    squarelotron[i][j] = squarelotron[i][j];
                }
            }
        }
    }
    
    public void printMatrix() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(" " + squarelotron[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Squarelotron a = new Squarelotron(5);
        a.printMatrix();
        Squarelotron d = a.upsideDownFlip(2);
        d.printMatrix();
    }
}
