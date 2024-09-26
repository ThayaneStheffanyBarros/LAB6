import java.util.Random;
public class Task {
    long id;
    long tempoCriacao;

    public Task(long id) {
        this.id = id;
        this.tempoCriacao = System.currentTimeMillis();
    }

    public void execute() {
        try {
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
            this.tempoCriacao = System.currentTimeMillis() - tempoCriacao;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public long getTotalTime() {
        return System.currentTimeMillis() - this.tempoCriacao;
    }
}
