package com.me.service;

import com.me.dao.CarDao;
import com.me.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    public void saves(List<Car> carList){
        for (Car car:carList){
            carDao.save(car);
        }
    }
}
