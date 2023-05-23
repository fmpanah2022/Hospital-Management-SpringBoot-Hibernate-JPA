package org.j2os.service.Implementation;

import org.j2os.domain.entity.InPatientMedicalInfo;
import org.j2os.domain.View.InPatientMedicalInfoView;
import org.j2os.repository.Classic.InPatientMedicalInfoClassicRepository;
import org.j2os.repository.modern.InPatientMedicalInfoRepository;
import org.j2os.service.modern.InPatientMedicalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InPatientMedicalInfoServiceImp implements InPatientMedicalInfoService {
    @Autowired
    private InPatientMedicalInfoClassicRepository inPatientMedicalInfoClassicRepository;
    @Autowired
    private InPatientMedicalInfoRepository inPatientMedicalInfoRepository;
    //-----------------------------------------------------------------------------
    @Override
    public List<Map<String, Object>> findInPatientMedicalInfosByInPatientId(Long inPatientId) {
        return convertInPatientMedicalInfoListToMapList(inPatientMedicalInfoClassicRepository.findInPatientMedicalInfosByInPatientId(inPatientId));
    }

    @Override
    public Map<String, Object> findInPatientMedicalInfoById(Long inPatientMedicalInfoId) {
        return convertInPatientMedicalInfoViewToMap(inPatientMedicalInfoClassicRepository.findInPatientMedicalInfoById(inPatientMedicalInfoId));
    }

    @Override
    public Map<String, Object> convertInPatientMedicalInfoViewToMap(InPatientMedicalInfoView inMedicalInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("inPatientMedicalInfoId",inMedicalInfo.getInPatientMedicalInfoId());
        data.put("medicineId",inMedicalInfo.getMedicineId());
        data.put("medicineCode", inMedicalInfo.getMedicineCode());
        data.put("medicineName", inMedicalInfo.getMedicineName());
        data.put("inPatientId", inMedicalInfo.getInPatientId());
        data.put("count", inMedicalInfo.getCount());
        data.put("versionNumber", inMedicalInfo.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertInPatientMedicalInfoListToMapList(List<InPatientMedicalInfoView> inMedicalInfos) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (InPatientMedicalInfoView inMedicalInfo : inMedicalInfos) {
            Map<String, Object> data = new HashMap<>();
            data.put("inPatientMedicalInfoId",inMedicalInfo.getInPatientMedicalInfoId());
            data.put("medicineId",inMedicalInfo.getMedicineId());
            data.put("medicineCode", inMedicalInfo.getMedicineCode());
            data.put("medicineName", inMedicalInfo.getMedicineName());
            data.put("inPatientId", inMedicalInfo.getInPatientId());
            data.put("count", inMedicalInfo.getCount());
            data.put("versionNumber", inMedicalInfo.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public List<InPatientMedicalInfo> findInPatientMedicalInfoByInPatientIdAndRemoveDateTimeIsNull(Long inPatientId) {
        return inPatientMedicalInfoRepository.findInPatientMedicalInfoByInPatientIdAndRemoveDateTimeIsNull(inPatientId);
    }
    @Override
    public Optional<InPatientMedicalInfo> findInPatientMedicalInfoByInPatientMedicalInfoIdAndRemoveDateTimeIsNull(Long inPatientMedicalInfoId) {
        return inPatientMedicalInfoRepository.findInPatientMedicalInfoByInPatientMedicalInfoIdAndRemoveDateTimeIsNull(inPatientMedicalInfoId);
    }
    @Override
    @Transactional
    public InPatientMedicalInfo save(InPatientMedicalInfo inPatientMedicalInfo) {
        return inPatientMedicalInfoClassicRepository.save(inPatientMedicalInfo);
    }
    @Override
    @Transactional
    public InPatientMedicalInfo update(InPatientMedicalInfo inPatientMedicalInfo) {
        return inPatientMedicalInfoClassicRepository.update(inPatientMedicalInfo);
    }

    @Override
    @Transactional
    public void removeLogical(InPatientMedicalInfo inPatientMedicalInfo) {
        inPatientMedicalInfoClassicRepository.removeLogical(inPatientMedicalInfo);
    }
    @Override
    public List<InPatientMedicalInfo> findInPatientMedicalInfoByRemoveDateTimeIsNull() {
        return inPatientMedicalInfoRepository.findInPatientMedicalInfoByRemoveDateTimeIsNull();
    }
    @Override
    @Transactional
    public List<InPatientMedicalInfo> saveList(Long inPatientId, List<InPatientMedicalInfo> medicineDetailList) {
        return inPatientMedicalInfoClassicRepository.saveList(inPatientId , medicineDetailList);
    }
}
