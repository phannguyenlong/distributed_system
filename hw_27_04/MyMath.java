package hw_27_04;

public class MyMath implements Runnable {
    private int n, sum;

    public MyMath(int n) {
        this.n = n;
    }

    public int getSum() {
        return sum;
    }
    
    @Override
    public void run() {
        sum = (n + 1) * (n / 2);
        System.out.println("Sum is: " + sum);
    }

    public static void main(String[] args) {
        MyMath mm = new MyMath(10);
        Thread worker = new Thread(mm);
        
        worker.start();
        try {
            worker.join(); // wait until worker finish then continue run
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 

        System.out.println("Final is: " + (mm.getSum() + 1));
    }
    
}
