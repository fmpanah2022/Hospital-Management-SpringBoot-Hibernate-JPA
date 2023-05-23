package org.j2os.service.modern;

import org.j2os.domain.entity.InPatientMedicalInfo;
import org.j2os.domain.View.InPatientMedicalInfoView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InPatientMedicalInfoService {
    List<Map<String, Object>> findInPatientMedicalInfosByInPatientId(Long inPatientId);
    Map<String, Object> findInPatientMedicalInfoById(Long inPatientMedicalInfoId);
    Map<String, Object> convertInPatientMedicalInfoViewToMap(InPatientMedicalInfoView inMedicalInfo);
    List<Map<String, Object>> convertInPatientMedicalInfoListToMapList(List<InPatientMedicalInfoView> inMedicalInfos);
    List<InPatientMedicalInfo> findInPatientMedicalInfoByInPatientIdAndRemoveDateTimeIsNull(Long inPatientId);
    Optional<InPatientMedicalInfo> findInPatientMedicalInfoByInPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long inPatientMedicalInfoId);
    InPatientMedicalInfo save(InPatientMedicalInfo inPatientMedicalInfo);
    // Just Medicine ID
    InPatientMedicalInfo update(InPatientMedicalInfo inPatientMedicalInfo);
    void removeLogical(InPatientMedicalInfo inPatientMedicalInfo);
    List<InPatientMedicalInfo> findInPatientMedicalInfoByRemoveDateTimeIsNull();
    List<InPatientMedicalInfo> saveList(Long inPatientId, List<InPatientMedicalInfo> medicineDetailList);
}
