package org.j2os.repository.modern;

import org.j2os.domain.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpecRepository extends JpaRepository<Specialization , Long> {
    Optional<Specialization> findBySpecializationIdAndRemoveDateTimeIsNull(Long specializationId);
    Specialization findSpecializationBySpecializationAndRemoveDateTimeIsNull(String specialization);
    List<Specialization> findSpecializationsByRemoveDateTimeIsNull();
}
