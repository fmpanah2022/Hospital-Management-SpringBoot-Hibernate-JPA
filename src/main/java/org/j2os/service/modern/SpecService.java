package org.j2os.service.modern;

import org.j2os.domain.entity.Specialization;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SpecService {
    Map<String, Object> convertSpecToMap(Specialization specialization);
    List<Map<String, Object>> convertSpecListToMapList(List<Specialization> specializations);
    Optional<Specialization> findBySpecializationIdAndRemoveDateTimeIsNull(Long specializationId);
    boolean existsSpecialization ( Specialization spec);
    boolean conflictSpecialization ( Specialization spec) ;
    boolean canRemove( Specialization spec)  ;
    Specialization save(Specialization specialization);
    Specialization update(Specialization specialization);
    void removeLogical(Specialization specialization);
    Specialization findSpecializationBySpecializationAndRemoveDateTimeIsNull(String specialization);
    List<Specialization> findSpecializationsByRemoveDateTimeIsNull();
    Optional<Specialization> findById(Long id);
}
