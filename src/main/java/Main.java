public class Main {

    static int ALL_LIMIT = 5; // салон должен выпустить максимум 5 авто

    public static void main(String[] args) throws InterruptedException {
        final ProducerConsumer produceConsume = new ProducerConsumer();
        final int sleepTimeForThreads = 1_000;

        ThreadGroup group = new ThreadGroup("Группа");

        new Thread(group, () -> {
            try {
                produceConsume.produceCar();
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }
        }, "Toyota").start();

        Thread.sleep(sleepTimeForThreads);

        while (produceConsume.howManyCarsBuy < ALL_LIMIT) {
            new Thread(group, () -> {
                try {
                    produceConsume.concumeCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "one").start();

            Thread.sleep(sleepTimeForThreads);

            new Thread(group, () -> {
                try {
                    produceConsume.concumeCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "two").start();

            Thread.sleep(sleepTimeForThreads);

            new Thread(group, () -> {
                try {
                    produceConsume.concumeCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "three").start();
        }
        group.interrupt();
    }
}