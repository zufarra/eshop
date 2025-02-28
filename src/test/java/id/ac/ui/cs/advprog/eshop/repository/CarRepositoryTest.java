package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class CarRepositoryTest extends BaseRepositoryTest<Car> {
    @Override
    protected BaseRepository<Car> createRepository() {
        return new CarRepository();
    }

    @Override
    protected Car createEntity() {
        Car car = new Car();
        car.setId(UUID.randomUUID().toString());
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        return car;
    }

    @Test
    void testUpdateCar() {
        Car car = createEntity();
        car = repository.create(car);

        Car updatedCar = new Car();
        updatedCar.setId(car.getId()); // ID harus sama agar update berhasil
        updatedCar.setCarName("Nissan GT-R");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        Car result = repository.update(car.getId(), updatedCar);
        assertNotNull(result);
        assertEquals("Nissan GT-R", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(5, result.getCarQuantity());
    }

    @Test
    void testUpdateCarNotExist() {
        Car updatedCar = new Car();
        updatedCar.setId("non-existent-id");
        updatedCar.setCarName("Honda Civic");
        updatedCar.setCarColor("Black");
        updatedCar.setCarQuantity(15);

        Car result = repository.update("non-existent-id", updatedCar);
        assertNull(result);
    }
}
