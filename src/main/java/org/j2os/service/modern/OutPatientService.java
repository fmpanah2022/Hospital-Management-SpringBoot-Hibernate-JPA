package org.j2os.service.modern;

import org.j2os.domain.entity.OutPatient;
import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.domain.View.OutPatientView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OutPatientService {
    Map<String, Object> convertOutPatientToMap(OutPatientView outPatientView);
    List<Map<String, Object>> convertOutPatientListToMapList(List<OutPatientView> outPatientViews);
    List<OutPatientView> findOutPatients();
    OutPatientView findByOutPatientId(Long outPatientId);
    List<OutPatientView> findByDoctorId(Long doctorId);
    OutPatientView findByInPatientId(Long inPatientId);
    List<OutPatientView> findByPatient_NationalCode(int patientNationalCode);
    List<OutPatientView> findByPatientId(Long patientId);
    Optional<OutPatient> findOutPatientByOutPatientIdAndRemoveDateTimeIsNull(Long outPatientId);
    OutPatient save(OutPatient outPatient);
    OutPatient saveWithMedicalInfo(OutPatient outPatient ,List<OutPatientMedicalInfo> outPatientMedicalInfoList);
    OutPatient update(OutPatient outPatient);
    void removeLogical(OutPatient outPatient);
    boolean existsOutPatient(OutPatient outPatient) ;
    boolean conflictOutPatient(OutPatient outPatient) ;
    boolean canRemove(OutPatient outPatient) ;
}
