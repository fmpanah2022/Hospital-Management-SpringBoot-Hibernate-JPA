package org.j2os.service.modern;

import org.j2os.domain.entity.DoctorSpec;
import java.util.List;
import java.util.Optional;

public interface DoctorSpecService {
    DoctorSpec findDoctorSpecByDoctorIdAndSpecializationIdAndRemoveDateTimeIsNull(Long doctorId ,Long specId);
    boolean existsDoctorSpec ( DoctorSpec doctorSpec);
    boolean conflictDoctorSpec ( DoctorSpec doctorSpec) ;
    List<DoctorSpec> findDoctorSpecsByDoctorIdAndRemoveDateTimeIsNull(Long doctorId);
    List<DoctorSpec> findDoctorSpecsBySpecializationIdAndRemoveDateTimeIsNull(Long specId);
    DoctorSpec save(DoctorSpec doctorSpec);

    List<DoctorSpec> saveDoctorSpecListOfOneDoctor(Long doctorId, List<String> specNameList);

    DoctorSpec update(DoctorSpec doctorSpec);

    void removeLogical(DoctorSpec doctorSpec);
    Optional<DoctorSpec> findDoctorSpecByDoctorSpecIdAndRemoveDateTimeIsNull(Long doctorSpecId);

}
