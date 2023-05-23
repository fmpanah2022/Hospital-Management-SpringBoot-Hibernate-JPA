package org.j2os.repository.modern;

import org.j2os.domain.entity.InPatientBill;
import org.j2os.service.Logic.InPatientBillLogic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InPatientBillRepository extends JpaRepository<InPatientBill , Long> {
    Optional<InPatientBill> findInPatientBillByInPatientBillIdAndRemoveDateTimeIsNull(Long inPatientBillId);
    InPatientBill findInPatientBillByInPatient_InPatientIdAndRemoveDateTimeIsNull(Long inPatientId);
}
