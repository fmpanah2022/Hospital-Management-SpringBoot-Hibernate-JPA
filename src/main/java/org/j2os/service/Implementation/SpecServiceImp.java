package org.j2os.service.Implementation;

import org.j2os.domain.entity.Specialization;
import org.j2os.repository.modern.DoctorSpecRepository;
import org.j2os.repository.Classic.SpecClassicRepository;
import org.j2os.repository.modern.SpecRepository;
import org.j2os.service.modern.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SpecServiceImp implements SpecService {
    @Autowired
    private SpecClassicRepository specClassicRepository;
    @Autowired
    private SpecRepository specRepository;
    @Autowired
    private DoctorSpecRepository doctorSpecRepository;
    //****************************************************************
    @Override
    public Map<String, Object> convertSpecToMap(Specialization specialization) {
        Map<String, Object> data = new HashMap<>();
        data.put("specializationId",specialization.getSpecializationId());
        data.put("specialization", specialization.getSpecialization());
        data.put("versionNumber", specialization.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertSpecListToMapList(List<Specialization> specializations) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Specialization specialization : specializations) {
            Map<String, Object> data = new HashMap<>();
            data.put("specializationId",specialization.getSpecializationId());
            data.put("specialization", specialization.getSpecialization());
            data.put("versionNumber", specialization.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public Optional<Specialization> findBySpecializationIdAndRemoveDateTimeIsNull(Long specializationId) {
        return specRepository.findBySpecializationIdAndRemoveDateTimeIsNull(specializationId);
    }
    @Override
    public boolean existsSpecialization(Specialization spec)  {
    return  (  specRepository.findSpecializationBySpecializationAndRemoveDateTimeIsNull(spec.getSpecialization()) != null  ) ;
}
    @Override
    public boolean conflictSpecialization(Specialization spec)  {
        Specialization spec1 = specRepository.findSpecializationBySpecializationAndRemoveDateTimeIsNull(spec.getSpecialization());
        if  (  spec1 != null && spec1.getSpecializationId() != null)
            return( spec1.getSpecializationId() != spec.getSpecializationId()) ;
        else return false;
    }
    @Override
    public boolean canRemove(Specialization spec)  {
        return ( doctorSpecRepository.findDoctorSpecsBySpecializationIdAndRemoveDateTimeIsNull(spec.getSpecializationId())).isEmpty();
    }

    @Override
    @Transactional
    public Specialization save(Specialization specialization) {
        return specClassicRepository.save(specialization);
    }

    @Override
    @Transactional
    public Specialization update(Specialization specialization) {
        return specClassicRepository.update(specialization);
    }

    @Override
    @Transactional
    public void removeLogical(Specialization specialization) {
        specClassicRepository.removeLogical(specialization);
    }

    @Override
    public Specialization findSpecializationBySpecializationAndRemoveDateTimeIsNull(String specialization) {
        return specRepository.findSpecializationBySpecializationAndRemoveDateTimeIsNull(specialization);
    }

    @Override
    public List<Specialization> findSpecializationsByRemoveDateTimeIsNull() {
        return specRepository.findSpecializationsByRemoveDateTimeIsNull();
    }

    @Override
    public Optional<Specialization> findById(Long id) {
        return specRepository.findById(id);
    }
    //-------------------------------------------------------------
}
