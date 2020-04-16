package pl.com.nur.pracadomowatydzien7.exercise1.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.nur.pracadomowatydzien7.exercise1.model.Car;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;


    private String dateMin; //= "0000-00-00";
    private String dateMax; //= "9999-12-31";

    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        saveCar(1L, "Volvo", "S60", "Srebrny", "2000-02-29");
//        saveCar(2L, "Volvo", "850", "Czerwony", "1995-06-14");
//        saveCar(3L, "Volvo", "244", "Czarny", "1977-12-12");
//        findAll().stream().forEach(System.out::println);
//        getCarListFromDateRange("1980-01-01", "2000-02-02").stream().forEach(System
//          .out::println);
    }

    @Override
    public void saveCar(long carId, String mark, String model, String color, String dateProduction) {
        Car car = new Car(carId, mark, model, color, dateProduction);
        String sql = "INSERT INTO cars VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, car.getCarId(), car.getMark(), car.getModel(), car.getColor(), car.getDateProduction());
    }

    public void saveCar(Car car) {
     //   Car car = new Car(carId, mark, model, color, dateProduction);
        String sql = "INSERT INTO cars VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, car.getCarId(), car.getMark(), car.getModel(), car.getColor(), car.getDateProduction());
    }


    @Override
    public List<Car> findAll() {
        String sql=  "SELECT * FROM cars";
        List<Car> carList = new ArrayList<>();
        // E07S06
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach((element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                String.valueOf(element.get("date_production"))
        ))));
        return carList;
    }

    @Override
    public List<Car> getCarListFromDateRange(String dateMin, String dateMax) {
        String sql=  "SELECT * FROM cars WHERE date_production BETWEEN " + dateMin + " AND " + dateMax;
        List<Car> carList = new ArrayList<>();
        // E07S06
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach((element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                String.valueOf(element.get("date_production"))
        ))));
//        System.out.println(carList);
        return carList;
    }


    public List<Car> getCarListFromDateRange() {
        String sql=  "SELECT * FROM cars WHERE date_production BETWEEN " + dateMin + " AND " + dateMax;
        List<Car> carList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach((element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                String.valueOf(element.get("date_production"))
        ))));
//        System.out.println(carList);
        return carList;
    }

    @Override
    public void updateCar(Car newCar) {   // pozwalamy tylko przemalowac :)
        String sql = "UPDATE cars SET cars.color=? where cars.video_id=?";
        jdbcTemplate.update(sql, newCar.getColor(),  newCar.getCarId());
    }

    @Override
    public void deleteCar(long id) {
        String sql = "DELETE FROM cars  WHERE cars.video_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Car getOne(long id) {
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        // kolumny numerujemy od 1 lub po nazwach
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Car(rs.getLong(1),
                rs.getString("mark"), rs.getString("model"),
                rs.getString("color"), rs.getString("data_production")), id);
    }
}
