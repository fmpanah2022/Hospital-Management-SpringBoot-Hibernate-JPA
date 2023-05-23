package org.j2os.service.Implementation;

import org.j2os.domain.View.InPatientVew;
import org.j2os.domain.entity.InPatient;
import org.j2os.domain.entity.InPatientBill;
import org.j2os.domain.entity.OutPatient;
import org.j2os.repository.Classic.InPatientClassicRepository;
import org.j2os.repository.modern.*;
import org.j2os.service.modern.InPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InPatientServiceImp implements InPatientService {
    @Autowired
    private InPatientRepository inPatientRepository;
    @Autowired
    private InPatientClassicRepository inPatientClassicRepository;
    @Autowired
    private InPatientMedicalInfoRepository inPatientMedicalInfoRepository;
    @Autowired
    private InPatientBillRepository inPatientBillRepository;
    @Autowired
    private InPatientServiceInfoRepository inPatientServiceInfoRepository;
    @Autowired
    private OutPatientRepository outPatientRepository;
//*************************************************************************
    @Override
    public boolean existsInPatient(InPatient inPatient) {
        return false;
       /* java.sql.Date inPatientDate = new java.sql.Date(inPatient.getInPatientDate().getTime());
        return (inPatientRepository.findInPatientByPatient_Person_NationalCodeAndInPatientDate_DateAndRemoveDateTimeIsNull(
                                                       inPatient.getPatient().getPerson().getNationalCode() ,inPatientDate) != null);*/
    }
    @Override
    public boolean conflictInPatient(InPatient inPatient) {
        return false;
      /*  java.sql.Date inPatientDate = new java.sql.Date(inPatient.getInPatientDate().getTime());
        InPatient inPatient1 = inPatientRepository.findInPatientByPatient_Person_NationalCodeAndInPatientDate_DateAndRemoveDateTimeIsNull(
                                                                      inPatient.getPatient().getPerson().getNationalCode() ,inPatientDate);
        if  (  inPatient1 != null && inPatient1.getInPatientId() != null)
            return( inPatient1.getInPatientId() != inPatient.getInPatientId()) ;
        else return false;*/
    }

    @Override
    public boolean canRemove(InPatient inPatient) {
        Long inPatientId = inPatient.getInPatientId();
        if (! inPatientMedicalInfoRepository.findInPatientMedicalInfoByInPatientIdAndRemoveDateTimeIsNull(inPatientId).isEmpty() ||
              inPatientServiceInfoRepository.findInPatientServiceInfoByInPatientIdAndRemoveDateTimeIsNull(inPatientId) != null ||
              inPatientBillRepository.findInPatientBillByInPatient_InPatientIdAndRemoveDateTimeIsNull(inPatientId) != null ||
              outPatientRepository.findOutPatientByInPatient_InPatientIdAndRemoveDateTimeIsNull(inPatientId) != null  )
            return false;
        else return true;
    }
    @Override
    public List<InPatient> findInPatientsByPatient_Person_NationalCodeAndRemoveDateTimeIsNull(int nationalCode) {
        return inPatientRepository.findInPatientsByPatient_Person_NationalCodeAndRemoveDateTimeIsNull(nationalCode);
    }

    @Override
    public Map<String, Object> convertInPatientToMap(InPatient inPatient) {
        Map<String, Object> data = new HashMap<>();
        data.put("inPatientId",inPatient.getInPatientId());
        data.put("inPatientDate", inPatient.getInPatientDate());
        data.put("roomId", inPatient.getRoom().getRoomId());
        data.put("roomNumber", inPatient.getRoom().getRoomNumber());
        data.put("roomCost", inPatient.getRoom().getRoomCost());
        data.put("patientId",inPatient.getPatient().getPatientId());
        data.put("patientNationalCode",inPatient.getPatient().getPerson().getNationalCode());
        data.put("patientName",inPatient.getPatient().getPerson().getSurname() + " " + inPatient.getPatient().getPerson().getFirstName());

        data.put("doctorId", inPatient.getDoctor().getDoctorId());
        data.put("doctorNationalCode",inPatient.getDoctor().getPerson().getNationalCode());
        data.put("doctorName",inPatient.getDoctor().getPerson().getSurname()+ " " + inPatient.getDoctor().getPerson().getFirstName());
        data.put("doctorPersonalCode", inPatient.getDoctor().getPersonalCode());

        data.put("staffId", inPatient.getStaff().getStaffId());
        data.put("staffNationalCode",inPatient.getStaff().getPerson().getNationalCode());
        data.put("staffName",inPatient.getStaff().getPerson().getSurname()+ " " + inPatient.getStaff().getPerson().getFirstName());
        data.put("staffPersonalCode", inPatient.getStaff().getPersonalCode());
        data.put("inPatientVno", inPatient.getVersionNumber());
        data.put("inPatientVno", inPatient.getVersionNumber());
        return data;
    }

    @Override
    public List<Map<String, Object>> convertInPatientListToMapList(List<InPatient> inPatients) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (InPatient inPatient : inPatients) {
            Map<String, Object> data = new HashMap<>();
            data.put("inPatientId",inPatient.getInPatientId());
            data.put("inPatientDate", inPatient.getInPatientDate());
            data.put("roomId", inPatient.getRoom().getRoomId());
            data.put("roomNumber", inPatient.getRoom().getRoomNumber());
            data.put("roomCost", inPatient.getRoom().getRoomCost());
            data.put("patientId",inPatient.getPatient().getPatientId());
            data.put("patientNationalCode",inPatient.getPatient().getPerson().getNationalCode());
            data.put("patientName",inPatient.getPatient().getPerson().getSurname() + " " + inPatient.getPatient().getPerson().getFirstName());

            data.put("doctorId", inPatient.getDoctor().getDoctorId());
            data.put("doctorNationalCode",inPatient.getDoctor().getPerson().getNationalCode());
            data.put("doctorName",inPatient.getDoctor().getPerson().getSurname()+ " " + inPatient.getDoctor().getPerson().getFirstName());
            data.put("doctorPersonalCode", inPatient.getDoctor().getPersonalCode());

            data.put("staffId", inPatient.getStaff().getStaffId());
            data.put("staffNationalCode",inPatient.getStaff().getPerson().getNationalCode());
            data.put("staffName",inPatient.getStaff().getPerson().getSurname()+ " " + inPatient.getStaff().getPerson().getFirstName());
            data.put("staffPersonalCode", inPatient.getStaff().getPersonalCode());
            data.put("inPatientVno", inPatient.getVersionNumber());
            dataList.add(data);
        }
        return dataList;
    }

    @Override
    public Optional<InPatient> findByInPatientIdAndRemoveDateTimeIsNull(Long patientId) {
        return inPatientRepository.findByInPatientIdAndRemoveDateTimeIsNull(patientId);
    }
    @Override
    public List<InPatient> findInPatientsByRemoveDateTimeIsNull() {
        return inPatientRepository.findInPatientsByRemoveDateTimeIsNull();
    }
    @Override
    public List<InPatient> findInPatientsByDoctor_DoctorIdAndRemoveDateTimeIsNull(Long doctorId) {
        return inPatientRepository.findInPatientsByDoctor_DoctorIdAndRemoveDateTimeIsNull(doctorId);
    }
    @Override
    public List<InPatient> findInPatientsByPatient_PatientIdAndRemoveDateTimeIsNull(Long patientId) {
        return inPatientRepository.findInPatientsByPatient_PatientIdAndRemoveDateTimeIsNull(patientId);
    }
    @Override
    public List<InPatient> findInPatientsByRoom_RoomIdAndRemoveDateTimeIsNull(Long roomId) {
        return inPatientRepository.findInPatientsByRoom_RoomIdAndRemoveDateTimeIsNull(roomId);
    }

    @Override
    public List<InPatient> findInPatientsByStaff_StaffIdAndRemoveDateTimeIsNull(Long staffId) {
        return inPatientRepository.findInPatientsByStaff_StaffIdAndRemoveDateTimeIsNull(staffId);
    }

    @Override
    public List<InPatientVew> findAllInPatients() {
        return inPatientClassicRepository.findAllInPatients();
    }

    @Override
    @Transactional
    public InPatient save(InPatient inPatient) {
        return inPatientClassicRepository.save(inPatient);
    }

    @Override
    @Transactional
    public InPatient saveWithMedicalInfo(InPatient inPatient, List<String> medicineCodeList) {
        return inPatientClassicRepository.saveWithMedicalInfo(inPatient,medicineCodeList);
    }

    @Override
    @Transactional
    public InPatient update(InPatient inPatient) {
        return inPatientClassicRepository.update(inPatient);
    }

    @Override
    @Transactional
    public void removeLogical(InPatient inPatient) {
        inPatientClassicRepository.removeLogical(inPatient);
    }

    public Optional<InPatient> findById(Long id){
        return inPatientRepository.findById(id);
    }
}
