import java.util.Random;

public class Task {
    long id;
    long tempoInicial;
    long duracao;
    TaskProducer producer;

    public Task(long id, TaskProducer producer) {
        this.id = id;
        this.tempoInicial = System.currentTimeMillis();
        this.producer = producer;
    }

    public void execute() {
        try {
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
            this.duracao = System.currentTimeMillis() - this.tempoInicial;
            producer.addTask(duracao, id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();

        }
    }
}
