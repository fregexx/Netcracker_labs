package core;

public class MatrixMultiplier {

    private static long startTime;
    private static long endTime;
    private static int THREADS_COUNT = 4;

    public static int[][] multiply(int[][] matrixA, int[][] matrixB){
        startTime = System.nanoTime();
        int[][] result = new int[matrixA.length][matrixB[0].length];

        if (THREADS_COUNT > matrixA.length) {
            THREADS_COUNT = matrixA.length;
        }

        int rowsForThread = matrixA.length / THREADS_COUNT;
        int remainedRows = matrixA.length % THREADS_COUNT;

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
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endTime = System.nanoTime();
        System.out.println("Duration: " + (endTime - startTime));
        return result;
    }
}
