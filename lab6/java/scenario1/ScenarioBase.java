import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class ScenarioBase {

    private final BlockingQueue<Task> filaDeTarefas;
    private final ExecutorService threadPool;
    private final List<TaskProducer> produtores;  // Lista para armazenar os produtores

    public ScenarioBase(int numProdutores, int numNos) {
        this.filaDeTarefas = new LinkedBlockingQueue<>();
        this.threadPool = Executors.newCachedThreadPool();  // Thread pool que lida com Produtores e Nós
        this.produtores = new ArrayList<>();  // Inicializa a lista de produtores

        // Inicia os produtores de tarefas
        for (int i = 0; i < numProdutores; i++) {
            TaskProducer producer = new TaskProducer(filaDeTarefas, i + 1);
            produtores.add(producer);  // Adiciona cada produtor à lista
            threadPool.submit(producer);
        }

        // Inicia os nós de processamento
        for (int i = 0; i < numNos; i++) {
            Node node = new Node(filaDeTarefas, i + 1);
            threadPool.submit(node);
        }
    }

    public void startScenario() {
        // Exibe o relatório de tarefas processadas a cada 30 segundos
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            System.out.println("----- Relatório do Sistema -----");
            // Chama o método tostring de cada produtor
            for (TaskProducer producer : produtores) {
                producer.tostring();
            }
        }, 0, 30, java.util.concurrent.TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ScenarioBase scenario = new ScenarioBase(5, 3);
        scenario.startScenario();
    }
}
