package pl.com.nur.pracadomowatydzien7.exercise1.model;


public class Car {

  private long carId;
  private String mark;
  private String model;
  private String color;
  private String dateProduction;

  public Car() {
  }

  public Car(long carId, String mark, String model, String color, String dateProduction) {
    this.carId = carId;
    this.mark = mark;
    this.model = model;
    this.color = color;
    this.dateProduction = dateProduction;
  }

  public long getCarId() {
    return carId;
  }

  public void setCarId(long carId) {
    this.carId = carId;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public String getDateProduction() {
    return dateProduction;
  }

  public void setDateProduction(String dateProduction) {
    this.dateProduction = dateProduction;
  }

  @Override
  public String toString() {
    return "Car{" +
            "carId=" + carId +
            ", mark='" + mark + '\'' +
            ", model='" + model + '\'' +
            ", color='" + color + '\'' +
            ", dateProduction='" + dateProduction + '\'' +
            '}';
  }
}
