package org.j2os.service.Implementation;

import org.j2os.domain.entity.OutPatientMedicalInfo;
import org.j2os.domain.View.OutPatientMedicalInfoView;
import org.j2os.repository.Classic.OutPatientMedicalInfoClassicRepository;
import org.j2os.repository.modern.OutPatientMedicalInfoRepository;
import org.j2os.service.modern.OutPatientMedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OutPatientMedicalInfoServiceImp implements OutPatientMedicalInfoService {
    @Autowired
    private OutPatientMedicalInfoRepository outPatientMedicalInfoRepository;
    @Autowired
    private OutPatientMedicalInfoClassicRepository outPatientMedicalInfoClassicRepository;
    //------------------------------------------------------------------------------------------------------
    @Override
    public boolean existsOutPatientMedicalInfo(OutPatientMedicalInfo outPatientMedicalInfo) {
        return false;
    }

    @Override
    public boolean conflictOutPatientMedicalInfo(OutPatientMedicalInfo outPatientMedicalInfo) {
        return false;
    }

    @Override
    public boolean canRemove(OutPatientMedicalInfo outPatientMedicalInfo) {
        return true;
    }

    @Override
    public List<Map<String, Object>> findOutPatientMedicalInfosByOutPatientId(Long outPatientId) {
        return convertOutPatientMedicalInfoListToMapList(outPatientMedicalInfoClassicRepository.findOutPatientMedicalInfosByOutPatientId(outPatientId));
    }

    @Override
    public Map<String, Object> findOutPatientMedicalInfoById(Long outPatientMedicalInfoId) {
        return convertOutPatientMedicalInfoViewToMap(outPatientMedicalInfoClassicRepository.findOutPatientMedicalInfoById(outPatientMedicalInfoId));
    }

    @Override
    public Map<String, Object> convertOutPatientMedicalInfoViewToMap(OutPatientMedicalInfoView outMedicalInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("outPatientMedicalInfoId",outMedicalInfo.getOutPatientMedicalInfoId());
        data.put("medicineId",outMedicalInfo.getMedicineId());
        data.put("medicineCode", outMedicalInfo.getMedicineCode());
        data.put("medicineName", outMedicalInfo.getMedicineName());
        data.put("outPatientId", outMedicalInfo.getOutPatientId());
        data.put("count", outMedicalInfo.getCount());
        data.put("versionNumber", outMedicalInfo.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertOutPatientMedicalInfoListToMapList(List<OutPatientMedicalInfoView> outMedicalInfos) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (OutPatientMedicalInfoView outMedicalInfo : outMedicalInfos) {
            Map<String, Object> data = new HashMap<>();
            data.put("outPatientMedicalInfoId",outMedicalInfo.getOutPatientMedicalInfoId());
            data.put("medicineId",outMedicalInfo.getMedicineId());
            data.put("medicineCode", outMedicalInfo.getMedicineCode());
            data.put("medicineName", outMedicalInfo.getMedicineName());
            data.put("outPatientId", outMedicalInfo.getOutPatientId());
            data.put("count", outMedicalInfo.getCount());
            data.put("versionNumber", outMedicalInfo.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public List<OutPatientMedicalInfo> findOutPatientMedicalInfosByOutPatientIdAndRemoveDateTimeIsNull(Long outPatientId) {
        return outPatientMedicalInfoRepository.findOutPatientMedicalInfosByOutPatientIdAndRemoveDateTimeIsNull(outPatientId);
    }

    @Override
    public Optional<OutPatientMedicalInfo> findOutPatientMedicalInfosByOutPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long outPatientMedicalInfoId) {
        return outPatientMedicalInfoRepository.findOutPatientMedicalInfosByOutPatientMedicalInfoIdAndRemoveDateTimeIsNull(outPatientMedicalInfoId);
    }

    @Override
    @Transactional
    public OutPatientMedicalInfo save(OutPatientMedicalInfo outPatientMedicalInfo) {
        return outPatientMedicalInfoClassicRepository.save(outPatientMedicalInfo);
    }

    @Override
    @Transactional
    public OutPatientMedicalInfo update(OutPatientMedicalInfo outPatientMedicalInfo) {
        return outPatientMedicalInfoClassicRepository.update(outPatientMedicalInfo);
    }

    @Override
    @Transactional
    public void removeLogical(OutPatientMedicalInfo outPatientMedicalInfo) {
        outPatientMedicalInfoClassicRepository.removeLogical(outPatientMedicalInfo);
    }
}
