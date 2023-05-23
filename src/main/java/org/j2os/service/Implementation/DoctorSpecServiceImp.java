package org.j2os.service.Implementation;

import org.j2os.domain.entity.DoctorSpec;
import org.j2os.repository.Classic.DoctorSpecClassicRepository;
import org.j2os.repository.modern.DoctorSpecRepository;
import org.j2os.service.modern.DoctorSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorSpecServiceImp implements DoctorSpecService {
    @Autowired
    private DoctorSpecRepository doctorSpecRepository;
    @Autowired
    private DoctorSpecClassicRepository doctorSpecClassicRepository;
    //***************************************************************
    @Override
    public DoctorSpec findDoctorSpecByDoctorIdAndSpecializationIdAndRemoveDateTimeIsNull(Long doctorId, Long specId) {
        return doctorSpecRepository.findDoctorSpecByDoctorIdAndSpecializationIdAndRemoveDateTimeIsNull(doctorId, specId);
    }

    @Override
    public boolean existsDoctorSpec(DoctorSpec doctorSpec) {
        return  (  findDoctorSpecByDoctorIdAndSpecializationIdAndRemoveDateTimeIsNull(doctorSpec.getDoctorId() , doctorSpec.getSpecializationId()) != null  ) ;
    }

    @Override
    public boolean conflictDoctorSpec(DoctorSpec doctorSpec) {
        DoctorSpec doctorSpec1 = findDoctorSpecByDoctorIdAndSpecializationIdAndRemoveDateTimeIsNull(doctorSpec.getDoctorId() , doctorSpec.getSpecializationId());
        if  (  doctorSpec1 != null && doctorSpec1.getDoctorSpecId() != null)
            return( doctorSpec1.getDoctorSpecId() != doctorSpec.getDoctorSpecId()) ;
        else return false;
    }
    @Override
    public List<DoctorSpec> findDoctorSpecsByDoctorIdAndRemoveDateTimeIsNull(Long doctorId) {
        return doctorSpecRepository.findDoctorSpecsByDoctorIdAndRemoveDateTimeIsNull(doctorId);
    }
    @Override
    public List<DoctorSpec> findDoctorSpecsBySpecializationIdAndRemoveDateTimeIsNull(Long specId) {
        return doctorSpecRepository.findDoctorSpecsBySpecializationIdAndRemoveDateTimeIsNull(specId);
    }

    @Override
    @Transactional
    public DoctorSpec save(DoctorSpec doctorSpec) {
        return doctorSpecClassicRepository.save(doctorSpec);
    }

    @Override
    @Transactional
    public List<DoctorSpec> saveDoctorSpecListOfOneDoctor(Long doctorId, List<String> specNameList) {
        return doctorSpecClassicRepository.saveDoctorSpecListOfOneDoctor(doctorId, specNameList);
    }

    @Override
    @Transactional
    public DoctorSpec update(DoctorSpec doctorSpec) {
        return doctorSpecClassicRepository.update(doctorSpec);
    }

    @Override
    @Transactional
    public void removeLogical(DoctorSpec doctorSpec) {
        doctorSpecClassicRepository.removeLogical(doctorSpec);
    }

    @Override
    public Optional<DoctorSpec> findDoctorSpecByDoctorSpecIdAndRemoveDateTimeIsNull(Long doctorSpecId) {
        return doctorSpecRepository.findDoctorSpecByDoctorSpecIdAndRemoveDateTimeIsNull(doctorSpecId);
    }
}
