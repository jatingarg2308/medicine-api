package com.example.medicine.Repository;

import com.example.medicine.Model.Medicine;
import com.example.medicine.Model.MedicineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, MedicineId> {

    List<Medicine> findByNameIsContaining(String name);
    List<Medicine> findByUniqueCode(int uniqueCode);
}
