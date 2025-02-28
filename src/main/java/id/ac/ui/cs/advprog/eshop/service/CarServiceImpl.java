package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends BaseService<Car> implements CarService {
    public CarServiceImpl(CarRepository repository) {
        super(repository);
    }
}
