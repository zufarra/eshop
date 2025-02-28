package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CarTest {
    private Car car;

    @BeforeEach
    void setup() {
        car = new Car();
        car.setCarId("a1b2c3d4-e5f6-7890-1234-abcdefabcdef");
        car.setCarName("Nissan GT-R");
        car.setCarColor("Blue");
        car.setCarQuantity(10);
    }

    @Test
    void testGetCarName() {
        assertEquals("Nissan GT-R", car.getCarName());
    }

    @Test
    void testSetCarName() {
        car.setCarName("Honda Civic");
        assertEquals("Honda Civic", car.getCarName());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Blue", car.getCarColor());
    }

    @Test
    void testSetCarColor() {
        car.setCarColor("Black");
        assertEquals("Black", car.getCarColor());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(10, car.getCarQuantity());
    }

    @Test
    void testSetCarQuantity() {
        car.setCarQuantity(20);
        assertEquals(20, car.getCarQuantity());
    }
}
