package org.j2os.service.modern;

import org.j2os.domain.View.DoctorSpecView;
import java.util.List;
import java.util.Map;

public interface DoctorSpecViewService {
    Map<String, Object> convertDoctorSpecViewToMap(DoctorSpecView doctorSpecView);
    List<Map<String, Object>> convertDoctorSpecViewListToMapList(List<DoctorSpecView> doctorSpecViews);
    List<Map<String, Object>> findDoctorSpecViewsBySpecializationId(Long specId);
    List<Map<String, Object>> findDoctorSpecViewsByDoctorId(Long doctorId);
    List<Map<String, Object>> findAllDoctorSpecViews();
    Map<String, Object> findDoctorSpecViewByDoctorSpecId(Long doctorSpecId);
}
