package com.example.medicine.Utils;

import com.example.medicine.Model.Medicine;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@UtilityClass
@Slf4j
public class CsvUtil {

    public List<Medicine> getMedicine(String csvPath) throws IOException{

        return new CsvToBeanBuilder(new FileReader(csvPath))
                .withType(Medicine.class).build().parse();
    }

}
