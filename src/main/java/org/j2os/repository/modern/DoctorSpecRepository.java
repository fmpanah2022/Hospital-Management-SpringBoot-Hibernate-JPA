package org.j2os.repository.modern;

import org.j2os.domain.entity.DoctorSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DoctorSpecRepository extends JpaRepository<DoctorSpec,Long> {
    Optional<DoctorSpec> findDoctorSpecByDoctorSpecIdAndRemoveDateTimeIsNull(Long doctorSpecId);
    List<DoctorSpec> findDoctorSpecsByDoctorIdAndRemoveDateTimeIsNull(Long doctorId);
    List<DoctorSpec> findDoctorSpecsBySpecializationIdAndRemoveDateTimeIsNull(Long specId);
    DoctorSpec findDoctorSpecByDoctorIdAndSpecializationIdAndRemoveDateTimeIsNull(Long doctorId ,Long specId);
}
