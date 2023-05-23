package org.j2os.service.Implementation;

import org.j2os.domain.entity.Medicine;
import org.j2os.repository.Classic.MedicineClassicRepository;
import org.j2os.repository.modern.MedicineRepository;
import org.j2os.service.modern.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MedicineServiceImp implements MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineClassicRepository medicineClassicRepository;
//*****************************************************************************
    @Override
    public boolean existsMedicine(Medicine medicine) {
        return( medicineRepository.findMedicineByMedicineCodeAndRemoveDateTimeIsNull(medicine.getMedicineCode()) != null);
    }
    @Override
    public boolean conflictMedicine(Medicine medicine) {
        Medicine medicine1 = medicineRepository.findMedicineByMedicineCodeAndRemoveDateTimeIsNull(medicine.getMedicineCode());
        if  (  medicine1 != null && medicine1.getMedicineId() != null)
            return( medicine1.getMedicineId() != medicine.getMedicineId()) ;
        else return false;
    }
// BAAAADAN DOROST KONAMMMMMMM
    @Override
    public boolean canRemove(Medicine medicine) {
       // return ( inPatientRepository.f.isEmpty());
        return true;
    }
    @Override
    public Optional<Medicine> findByMedicineIdAndRemoveDateTimeIsNull(Long medicineId) {
        return medicineRepository.findByMedicineIdAndRemoveDateTimeIsNull(medicineId);
    }

    @Override
    @Transactional
    public Medicine save(Medicine medicine) {
        return medicineClassicRepository.save(medicine);
    }

    @Override
    @Transactional
    public Medicine update(Medicine medicine) {
        return medicineClassicRepository.update(medicine);
    }

    @Override
    @Transactional
    public void removeLogical(Medicine medicine) {
        medicineClassicRepository.removeLogical(medicine);
    }

    @Override
    public Medicine findMedicineByMedicineCodeAndRemoveDateTimeIsNull(String medicineCode) {
        return medicineRepository.findMedicineByMedicineCodeAndRemoveDateTimeIsNull(medicineCode);
    }

    @Override
    public Medicine findMedicineByMedicineNameAndRemoveDateTimeIsNull(String medicineName) {
        return medicineRepository.findMedicineByMedicineNameAndRemoveDateTimeIsNull(medicineName);
    }

    @Override
    public List<Medicine> findMedicineByMedicineCostIsBetweenAndRemoveDateTimeIsNull(int fromCost, int toCost) {
        return medicineRepository.findMedicineByMedicineCostIsBetweenAndRemoveDateTimeIsNull(fromCost, toCost);
    }

    @Override
    public Map<String, Object> convertMedicineToMap(Medicine medicine) {
        Map<String, Object> data = new HashMap<>();
        data.put("medicineId",medicine.getMedicineId());
        data.put("medicineCode", medicine.getMedicineCode());
        data.put("medicineName", medicine.getMedicineName());
        data.put("medicineCost",medicine.getMedicineCost());
        data.put("versionNumber", medicine.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertMedicineListToMapList(List<Medicine> medicines) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            Map<String, Object> data = new HashMap<>();
            data.put("medicineId",medicine.getMedicineId());
            data.put("medicineCode", medicine.getMedicineCode());
            data.put("medicineName", medicine.getMedicineName());
            data.put("medicineCost",medicine.getMedicineCost());
            data.put("versionNumber", medicine.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<Medicine> findMedicinesByRemoveDateTimeIsNull() {
        return medicineRepository.findMedicinesByRemoveDateTimeIsNull();
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        return medicineRepository.findById(id);
    }
}
