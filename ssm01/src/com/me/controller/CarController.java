package com.me.controller;

import com.me.domain.Car;
import com.me.service.CarService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("importCars")
    @ResponseBody
    public String importCars(MultipartFile excel) throws IOException {
        InputStream inputStream = excel.getInputStream();

        List<Car> carList = new ArrayList<>();//准备装载excel文件中的内容

        Workbook workbook = WorkbookFactory.create(inputStream);//获得excel文件的内容
        Sheet sheet = workbook.getSheetAt(0);//excel中的一个sheet表对象
        for (int i=1;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);//excel中的行对象
            Cell cell1 = row.getCell(0);//excel中的单元对象
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(2);

            String cname = cell1.toString();
            String color = cell2.toString();
            Integer price = Integer.parseInt(cell3.toString().replace(".0",""));

            Car car = new Car(null,cname,color,price);
            carList.add(car);
        }
        //从上传的excel文件中，读取了所有的汽车信息，并装入List集合。
        carService.saves(carList);
        return "success!!!";
    }
}
