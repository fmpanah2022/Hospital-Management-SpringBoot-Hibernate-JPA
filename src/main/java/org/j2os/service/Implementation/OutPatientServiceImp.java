package org.j2os.service.Implementation;

import org.j2os.domain.entity.OutPatient;
import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.domain.View.OutPatientView;
import org.j2os.repository.Classic.OutPatientClassicRepository;
import org.j2os.repository.modern.OutPatientMedicalInfoRepository;
import org.j2os.repository.modern.OutPatientRepository;
import org.j2os.service.modern.OutPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OutPatientServiceImp implements OutPatientService {
    @Autowired
    private OutPatientRepository outPatientRepository;
    @Autowired
    private OutPatientClassicRepository outPatientClassicRepository;
    @Autowired
    private OutPatientMedicalInfoRepository outPatientMedicalInfoRepository;
    //------------------------------------------------------------------------------------------
    @Override
    public Map<String, Object> convertOutPatientToMap(OutPatientView outPatientView) {
        Map<String, Object> data = new HashMap<>();
        data.put("outPatientId",outPatientView.getOutPatientId());
        data.put("outPatientDate", outPatientView.getOutPatientDate());
        data.put("inPatientId", outPatientView.getInPatientId());
        data.put("inPatientDate",outPatientView.getInPatientDate());
        data.put("doctorId", outPatientView.getDoctorId());
        data.put("doctorPersonalCode",outPatientView.getDoctorPersonalCode());
        data.put("doctorName", outPatientView.getDoctorName());
        data.put("patientId", outPatientView.getPatientId());
        data.put("patientNationalCode", outPatientView.getPatientNationalCode());
        data.put("patientName", outPatientView.getPatientName());
        data.put("outVno",outPatientView.getOutVno());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertOutPatientListToMapList(List<OutPatientView> outPatientViews) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (OutPatientView outPatientView : outPatientViews) {
            Map<String, Object> data = new HashMap<>();
            data.put("outPatientId",outPatientView.getOutPatientId());
            data.put("outPatientDate", outPatientView.getOutPatientDate());
            data.put("inPatientId", outPatientView.getInPatientId());
            data.put("inPatientDate",outPatientView.getInPatientDate());
            data.put("doctorId", outPatientView.getDoctorId());
            data.put("doctorPersonalCode",outPatientView.getDoctorPersonalCode());
            data.put("doctorName", outPatientView.getDoctorName());
            data.put("patientId", outPatientView.getPatientId());
            data.put("patientNationalCode", outPatientView.getPatientNationalCode());
            data.put("patientName", outPatientView.getPatientName());
            data.put("outVno",outPatientView.getOutVno());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<OutPatientView> findOutPatients() {
        return outPatientClassicRepository.findOutPatients();
    }

    @Override
    public OutPatientView findByOutPatientId(Long outPatientId) {
        return outPatientClassicRepository.findByOutPatientId(outPatientId);
    }

    @Override
    public List<OutPatientView> findByDoctorId(Long doctorId) {
        return outPatientClassicRepository.findByDoctorId(doctorId);
    }

    @Override
    public OutPatientView findByInPatientId(Long inPatientId) {
        return outPatientClassicRepository.findByInPatientId(inPatientId);
    }

    @Override
    public List<OutPatientView> findByPatient_NationalCode(int patientNationalCode) {
        return outPatientClassicRepository.findByPatient_NationalCode(patientNationalCode);
    }

    @Override
    public List<OutPatientView> findByPatientId(Long patientId) {
        return outPatientClassicRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<OutPatient> findOutPatientByOutPatientIdAndRemoveDateTimeIsNull(Long outPatientId) {
        return outPatientRepository.findOutPatientByOutPatientIdAndRemoveDateTimeIsNull(outPatientId);
    }

    @Override
    @Transactional
    public OutPatient save(OutPatient outPatient) {
        return outPatientClassicRepository.save(outPatient);
    }

    @Override
    @Transactional
    public OutPatient saveWithMedicalInfo(OutPatient outPatient, List<OutPatientMedicalInfo> outPatientMedicalInfoList) {
        return outPatientClassicRepository.saveWithMedicalInfo(outPatient ,outPatientMedicalInfoList);
    }

    @Override
    @Transactional
    public OutPatient update(OutPatient outPatient) {
        return outPatientClassicRepository.update(outPatient);
    }

    @Override
    @Transactional
    public void removeLogical(OutPatient outPatient) {
        outPatientClassicRepository.removeLogical(outPatient);
    }

    @Override
    public boolean existsOutPatient(OutPatient outPatient) {
        return( findByInPatientId(outPatient.getInPatient().getInPatientId()) != null);
    }

    @Override
    public boolean conflictOutPatient(OutPatient outPatient) {
        OutPatientView outPatient1 = findByInPatientId((outPatient.getInPatient().getInPatientId()));
        if  (  outPatient1 != null && outPatient1.getOutPatientId() != null)
            return( outPatient1.getOutPatientId() != outPatient.getOutPatientId()) ;
        else return false;
    }
    @Override
    public boolean canRemove(OutPatient outPatient) {
       return  outPatientMedicalInfoRepository.findOutPatientMedicalInfosByOutPatientIdAndRemoveDateTimeIsNull(outPatient.getOutPatientId()).isEmpty() ;
    }
}
