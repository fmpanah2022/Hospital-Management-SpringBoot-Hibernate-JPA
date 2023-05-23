package org.j2os.repository.modern;

import org.j2os.domain.View.DoctorSpecView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSpecViewRepository extends JpaRepository<DoctorSpecView , Long> {
    List<DoctorSpecView> findDoctorSpecViewsBySpecializationId(Long specId);
    List<DoctorSpecView> findDoctorSpecViewsByDoctorId(Long doctorId);
    DoctorSpecView findDoctorSpecViewByDoctorSpecId(Long doctorSpecId);
}
