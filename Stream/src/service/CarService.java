package service;

import model.Car;
import model.CarInfo;
import model.Owner;
import utils.Condition;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CarService {
    public List<String> getConditions(List<Car> cars) {
        return cars
                .stream()
                .map(car -> car.getCondition().getText())
                .toList();
    }

    public List<Car> getNewCars(List<Car> cars) {
        return cars
                .stream()
                .filter(car -> car.getCondition().equals(Condition.NEW))
                .toList();
    }

    public long countCarsOwners(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getOwners().size() > 2)
                .count();
    }

    public List<Car> incrementCarAge(List<Car> cars) {
        return cars.stream()
                .peek(car -> car.setAge(car.getAge()+1))
                .toList();
    }

    public Car getOldestCar(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparing(Car::getAge))
                .orElseThrow(RuntimeException::new);
    }

    public List<String> getOwnersCarsNames(List<Car> cars) {
        return cars.stream()
                .flatMap(car -> car.getOwners().stream())
                .map(Owner::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<CarInfo> mapToCarInfo(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarInfo(car.getName(), car.getAge(), car.getOwners().size()))
                .toList();
    }

    public List<Car> getTwoBrokenCar(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getCondition().equals(Condition.BROKEN))
                .limit(2)
                .toList();
    }

    public List<Car> getSortedCarsByAge(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getAge))
                .toList();
    }

    public double getAvgCarsAge(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getAge)
                .average()
                .orElseThrow();
    }

    public Boolean checkBrokenCarsAge(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getCondition().equals(Condition.BROKEN))
                .allMatch(car -> car.getAge() > 10);
    }

    public Boolean checkCarOwnerName(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getCondition().equals("USED"))
                .anyMatch(car -> car.getOwners().contains("Adam"));
    }

    public Owner getAnyOwner(List<Car> cars) {
        return cars.stream()
                .flatMap(car -> car.getOwners().stream())
                .filter(owner -> owner.getAge() > 36)
                .findAny()
                .orElse(null);
    }

}