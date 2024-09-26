import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Node implements Runnable {
    private final BlockingQueue<Task> fila;
    private List<Long> tempoAtivo;
   
    public Node(BlockingQueue<Task> fila){
      this.fila = fila;
      this.tempoAtivo = new ArrayList<>();
  }

    @Override
    public void run() {
      try {
        while (true) {
            Task task = fila.take();
            task.execute();
            long tempoTotal = task.getActiveTime(); 
            tempoAtivo.add(tempoTotal);
          

        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    public double getTempoMedio() {
      return tempoAtivo.stream().mapToLong(Long::longValue).average().orElse(0);
  }

  public List<Long> getTarefaProcessada() {
      return new ArrayList<>(tempoAtivo);
}
}
