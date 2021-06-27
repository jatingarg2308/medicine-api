package com.example.medicine;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.*;


import lombok.val;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public boolean loadCsv(String filePath) throws IOException {
        val medicines = CsvUtil.getMedicine(filePath);

        int batchSize = 500;
        for (int i = 0; i < medicines.size();) {
            int chunkSize = Math.min(i + batchSize, medicines.size());
            val chunk = medicines.subList(i, chunkSize);
            processChunk(chunk);
            i += batchSize;
        }
        return true;
    }

    public List<Medicine> getMedicineByName(String name){
        return medicineRepository.findByNameIsContaining(name);
    }

    public List<Medicine> getMedicineByUniqueCode(int uniqueCode) {
        return medicineRepository.findByUniqueCode(uniqueCode);
    }

    public List<Orders> placeOrder(List<Orders> orders){
        val afterOrder = ordersRepository.saveAll(orders);
        return afterOrder;
    }

    private void processChunk(List<Medicine> medicines){
        Map<MedicineId, Medicine> allmeds = new HashMap<>();
        List<MedicineId> idFields = new ArrayList<>();
        for (Medicine medicine: medicines) {
            val medId = new MedicineId(medicine.uniqueCode, medicine.batchNo);
            if (allmeds.containsKey(medId)){
                val prev_medicine = allmeds.get(medId);
                prev_medicine.balanceQty += medicine.balanceQty;
                allmeds.put(medId, prev_medicine);
            }else{
                allmeds.put(medId, medicine);
                idFields.add(medId);
            }

        }

        val presentMeds = medicineRepository.findAllById(idFields);
        for (Medicine medicine: presentMeds) {
            val idField = new MedicineId(medicine.uniqueCode, medicine.batchNo);
            val updateMeds = allmeds.get(idField);
            updateMeds.balanceQty += medicine.balanceQty;
            allmeds.put(idField, updateMeds);
        }

        medicineRepository.saveAll(medicines);
    }

}
