import java.util.Random;

public class Task {
    long id;
    long tempoInicial;
    long duracao;

    public Task(long id) {
        this.id = id;
        this.tempoInicial = System.currentTimeMillis();
    }

    public void execute() {
        try {
            
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
            this.duracao = System.currentTimeMillis() - this.tempoInicial;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
