/*
 * Created by nahua on 2017-09-08
 * PennX: SD1x Software Development Fundamentals
 * Homework Assignment 2: Squarelotron
 * Implement the following methods with Test-Driven Development:
 * - Squarelotron upsideDownFlip(int ring)
 * - Squarelotron mainDiagonalFlip(int ring)
 * - void rotateRight(int numOfTurns)
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquarelotronTest {

    Squarelotron test1, test2;
    
    @Before
    public void setUp() throws Exception {
        test1 = new Squarelotron(4);
        int[] testarray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        test2 = new Squarelotron(testarray);
    }

    
    // testing 2 constructors
    @Test
    public void testSquarelotron() {
        // check test1 setup
        int[][] check1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        assertEquals(4, test1.size);
        assertArrayEquals(check1[0], test1.squarelotron[0]);
        assertArrayEquals(check1[1], test1.squarelotron[1]);
        assertArrayEquals(check1[2], test1.squarelotron[2]);
        assertArrayEquals(check1[3], test1.squarelotron[3]);
        // check test2 setup
        int[][] check2 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        assertEquals(5, test2.size);
        assertArrayEquals(check2[0], test2.squarelotron[0]);
        assertArrayEquals(check2[1], test2.squarelotron[1]);
        assertArrayEquals(check2[2], test2.squarelotron[2]);
        assertArrayEquals(check2[3], test2.squarelotron[3]);
        assertArrayEquals(check2[4], test2.squarelotron[4]);
    }

    
    // testing method matrix2Vec()
    @Test
    public void testMatrix2Vec() {
        int[] checking1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        int[] checking2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        assertArrayEquals(checking1, test1.matrix2vec());
        assertArrayEquals(checking2, test2.matrix2vec());
    }
    
    
    @Test
    public void testUpsideDownFlip() {
        int[] flipped1 = {13,14,15,16,9,6,7,12,5,10,11,8,1,2,3,4}; // flipped on ring 1
        Squarelotron check1 = new Squarelotron(flipped1);
        assertArrayEquals(check1.squarelotron[0], test1.upsideDownFlip(1).squarelotron[0]);
        assertArrayEquals(check1.squarelotron[1], test1.upsideDownFlip(1).squarelotron[1]);
        assertArrayEquals(check1.squarelotron[2], test1.upsideDownFlip(1).squarelotron[2]);
        assertArrayEquals(check1.squarelotron[3], test1.upsideDownFlip(1).squarelotron[3]);
    }

    
    @Test
    public void testMainDiagonalFlip() {
        int[] diagonal1 = {1,5,9,13,2,6,7,14,3,10,11,15,4,8,12,16};
        Squarelotron check1 = new Squarelotron(diagonal1);
        assertArrayEquals(check1.squarelotron[0], test1.mainDiagonalFlip(1).squarelotron[0]);
        assertArrayEquals(check1.squarelotron[1], test1.mainDiagonalFlip(1).squarelotron[1]);
        assertArrayEquals(check1.squarelotron[2], test1.mainDiagonalFlip(1).squarelotron[2]);
        assertArrayEquals(check1.squarelotron[3], test1.mainDiagonalFlip(1).squarelotron[3]);
    }

    
    @Test
    public void testRotateOnce() {
        int[] rotate1 = {13,9,5,1,14,10,6,2,15,11,7,3,16,12,8,4};
        Squarelotron check1 = new Squarelotron(rotate1);
        test1.rotateRightOnce();
        assertArrayEquals(check1.squarelotron[0], test1.squarelotron[0]);
        assertArrayEquals(check1.squarelotron[1], test1.squarelotron[1]);
        assertArrayEquals(check1.squarelotron[2], test1.squarelotron[2]);
        assertArrayEquals(check1.squarelotron[3], test1.squarelotron[3]);
    }
    
    
    @Test
    public void testRotateRight() {
        int[] rotate0 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        Squarelotron check0 = new Squarelotron(rotate0);
        // rotate by 0
        test2.rotateRight(0);
        assertArrayEquals(check0.squarelotron[0], test2.squarelotron[0]);
        assertArrayEquals(check0.squarelotron[1], test2.squarelotron[1]);
        assertArrayEquals(check0.squarelotron[2], test2.squarelotron[2]);
        assertArrayEquals(check0.squarelotron[3], test2.squarelotron[3]);
        assertArrayEquals(check0.squarelotron[4], test2.squarelotron[4]);
        // rotate by 4
        test2.rotateRight(4);
        assertArrayEquals(check0.squarelotron[0], test2.squarelotron[0]);
        assertArrayEquals(check0.squarelotron[1], test2.squarelotron[1]);
        assertArrayEquals(check0.squarelotron[2], test2.squarelotron[2]);
        assertArrayEquals(check0.squarelotron[3], test2.squarelotron[3]);
        assertArrayEquals(check0.squarelotron[4], test2.squarelotron[4]);
        // rotate by -4
        test2.rotateRight(-4);
        assertArrayEquals(check0.squarelotron[0], test2.squarelotron[0]);
        assertArrayEquals(check0.squarelotron[1], test2.squarelotron[1]);
        assertArrayEquals(check0.squarelotron[2], test2.squarelotron[2]);
        assertArrayEquals(check0.squarelotron[3], test2.squarelotron[3]);
        assertArrayEquals(check0.squarelotron[4], test2.squarelotron[4]);
    }

}
