import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    private List<Car> carForSale = new ArrayList<>();
    private final int LIMIT = 1;
    private final int sleepTimeForProduce = 1_000;
    private final int sleepTimeForConcume = 500;

    Car car = new Car("Toyota");
    int howManyCarsBuy = 0;

    public void produce() throws InterruptedException {
        synchronized (car) {
            while (carForSale.size() == LIMIT) {
                car.wait();
            }
            Thread.sleep(sleepTimeForProduce);
            carForSale.add(car);
            System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил " + carForSale.size() + " авто");
            car.notify();
        }
    }

    public void concume() throws InterruptedException {
        synchronized (car) {
            Thread.sleep(sleepTimeForConcume);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " вошел в салон");
            if (carForSale.size() == 0) {
                System.out.println("Машины нет");
                car.wait();
            }
            Thread.sleep(sleepTimeForConcume);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новом авто");
            howManyCarsBuy++;
            carForSale.clear();
            car.notify();
        }
    }
}

