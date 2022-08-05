import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private static final Lock lock = new ReentrantLock(true);
    private final Condition conditionMake = lock.newCondition(); // ожидаемое событие - изготовление авто
    private final Condition conditionBay = lock.newCondition(); // ожидаемое событие - покупка машины

    private List<Car> carForSale = new ArrayList<>();
    private final int LIMIT = 1;
    private final int sleepTimeForProduce = 2_000;
    private final int sleepTimeForConcume = 1_000;
    int howManyCarsBuy = 0;

    public void produce() throws InterruptedException {
        try {
            lock.lock();
            while (howManyCarsBuy < 3) {
                while (carForSale.size() == LIMIT) {
                    conditionBay.await();
                }
                Thread.sleep(sleepTimeForProduce);
                carForSale.add(new Car("Toyota"));
                System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил " + carForSale.size() + " авто");
                conditionMake.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void concume() throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(sleepTimeForConcume);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " вошел в салон");
            if (carForSale.size() == 0) {
                System.out.println("Машины нет");
            }
            conditionMake.await();

            Thread.sleep(sleepTimeForConcume);
            howManyCarsBuy++;
            System.out.print("Покупатель " + Thread.currentThread().getName() + " уехал на новом авто. ");
            System.out.println("Куплено " + howManyCarsBuy + " авто");
            System.out.println("Остальные ждут");
            carForSale.clear();
            conditionBay.signal();
        } finally {
            lock.unlock();
        }
    }
}

