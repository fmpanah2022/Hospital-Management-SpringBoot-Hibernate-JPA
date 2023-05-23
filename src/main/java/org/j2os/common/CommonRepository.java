package org.j2os.common;

import org.j2os.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CommonRepository extends JpaRepository<Doctor,Long> {
    @Query(value = "SELECT PERSONALCODE_SEQ.nextval FROM DUAL", nativeQuery = true)
    BigDecimal getNextValPERSONALCODE_SEQ();
}
