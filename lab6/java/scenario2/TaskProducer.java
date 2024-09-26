import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class TaskProducer implements Runnable {
    private final BlockingQueue<Task> fila;
    private final int prioridade;
    private long intervalo;
    // private static final int TIME_SLEEP = 5000;

    public TaskProducer(BlockingQueue<Task> fila, int prioridade, long intervalo){
        this.fila = fila;
        this.prioridade = prioridade;
        this.intervalo = intervalo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                long id = new Random().nextLong();
                Task task = new Task(id, this.prioridade);
        
                fila.put(task);
                Thread.sleep(this.intervalo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
