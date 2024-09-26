import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ScenarioBase {

    private static final int NUM_TASK_PRODUCER = 5;
    private static final int NUM_NODE = 3;


    public static void main(String[] args) {
        BlockingQueue<Task> fila = new LinkedBlockingDeque<Task>();
        ExecutorService ex = Executors.newCachedThreadPool();
        ExecutorService ex2 = Executors.newFixedThreadPool(NUM_TASK_PRODUCER);
        
    }
}
