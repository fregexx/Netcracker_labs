package core;

public class MyRunnable implements Runnable {

    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] result;
    private int firstIndex;
    private int lastIndex;

    public MyRunnable(int[][] matrixA, int[][] matrixB, int[][] result, int firstIndex, int lastIndex) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    @Override
    public void run() {
        System.out.println("Thread [ " + firstIndex + " - " + lastIndex + "] started");
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixB.length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        System.out.println("Thread [ " + firstIndex + " - " + lastIndex + "] finished");
    }
}
