public class Main {

    public static void main(String[] args) throws InterruptedException {
        final ProducerConsumer produceConsume = new ProducerConsumer();
        final int timeUntilTheEnd = 1_000;

        ThreadGroup group = new ThreadGroup("Группа");

        while (produceConsume.howManyCarsBuy < 5) {

            new Thread(group, () -> {
                try {
                    produceConsume.concume();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "one").start();

            new Thread(group, () -> {
                try {
                    produceConsume.concume();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "two").start();

            new Thread(group, () -> {
                try {
                    produceConsume.concume();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "three").start();

            new Thread(group, () -> {
                try {
                    produceConsume.produce();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "Toyota").start();

        }
        Thread.sleep(timeUntilTheEnd);
        group.interrupt();
    }
}


