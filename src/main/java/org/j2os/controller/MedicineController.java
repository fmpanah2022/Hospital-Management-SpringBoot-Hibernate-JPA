package org.j2os.controller;

import org.j2os.domain.entity.Medicine;
import org.j2os.service.modern.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    //*******************************************************
    @GetMapping("/medicines")
    public ResponseEntity<List<Map<String, Object>>> getAllMedicines() {
        try {
            List<Medicine> medicines = medicineService.findMedicinesByRemoveDateTimeIsNull();
            if (medicines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicineService.convertMedicineListToMapList(medicines), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByMedicineName")
    public ResponseEntity<Map<String, Object>> findByMedicineName(@RequestParam("medicineName") String medicineName) {
        try {
            Medicine medicine = medicineService.findMedicineByMedicineNameAndRemoveDateTimeIsNull(medicineName);
            if (medicine.getMedicineId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicineService.convertMedicineToMap(medicine), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByMedicineCode")
    public ResponseEntity<Map<String, Object>> findByMedicineCode(@RequestParam("medicineCode") String medicineCode) {
        try {
            Medicine medicine = medicineService.findMedicineByMedicineCodeAndRemoveDateTimeIsNull(medicineCode);
            if (medicine.getMedicineId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicineService.convertMedicineToMap(medicine), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByMedicineCostIsBetween")
    public ResponseEntity<List<Map<String, Object>>> findByMedicineCostIsBetween(@RequestParam("fromCost") String fromCost,
                                                                                 @RequestParam("toCost") String toCost) {
        try {
            List<Medicine> medicines = new ArrayList<Medicine>();
            medicines = medicineService.findMedicineByMedicineCostIsBetweenAndRemoveDateTimeIsNull(Integer.parseInt(fromCost) ,Integer.parseInt(toCost));
            if (medicines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicineService.convertMedicineListToMapList(medicines), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public ResponseEntity<Map<String, Object>>  saveMedicine(@RequestParam("medicineCode") String medicineCode ,
                                                  @RequestParam("medicineName") String medicineName ,
                                                  @RequestParam("medicineCost") String medicineCost) {
        try {
            Medicine medicine1 = new Medicine().builder().medicineCode(medicineCode).medicineName(medicineName).medicineCost(Integer.parseInt(medicineCost)).build();
            if (medicineService.existsMedicine(medicine1)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Medicine savedMedicine = medicineService.save(medicine1);
            if (savedMedicine.getMedicineId() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicineService.convertMedicineToMap(savedMedicine), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateMedicine(@RequestParam("medicineId") String medicineId,
                                          @RequestParam("medicineCode") String medicineCode ,
                                          @RequestParam("medicineName") String medicineName ,
                                          @RequestParam("medicineCost") String medicineCost,
                                          @RequestParam("medicineVno") String medicineVno) {
        Optional<Medicine> medicineData = medicineService.findByMedicineIdAndRemoveDateTimeIsNull(Long.valueOf(medicineId));
        if (medicineData.isPresent()) {
            Medicine medicine1 = medicineData.get();
            medicine1.builder().versionNumber(Integer.parseInt(medicineVno)).medicineCode(medicineCode).medicineId(Long.valueOf(medicineId)).medicineCost(Integer.parseInt(medicineCost)).medicineName(medicineName).build();
            if (medicineService.conflictMedicine(medicine1)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Medicine savedMedicine = medicineService.update(medicine1);
            return new ResponseEntity<>(medicineService.convertMedicineToMap(savedMedicine), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<HttpStatus> removeLogicalMedicine(@RequestParam("medicineId") String medicineId,
                                                            @RequestParam("medicineVno") String medicineVno) {
        Optional<Medicine> medicineData = medicineService.findByMedicineIdAndRemoveDateTimeIsNull(Long.valueOf(medicineId));
        if (medicineData.isPresent()) {
            Medicine medicine1 = medicineData.get();
            medicine1.setVersionNumber(Integer.parseInt(medicineVno));
            if ( !medicineService.canRemove(medicine1) ){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            medicineService.removeLogical(medicine1);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
