import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Node implements Runnable {
    private final BlockingQueue<Task> fila;
    private List<Task> processedTasks;

    public Node(BlockingQueue<Task> fila){
      this.fila = fila;
      this.processedTasks = new ArrayList<>();
  }

    @Override
    public void run() {
      try {
        while (true) {
            Task task = fila.take();
            task.execute();
            processedTasks.add(task);

        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    public List<Task> getProcessedTasks() {
      return processedTasks;
  }
}
