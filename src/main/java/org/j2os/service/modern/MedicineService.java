package org.j2os.service.modern;

import org.j2os.domain.entity.Medicine;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MedicineService {
    Map<String, Object> convertMedicineToMap(Medicine medicine);
    List<Map<String, Object>> convertMedicineListToMapList(List<Medicine> medicines);
    List<Medicine> findMedicinesByRemoveDateTimeIsNull();
    boolean existsMedicine ( Medicine medicine);
    boolean conflictMedicine ( Medicine medicine) ;
    boolean canRemove( Medicine medicine);
    Optional<Medicine> findByMedicineIdAndRemoveDateTimeIsNull(Long medicineId);
    public Medicine save (Medicine medicine);
    public Medicine update (Medicine medicine);
    public void removeLogical(Medicine medicine);
    public Medicine findMedicineByMedicineCodeAndRemoveDateTimeIsNull(String medicineCode);
    public Medicine findMedicineByMedicineNameAndRemoveDateTimeIsNull( String medicineName);
    public List<Medicine> findMedicineByMedicineCostIsBetweenAndRemoveDateTimeIsNull(int fromCost  , int toCost);
    Optional<Medicine> findById(Long id);
}
