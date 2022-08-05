public class Main {

    public static void main(String[] args) {

        final ProducerConsumer produceConsume = new ProducerConsumer();
        final int sleepTime = 500;

        ThreadGroup group = new ThreadGroup("Группа");
        
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
                Thread.sleep(sleepTime);
                produceConsume.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }
        }, "Toyota").start();
    }
}




