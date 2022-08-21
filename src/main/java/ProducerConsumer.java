import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    private List<Car> carForSale = new ArrayList<>();
    private final int LIMIT = 1; // салон выпускае за раз 1 авто
    private final int sleepTimeForProduce = 3_000;
    private final int sleepTimeForConcume = 500;

    Car car = new Car("Toyota");
    int howManyCarsBuy = 0;

    public synchronized void produceCar() throws InterruptedException {
        while (carForSale.size() < Main.ALL_LIMIT) {
            Thread.sleep(sleepTimeForProduce);
            carForSale.add(car);
            System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил " + carForSale.size() + " авто");
            wait(sleepTimeForConcume);
            notify();
        }
        wait();
    }

    public synchronized void concumeCar() throws InterruptedException {
        Thread.sleep(sleepTimeForConcume);
        System.out.println("Покупатель " + Thread.currentThread().getName() + " вошел в салон");

        if (carForSale.size() == 0) {
            System.out.println("Машины нет");
            wait();
        }
        Thread.sleep(sleepTimeForConcume);
        howManyCarsBuy++;
        System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новом авто");
        System.out.println("Куплено " + howManyCarsBuy + " авто");

        try {
            carForSale.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Внимание, машин больше не будет");
        }
    }
}