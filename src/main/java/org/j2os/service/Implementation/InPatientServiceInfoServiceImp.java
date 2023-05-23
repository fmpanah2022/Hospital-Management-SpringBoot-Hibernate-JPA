package org.j2os.service.Implementation;

import org.j2os.domain.entity.InPatientServiceInfo;
import org.j2os.domain.View.InPatientServiceInfoView;
import org.j2os.repository.Classic.InPatientServiceInfoClassicRepository;
import org.j2os.repository.modern.InPatientServiceInfoRepository;
import org.j2os.service.modern.InPatientServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InPatientServiceInfoServiceImp implements InPatientServiceInfoService {
    @Autowired
    private InPatientServiceInfoRepository inPatientServiceInfoRepository;
    @Autowired
    private InPatientServiceInfoClassicRepository inPatientServiceInfoClassicRepository;
    //--------------------------------------------------------------------------------------------------------
    @Override
    public List<Map<String, Object>> findServiceInfoByServiceId(Long serviceId) {
        return convertInPatientServiceInfoListToMapList( inPatientServiceInfoClassicRepository.findServiceInfoByServiceId(serviceId));
    }
    @Override
    public List<Map<String, Object>> findInPatientServiceInfosByInPatientId(Long inPatientId) {
        return convertInPatientServiceInfoListToMapList(inPatientServiceInfoClassicRepository.findServiceInfoByInPatientIdWithDetail(inPatientId));
    }
    @Override
    public Map<String, Object> findInPatientServiceInfoById(Long inPatientServiceInfoId) {
        return convertInPatientServiceInfoViewToMap(inPatientServiceInfoClassicRepository.findServiceInfoByIdWithDetail(inPatientServiceInfoId));
    }
    @Override
    public Map<String, Object> convertInPatientServiceInfoViewToMap(InPatientServiceInfoView inServiceInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("inPatientServiceInfoId",inServiceInfo.getInPatientServiceInfoId());
        data.put("inPatientId",inServiceInfo.getInPatientId());
        data.put("serviceId", inServiceInfo.getServiceId());
        data.put("serviceUnitCost", inServiceInfo.getServiceUnitCost());
        data.put("count", inServiceInfo.getCount());
        data.put("serviceName", inServiceInfo.getServiceName());
        data.put("totalCost", inServiceInfo.getTotalCost());
        data.put("versionNumber", inServiceInfo.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertInPatientServiceInfoListToMapList(List<InPatientServiceInfoView> inServiceInfos) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (InPatientServiceInfoView inServiceInfo : inServiceInfos) {
            Map<String, Object> data = new HashMap<>();
            data.put("inPatientServiceInfoId",inServiceInfo.getInPatientServiceInfoId());
            data.put("inPatientId",inServiceInfo.getInPatientId());
            data.put("serviceId", inServiceInfo.getServiceId());
            data.put("serviceUnitCost", inServiceInfo.getServiceUnitCost());
            data.put("count", inServiceInfo.getCount());
            data.put("serviceName", inServiceInfo.getServiceName());
            data.put("totalCost", inServiceInfo.getTotalCost());
            data.put("versionNumber", inServiceInfo.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }
    @Override
    public List<InPatientServiceInfo> findInPatientServiceInfosByServiceIdAndRemoveDateTimeIsNull(Long serviceId) {
        return inPatientServiceInfoRepository.findInPatientServiceInfosByServiceIdAndRemoveDateTimeIsNull(serviceId);
    }

    @Override
    public List<InPatientServiceInfo> findInPatientServiceInfoByInPatientIdAndRemoveDateTimeIsNull(Long inPatientId) {
        return inPatientServiceInfoRepository.findInPatientServiceInfoByInPatientIdAndRemoveDateTimeIsNull(inPatientId);
    }

    @Override
    public List<InPatientServiceInfo> findInPatientServiceInfoByRemoveDateTimeIsNull() {
        return inPatientServiceInfoRepository.findInPatientServiceInfoByRemoveDateTimeIsNull();
    }

    @Override
    public Optional<InPatientServiceInfo> findInPatientServiceInfoByInPatientServiceInfoIdAndRemoveDateTimeIsNull(Long inPatientServiceInfoId) {
        return inPatientServiceInfoRepository.findInPatientServiceInfoByInPatientServiceInfoIdAndRemoveDateTimeIsNull(inPatientServiceInfoId);
    }

    @Override
    @Transactional
    public InPatientServiceInfo save(InPatientServiceInfo inPatientServiceInfo) {
        return inPatientServiceInfoClassicRepository.save(inPatientServiceInfo);
    }

    @Override
    @Transactional
    public List<InPatientServiceInfo> saveList(Long inPatientId, List<InPatientServiceInfo> inPatientServiceInfos) {
        return inPatientServiceInfoClassicRepository.saveList(inPatientId,inPatientServiceInfos);
    }

    @Override
    @Transactional
    public InPatientServiceInfo update(InPatientServiceInfo inPatientServiceInfo) {
        return inPatientServiceInfoClassicRepository.update(inPatientServiceInfo);
    }

    @Override
    @Transactional
    public void removeLogical(InPatientServiceInfo inPatientServiceInfo) {
        inPatientServiceInfoClassicRepository.removeLogical(inPatientServiceInfo);
    }
// have to check
    @Override
    public boolean canRemove(InPatientServiceInfo inPatientServiceInfo) {
        return true;
    }
}
