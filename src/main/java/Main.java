public class Main {

    public static void main(String[] args)  {
        final ProducerConsumer produceConsume = new ProducerConsumer();
        final int ALL_LIMIT = 5; // салон должен выпустить максимум 5 авто

        ThreadGroup group = new ThreadGroup("Группа");

        while (produceConsume.howManyCarsBuy < ALL_LIMIT) {
            new Thread(group, () -> {
                try {
                    produceConsume.concumeCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "one").start();

            new Thread(group, () -> {
                try {
                    produceConsume.concumeCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "two").start();

            new Thread(group, () -> {
                try {
                    produceConsume.concumeCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "three").start();

            new Thread(group, () -> {
                try {
                    produceConsume.produceCar();
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                }
            }, "Toyota").start();
        }
        group.interrupt();
    }
}
