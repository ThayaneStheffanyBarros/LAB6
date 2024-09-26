import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class TaskProducer implements Runnable {
    private final BlockingQueue<Task> fila;
    private static final int TIME_SLEEP = 5000;
    private final int id;
    private String tasksConcluidas;
    private int totalConcluido;
    private long tempoTotal;

    public TaskProducer(BlockingQueue<Task> fila, int id){
        this.fila = fila;
        this.id = id;
        this.tasksConcluidas = "";
        this.totalConcluido = 0;
        this.tempoTotal = 0;
    }

    @Override
    public void run() {
        try{
            while(true) {
                long id = new Random().nextLong();
                Task task = new Task(id, this);
                fila.put(task);
                Thread.sleep(TIME_SLEEP);
            }

        } catch(InterruptedException e){
            Thread.currentThread().interrupt();

        }
    }

    public void tostring() {
        System.out.println("Produtor " + (this.id));
        System.out.println(this.tasksConcluidas);
        if (totalConcluido > 0) {
            System.out.println("Tempo médio: " + (tempoTotal / totalConcluido) + " ms");
        } else {
            System.out.println("Nenhuma tarefa concluída.");
        }
    }

    public void addTask(long tempo, long idTask) {
        this.totalConcluido += 1;
        this.tempoTotal += tempo;
        this.tasksConcluidas += "task " + idTask + " concluída em: " + tempo + "ms\n";
    }
}