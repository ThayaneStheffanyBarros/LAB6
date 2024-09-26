import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class ScenarioBase {

    private static final int NUM_TASK_PRODUCER = 5;
    private static final int NUM_NODE = 3;


    public static void main(String[] args) {
        BlockingQueue<Task> fila = new LinkedBlockingDeque<Task>();
        ExecutorService consumidor = Executors.newCachedThreadPool();
        ExecutorService produtor = Executors.newFixedThreadPool(NUM_TASK_PRODUCER);


        for(int i = 0; i < NUM_TASK_PRODUCER; i++) {
            produtor.submit(new TaskProducer(fila));
        }

        for(int j = 0; j < NUM_NODE; j++) {
            consumidor.submit(new Node(fila));
        }
    }
}
