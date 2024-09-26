import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScenarioBase {

    private static final int NUM_TASK_PRODUCER = 3;
    private static final int NUM_NODE = 3;


    public static void main(String[] args) {

        BlockingQueue<Task> fila = new ArrayBlockingQueue<>(10);
        ExecutorService produtor = Executors.newFixedThreadPool(NUM_TASK_PRODUCER);
        ExecutorService consumidor = Executors.newFixedThreadPool(NUM_NODE);

        Node[] nodes = new Node[3];
    
        produtor.submit(new TaskProducer(fila, 0, 13000));
        produtor.submit(new TaskProducer(fila, 1, 7000));
        produtor.submit(new TaskProducer(fila, 2, 3000));

        for (int i = 0; i < 3; i++) {
            nodes[i] = new Node(fila);
            consumidor.submit(nodes[i]);
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Exibindo estatísticas...");
                    for (int i = 0; i < nodes.length; i++) {
                        System.out.println("Node " + i + ":");
                        List<Long> activeTimes = nodes[i].getTarefaProcessada();
                        for (Long activeTime : activeTimes) {
                            System.out.println(" - Tarefa ID " + (activeTimes.indexOf(activeTime) + 1) + 
                                " esteve ativa por " + activeTime + " ms");
                        }
                        System.out.printf(" - Tempo médio ativo: %.2f ms%n", nodes[i].getTempoMedio());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            produtor.shutdown();
            consumidor.shutdown();
        }));
    }
}