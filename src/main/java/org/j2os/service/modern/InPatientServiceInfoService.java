package org.j2os.service.modern;

import org.j2os.domain.entity.InPatientServiceInfo;
import org.j2os.domain.View.InPatientServiceInfoView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InPatientServiceInfoService {
    List<Map<String, Object>> findServiceInfoByServiceId(Long serviceId);
    List<Map<String, Object>> findInPatientServiceInfosByInPatientId(Long inPatientId);
    Map<String, Object> findInPatientServiceInfoById(Long inPatientServiceInfoId);
    Map<String, Object> convertInPatientServiceInfoViewToMap(InPatientServiceInfoView inServiceInfo);
    List<Map<String, Object>> convertInPatientServiceInfoListToMapList(List<InPatientServiceInfoView> inServiceInfos);
    List<InPatientServiceInfo> findInPatientServiceInfosByServiceIdAndRemoveDateTimeIsNull(Long serviceId);
    List<InPatientServiceInfo> findInPatientServiceInfoByInPatientIdAndRemoveDateTimeIsNull(Long inPatientId);
    List<InPatientServiceInfo> findInPatientServiceInfoByRemoveDateTimeIsNull();
    Optional<InPatientServiceInfo> findInPatientServiceInfoByInPatientServiceInfoIdAndRemoveDateTimeIsNull(Long inPatientServiceInfoId);
    InPatientServiceInfo save(InPatientServiceInfo inPatientServiceInfo);
    List<InPatientServiceInfo> saveList(Long inPatientId, List<InPatientServiceInfo> inPatientServiceInfos);
    InPatientServiceInfo update(InPatientServiceInfo inPatientServiceInfo);
    void removeLogical(InPatientServiceInfo inPatientServiceInfo);
    boolean canRemove(InPatientServiceInfo inPatientServiceInfo);
}
