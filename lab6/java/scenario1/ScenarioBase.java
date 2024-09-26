import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScenarioBase {

    private static final int NUM_TASK_PRODUCER = 5;
    private static final int NUM_NODE = 3;


    public static void main(String[] args) {
        BlockingQueue<Task> fila = new LinkedBlockingDeque<Task>();
        ExecutorService consumidor = Executors.newCachedThreadPool();
        ExecutorService produtor = Executors.newFixedThreadPool(NUM_TASK_PRODUCER);
         List<Node> nodes = new ArrayList<>();

        for(int i = 0; i < NUM_TASK_PRODUCER; i++) {
            produtor.execute(new TaskProducer(fila));
        }

        for(int j = 0; j < NUM_NODE; j++) {
            Node node = new Node(fila);
            nodes.add(node);
            consumidor.execute(node);
        }

        ScheduledExecutorService monitor = Executors.newScheduledThreadPool(1);
        monitor.scheduleAtFixedRate(() -> {
            for (int i = 0; i < nodes.size(); i++) {
                List<Task> processedTasks = nodes.get(i).getProcessedTasks();
                System.out.println("Relatório de Tarefas do Nó " + (i + 1) + ":");
                long totalActiveTime = 0;
                for (Task task : processedTasks) {
                    long activeTime = task.getTotalTime();
                    totalActiveTime += activeTime;
                    System.out.println("Tarefa " + task.getId() + " esteve ativa por " + activeTime + " ms");
                }
                if (!processedTasks.isEmpty()) {
                    System.out.println("Tempo médio de execução: " + (totalActiveTime / processedTasks.size()) + " ms");
                } else {
                    System.out.println("Nenhuma tarefa processada.");
                }
            }
        }, 5, 5, TimeUnit.SECONDS);
    }
}