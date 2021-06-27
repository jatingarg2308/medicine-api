package com.example.medicine;


import javax.persistence.*;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Entity
@Data
@IdClass(MedicineId.class)
@Table(name="medicines")
public class Medicine {
    public @Id @CsvBindByName(column = "c_unique_code") int uniqueCode;


    public  @CsvBindByName(column="c_name") String name;
    public @Id @CsvBindByName(column = "c_batch_no") String batchNo;
    public  @CsvBindByName(column = "d_expiry_date") String expiryDate;
    public  @CsvBindByName(column = "n_balance_qty") int balanceQty;
    public  @CsvBindByName(column = "c_packaging") String packaging;
    public  @CsvBindByName(column = "c_schemes") String schemes;
    public  @CsvBindByName(column = "n_mrp") float mrp;
    public  @CsvBindByName(column = "c_manufacturer") String manufacturer;
    public  @CsvBindByName(column = "hsn_code") int hsnCode;
}

