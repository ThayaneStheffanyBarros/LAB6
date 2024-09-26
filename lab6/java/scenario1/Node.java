import java.util.concurrent.BlockingQueue;

public class Node implements Runnable {
    private final BlockingQueue<Task> fila;

    public Node(BlockingQueue<Task> fila){
      this.fila = fila;
  }

    @Override
    public void run() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'run'");  
    }
}
