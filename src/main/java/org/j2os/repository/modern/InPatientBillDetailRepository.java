package org.j2os.repository.modern;

import org.j2os.domain.entity.InPatientBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InPatientBillDetailRepository extends JpaRepository<InPatientBillDetail , Long> {
    Optional<InPatientBillDetail> findInPatientBillDetailByInPatientBillDetailIdAndRemoveDateTimeIsNull(Long inPatientBillDetailId);
}
