import java.util.concurrent.BlockingQueue;

public class Node implements Runnable {
  private final BlockingQueue<Task> fila;
  private final int id;

  public Node(BlockingQueue<Task> fila, int id) {
      this.fila = fila;
      this.id = id;
  }

  @Override
  public void run() {
      try {
          while (true) {
              // Pega a tarefa da fila, se não houver tarefa, a thread fica bloqueada
              Task task = fila.take();
              System.out.println("Nó " + id + " processando tarefa " + task.id);
              task.execute();  // Processa a tarefa
              System.out.println("Nó " + id + " completou a tarefa " + task.id);
          }
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt();  // Interrompe a thread corretamente
          System.out.println("Nó " + id + " interrompido.");
      }
  }
}
