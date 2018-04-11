package core;

import util.Utils;

public class Main {
    private static final int ROWS_A = 1000;
    private static final int COLS_A = 1000;
    private static final int ROWS_B = COLS_A;
    private static final int COLS_B = 1000;
    private static int THREADS_COUNT = 1;
    private static long startTime;
    private static long endTime;

    public static void main(String[] args) throws InterruptedException {

        startTime = System.nanoTime();

        int[][] matrixA = Utils.getRandomMatrix(ROWS_A, COLS_A);
        int[][] matrixB = Utils.getRandomMatrix(ROWS_B, COLS_B);

        int[][] result = new int[matrixA.length][matrixB[0].length];

        if (THREADS_COUNT > ROWS_A) {
            THREADS_COUNT = ROWS_A;
        }

        int rowsForThread = ROWS_A / THREADS_COUNT;
        int remainedRows = ROWS_A % THREADS_COUNT;

        int firstIndex = 0;
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            int lastIndex = firstIndex + rowsForThread - 1;
            if (i == 0) lastIndex += remainedRows;
            threads[i] = new Thread(new MyRunnable(matrixA, matrixB, result, firstIndex, lastIndex));
            threads[i].start();
            firstIndex = lastIndex + 1;
        }

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i].join();
        }

        endTime = System.nanoTime();

        System.out.println("Duration: " + (endTime - startTime));

        //Utils.printMatrix(result);
    }
}


