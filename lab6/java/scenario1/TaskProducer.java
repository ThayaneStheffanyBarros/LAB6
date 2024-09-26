import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class TaskProducer implements Runnable {
    private final BlockingQueue<Task> fila;
    private static final int TIME_SLEEP = 5000;

    public TaskProducer(BlockingQueue<Task> fila){
        this.fila = fila;
    }

    @Override
    public void run() {
        try{
            while(true) {
                long id = new Random().nextLong();
                Task task = new Task(id);
                fila.put(task);
                Thread.sleep(TIME_SLEEP);
            }

        } catch(InterruptedException e){
            Thread.currentThread().interrupt();

        }
    }
}