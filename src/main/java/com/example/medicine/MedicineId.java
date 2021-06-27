package com.example.medicine;

import lombok.Data;

import java.io.Serializable;

@Data
public class MedicineId implements Serializable {
    int uniqueCode;
    String batchNo;

    public MedicineId(int unique_code, String batch_no) {
        batchNo = batch_no;
        uniqueCode = unique_code;
    }
    public MedicineId(){};
}
