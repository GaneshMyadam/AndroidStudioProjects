import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PrCon {
    public static void main(String[] args) throws Exception {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

       // Producer producer = new Producer(queue);
      //  Consumer consumer = new Consumer(queue);

       // new Thread(producer).start();
       // new Thread(consumer).start();
System.out.println("hello");
        Thread.sleep(4000);
    }
}
