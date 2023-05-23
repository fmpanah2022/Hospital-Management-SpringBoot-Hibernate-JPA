package org.j2os.service.Implementation;

import org.j2os.domain.View.DoctorSpecView;
import org.j2os.repository.modern.DoctorSpecViewRepository;
import org.j2os.service.modern.DoctorSpecViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorSpecViewServiceImp implements DoctorSpecViewService {
    @Autowired
    private DoctorSpecViewRepository doctorSpecViewRepository;
    //******************************************************************

    @Override
    public Map<String, Object> convertDoctorSpecViewToMap(DoctorSpecView doctorSpecView) {
        Map<String, Object> data = new HashMap<>();
        data.put("doctorSpecId",doctorSpecView.getDoctorSpecId());
        data.put("doctorId", doctorSpecView.getDoctorId());
        data.put("personalCode", doctorSpecView.getPersonalCode());
        data.put("personId", doctorSpecView.getPersonId());
        data.put("surname", doctorSpecView.getSurname());
        data.put("firstName", doctorSpecView.getFirstName());
        data.put("birthdate" , doctorSpecView.getBirthdate());
        data.put("nationalCode", doctorSpecView.getNationalCode());
        data.put("specializationId" , doctorSpecView.getSpecializationId());
        data.put("specialization", doctorSpecView.getSpecialization());
        data.put("doctorSpecVno", doctorSpecView.getDoctorSpecVno());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertDoctorSpecViewListToMapList(List<DoctorSpecView> doctorSpecViews) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (DoctorSpecView doctorSpecView : doctorSpecViews) {
            Map<String, Object> data = new HashMap<>();
            data.put("doctorSpecId",doctorSpecView.getDoctorSpecId());
            data.put("doctorId", doctorSpecView.getDoctorId());
            data.put("personalCode", doctorSpecView.getPersonalCode());
            data.put("personId", doctorSpecView.getPersonId());
            data.put("surname", doctorSpecView.getSurname());
            data.put("firstName", doctorSpecView.getFirstName());
            data.put("birthdate" , doctorSpecView.getBirthdate());
            data.put("nationalCode", doctorSpecView.getNationalCode());
            data.put("specializationId" , doctorSpecView.getSpecializationId());
            data.put("specialization", doctorSpecView.getSpecialization());
            data.put("doctorSpecVno", doctorSpecView.getDoctorSpecVno());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<Map<String, Object>> findDoctorSpecViewsBySpecializationId(Long specId) {
        return convertDoctorSpecViewListToMapList(doctorSpecViewRepository.findDoctorSpecViewsBySpecializationId(specId));
    }

    @Override
    public List<Map<String, Object>> findDoctorSpecViewsByDoctorId(Long doctorId) {
        return convertDoctorSpecViewListToMapList(doctorSpecViewRepository.findDoctorSpecViewsByDoctorId(doctorId));
    }

    @Override
    public List<Map<String, Object>> findAllDoctorSpecViews() {
        return convertDoctorSpecViewListToMapList(doctorSpecViewRepository.findAll());
    }

    @Override
    public Map<String, Object> findDoctorSpecViewByDoctorSpecId(Long doctorSpecId) {
        return convertDoctorSpecViewToMap(doctorSpecViewRepository.findDoctorSpecViewByDoctorSpecId(doctorSpecId));
    }
}
