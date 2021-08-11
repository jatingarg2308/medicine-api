package com.example.medicine.Service;

import com.example.medicine.Model.Medicine;
import com.example.medicine.Repository.MedicineRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GraphQLApi
public class GraphQLService {

    @Autowired
    MedicineRepository medicineRepository;

    @GraphQLQuery(name="allMedicine")
    public List<Medicine> getAllMedicine(){
        return medicineRepository.findAll();
    }

    @GraphQLQuery(name="medicine")
    public List<Medicine> getAllMedicine(@GraphQLArgument(name = "uniqueCode") int uniqueCode){
        return medicineRepository.findByUniqueCode(uniqueCode);
    }
}
