import java.util.Random;
public class Task implements Comparable<Task> {
    private final long id;
    private final int prioridade;
    private final long tempoCriacao;

    public Task(long id, int prioridade) {
        this.id = id;
        this.prioridade = prioridade;
        this.tempoCriacao = System.currentTimeMillis();
    }

    public void execute() {
        try {
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }


    public int getPrioridade() {
        return this.prioridade;
    }

    public long getActiveTime() {
        return System.currentTimeMillis() - tempoCriacao;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.prioridade, other.prioridade);
    }

}
