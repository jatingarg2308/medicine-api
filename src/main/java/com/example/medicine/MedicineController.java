package com.example.medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/uploadCSV")
    public String UploadCsv(@RequestBody String filePath){
        try{
            medicineService.loadCsv(filePath);
            return "Success";
        }catch (IOException e){
            return e.getMessage();
        }
    }

    @GetMapping("/searchMedicine")
    public List<Medicine> getMedicinebyName(@RequestParam String name){
        return medicineService.getMedicineByName(name);
    }

    @GetMapping("/getMedicineDetails")
    public List<Medicine> getMedicinebyUniqueCode(@RequestParam int uniqueCode){
        return medicineService.getMedicineByUniqueCode(uniqueCode);
    }

    @PostMapping("/placeOrder")
    public List<Orders> uploadPlaceOrder(@RequestBody List<Orders> orders){
        return medicineService.placeOrder(orders);
    }
}
