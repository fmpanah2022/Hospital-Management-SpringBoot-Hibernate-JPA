package org.j2os.service.modern;

import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.domain.View.OutPatientMedicalInfoView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OutPatientMedicalInfoService {
    List<Map<String, Object>> findOutPatientMedicalInfosByOutPatientId(Long outPatientId);
    Map<String, Object> findOutPatientMedicalInfoById(Long outPatientMedicalInfoId);
    Map<String, Object> convertOutPatientMedicalInfoViewToMap(OutPatientMedicalInfoView outMedicalInfo);
    List<Map<String, Object>> convertOutPatientMedicalInfoListToMapList(List<OutPatientMedicalInfoView> outMedicalInfos);
    List<OutPatientMedicalInfo> findOutPatientMedicalInfosByOutPatientIdAndRemoveDateTimeIsNull(Long outPatientId);
    Optional<OutPatientMedicalInfo> findOutPatientMedicalInfosByOutPatientMedicalInfoIdAndRemoveDateTimeIsNull (Long outPatientMedicalInfoId);
    OutPatientMedicalInfo save(OutPatientMedicalInfo outPatientMedicalInfo);
    OutPatientMedicalInfo update(OutPatientMedicalInfo outPatientMedicalInfo);
    void removeLogical(OutPatientMedicalInfo outPatientMedicalInfo);
    boolean existsOutPatientMedicalInfo(OutPatientMedicalInfo outPatientMedicalInfo);
    boolean conflictOutPatientMedicalInfo(OutPatientMedicalInfo outPatientMedicalInfo);
    boolean canRemove(OutPatientMedicalInfo outPatientMedicalInfo);
}
