package org.j2os.repository.modern;

import org.j2os.domain.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine , Long> {
    Optional<Medicine> findByMedicineIdAndRemoveDateTimeIsNull(Long medicineId);
    Medicine findMedicineByMedicineCodeAndRemoveDateTimeIsNull(String medicineCode);
    Medicine findMedicineByMedicineNameAndRemoveDateTimeIsNull( String medicineName);
    List<Medicine> findMedicineByMedicineCostIsBetweenAndRemoveDateTimeIsNull(int fromCost  , int toCost);
    List<Medicine> findMedicinesByRemoveDateTimeIsNull();
}
