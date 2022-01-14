public class SolveTowerOfHanoi {

    public static void towerOfHanoi(int n,String source, 
    String auxillary, String destination) {
        if (n == 0) return;
        towerOfHanoi(n - 1, source, destination, auxillary);
        towerOfHanoi(n - 1, auxillary, source, destination);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            long startTime = System.nanoTime();
            towerOfHanoi(i, "Source", "Destination", "Auxillary");
            System.out.println( i + " Disk - Time Taken: "
                    + (System.nanoTime() - startTime));
        }
    }
}

