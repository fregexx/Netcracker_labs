package core;

import util.Utils;

public class Main {
    private static final int ROWS_A = 10;
    private static final int COLS_A = 10;
    private static final int ROWS_B = COLS_A;
    private static final int COLS_B = 10;

    public static void main(String[] args) {

        int[][] matrixA = Utils.getRandomMatrix(ROWS_A, COLS_A);
        int[][] matrixB = Utils.getRandomMatrix(ROWS_B, COLS_B);

        MatrixMultiplier matrixMultiplier = new MatrixMultiplier();
        int[][] result = matrixMultiplier.multiply(matrixA, matrixB);

        Utils.printMatrix(result);
    }
}


