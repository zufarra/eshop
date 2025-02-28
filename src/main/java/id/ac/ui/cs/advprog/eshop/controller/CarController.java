package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController<Car> {
    public CarController(CarService service) {
        super(service);
    }

    @Override
    protected String getBaseUrl() {
        return "car";
    }

    @Override
    protected String getCreateView() {
        return "CreateCar";
    }

    @Override
    protected String getListView() {
        return "CarList";
    }

    @Override
    protected String getEditView() {
        return "EditCar";
    }

    @Override
    protected Car createEntity() {
        return new Car();
    }
}
