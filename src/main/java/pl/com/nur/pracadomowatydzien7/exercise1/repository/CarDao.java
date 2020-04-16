package pl.com.nur.pracadomowatydzien7.exercise1.repository;

import pl.com.nur.pracadomowatydzien7.exercise1.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar(long carId, String mark, String model, String color, String dateProduction);

    List<Car> findAll();

    List<Car> getCarListFromDateRange(String dateMin, String dateMax);

    void updateCar(Car newCar);

    void deleteCar(long id);

    Car getOne(long id);


}
