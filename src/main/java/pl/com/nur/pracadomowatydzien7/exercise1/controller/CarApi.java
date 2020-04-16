package pl.com.nur.pracadomowatydzien7.exercise1.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.nur.pracadomowatydzien7.exercise1.model.Car;
import pl.com.nur.pracadomowatydzien7.exercise1.repository.CarDaoImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequestMapping("/vehicles")
public class CarApi {

    private CarDaoImpl carDaoImpl;
    private String dateMin= "0000-00-00";
    private String dateMax= "9999-12-31";

    public CarApi(CarDaoImpl carDaoImpl) {
        this.carDaoImpl = carDaoImpl;
    }

    @GetMapping
    public String getVehicles(Model model){
        model.addAttribute("cars", carDaoImpl.findAll());
        model.addAttribute("newCar", new Car());
        model.addAttribute("delCar", new Car());
        model.addAttribute("modCar", new Car());

        model.addAttribute("startDate", dateMin);
        model.addAttribute("endDate", dateMax);
        return "vehicle";
    }


    @PostMapping
    public String addVehicle(@ModelAttribute Car car){
        carDaoImpl.saveCar(car);
        return "redirect:/vehicles";
    }

    @PostMapping("/parametr")
    // http://localhost:8080/products/parametr?name=Wojtek&surname=Galach
    public String getVehiclesListFromDateRange(@RequestParam(name = "namestart") String dateMin,
                                               @RequestParam(name = "nameend") String dateMax,
                                               Model model){
        if(dateMin.isEmpty())
            dateMin = "0000-01-01";
        if(dateMax.isEmpty())
            dateMax = "9999-12-31";
//        System.out.println("przesylam parametry = " + dateMin + "  " + dateMax);
        model.addAttribute("cars", carDaoImpl.getCarListFromDateRange(dateMin, dateMax));
        return "vehicledate";
    }

}
