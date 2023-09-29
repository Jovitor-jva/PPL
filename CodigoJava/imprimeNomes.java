package CodigoJava;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class imprimeNomes {

    public static void main(String[] args) {
        List<String> nomes = List.of(
                "Adson", "Gabriel", "Siqueira", "Ronaldo", "Gleilson",
                "Emerson", "Joselito", "Piloto", "Kleber", "Mauricio");

        int pool = 4;
        ExecutorService executor = Executors.newFixedThreadPool(pool);
        long inicialização = System.currentTimeMillis();

        for (String nome : nomes) {
            executor.submit(() -> imprimeNomes(nome));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException execution) {
            execution.printStackTrace();
        }

        long finalização = System.currentTimeMillis();
        long tempoDeExecucaoDaPool = finalização - inicialização;

        System.out.println("tempo de execução da tradução: " + tempoDeExecucaoDaPool);
    }

    private static void imprimeNomes(String nome) {
        try {
            Random rand = new Random();
            int delay = rand.nextInt(3000) + 1000;
            Thread.sleep(delay);
            System.out.println("Meu nome é: " + nome);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
