package org.j2os.common;

import org.hibernate.integrator.spi.Integrator;
import org.j2os.domain.entity.*;

import java.util.Date;

public class ObjectMapper {
    public ObjectMapper() {
    }
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }
//******************************************************************************
    public Cashier mapToCashier(String firstName, String surname , String nationalCode  , String genderId , String userName , String password  ) {
        Gender gender = new Gender().builder().
                genderId(Long.valueOf(genderId)).
                build();

        Users user = new Users().builder().
                userName(userName).
                password(password).
                build();

        Person person = new Person().builder().
                firstName(firstName).
                surname(surname).
                nationalCode(Integer.parseInt(nationalCode)).
                gender(gender).
                user(user).
                build();

        return new Cashier().builder().
                person(person).
                build();
       }

    public Cashier mapToCashier(String cashierId ,String firstName, String surname , String nationalCode  , String genderId ,String userId, String password , String userVno , String personVno , String cashierVno) {
        Gender gender = new Gender().builder().
                genderId(Long.valueOf(genderId)).
                build();

        Users user = new Users().builder().
                userId(Long.parseLong(userId)).
                password(password).
                build();

        Person person = new Person().builder().
                versionNumber(Integer.parseInt(personVno)).
                firstName(firstName).
                surname(surname).
                nationalCode(Integer.parseInt(nationalCode)).
                gender(gender).
                user(user).
                build();

        return new Cashier().builder().
                cashierId(Long.valueOf(cashierId)).
                versionNumber(Integer.parseInt(cashierVno)).
                person(person).
                build();
    }

    public Doctor mapToDoctor(String firstName, String surname , String nationalCode  , String genderId , String userName , String password  ) {
        Gender gender = new Gender().builder().
                genderId(Long.valueOf(genderId)).
                build();

        Users user = new Users().builder().
                userName(userName).
                password(password).
                build();

        Person person = new Person().builder().
                firstName(firstName).
                surname(surname).
                nationalCode(Integer.parseInt(nationalCode)).
                gender(gender).
                user(user).
                build();

        return new Doctor().builder().
                person(person).
                build();
    }

    public Doctor mapToDoctor(String doctorId ,String firstName, String surname , String nationalCode  , String genderId ,String userId, String password , String userVno , String personVno , String doctorVno) {
        Gender gender = new Gender().builder().
                genderId(Long.valueOf(genderId)).
                build();

        Users user = new Users().builder().
                userId(Long.parseLong(userId)).
                password(password).
                build();

        Person person = new Person().builder().
                versionNumber(Integer.parseInt(personVno)).
                firstName(firstName).
                surname(surname).
                nationalCode(Integer.parseInt(nationalCode)).
                gender(gender).
                user(user).
                build();

        return new Doctor().builder().
                doctorId(Long.valueOf(doctorId)).
                versionNumber(Integer.parseInt(doctorVno)).
                person(person).
                build();
    }

    //***** InPatientBill
    public InPatientBill mapToInPatientBill(String staffId ,String inPatientId) {
        Staff staff = new Staff().builder().staffId(Long.valueOf(staffId)).build();
        InPatient inPatient = new InPatient().builder().inPatientId(Long.valueOf(inPatientId)).build();
        InPatientBill inPatientBill1 = new InPatientBill().builder().inPatientBillDate(new java.sql.Timestamp(new Date().getTime())).
                                                                     staff(staff).inPatient(inPatient).
                                                                     build();
        return inPatientBill1;
    }

    //****  InPatientBillDetail
    public InPatientBillDetail mapToInPatientBillDetail(String inPatientBillId ,String serviceId , String serviceCount, String serviceUnitCost) {
        InPatientBillDetail detail = new InPatientBillDetail().builder().
                inPatientBillId(Long.valueOf(inPatientBillId)).
                serviceId(Long.valueOf(serviceId)).
                serviceCount(Integer.parseInt(serviceCount)).
                serviceUnitCost(Integer.parseInt(serviceUnitCost)).
                build();

        return detail;
    }
    //****  InPatient
    public InPatient mapToInPatient(String staffId , String doctorId , String roomId , String patientId) {
        // date sets from system date and set available room to '0'
        Staff staff = new Staff().builder().staffId(Long.valueOf(staffId)).build();
        Doctor doctor = new Doctor().builder().doctorId(Long.valueOf(doctorId)).build();
        Room room = new Room().builder().roomId(Long.valueOf(roomId)).available('0').build();
        Patient patient = new Patient().builder().patientId(Long.valueOf(patientId)).build();

        InPatient inPatient = new InPatient().builder().
                patient(patient).staff(staff).
                doctor(doctor).room(room).
                inPatientDate(new java.sql.Timestamp(new Date().getTime())).build();
        return inPatient;
    }

    //********  OutPatient
    public OutPatient mapToOutPatient(String inPatientId , String doctorId){
        InPatient inPatient = new InPatient().builder().inPatientId(Long.valueOf(inPatientId)).build();
        Doctor doctor = new Doctor().builder().doctorId(Long.valueOf(doctorId)).build();

        OutPatient outPatient = new OutPatient().builder().
                inPatient(inPatient).
                doctor(doctor).build();
        return outPatient;
    }
    //********  Patient
    public Patient mapToPatient(String firstName, String surname , String nationalCode  , String genderId ) {
        Gender gender = new Gender().builder().
                genderId(Long.valueOf(genderId)).
                build();
        Person person = new Person().builder().
                firstName(firstName).
                surname(surname).
                nationalCode(Integer.parseInt(nationalCode)).
                gender(gender).
                build();

        return new Patient().builder().
                person(person).
                build();
    }
    //********  Payment
    public Payment mapToPayment(String cashierId, String paymentTypeId , String paymentAmount , String payer , String inPatientBillId){
        Cashier cashier = new Cashier().builder().cashierId(Long.valueOf(cashierId)).build();
        PaymentType paymentType = new PaymentType().builder().paymentTypeId(Long.valueOf(paymentTypeId)).build();
        Payment payment = new Payment().builder().
                paymentDate(new java.sql.Timestamp(new Date().getTime())).
                paymentType(paymentType).
                cashier(cashier).
                paymentAmount(Long.parseLong(paymentAmount)).payer(payer).inPatientBillId(Long.valueOf(inPatientBillId)).build();
        return payment;
    }

    //********  Staff
    public Staff mapToPaymentStaff(String firstName, String surname , String nationalCode , String genderId , String userName , String password){
        Gender gender = new Gender().builder().genderId(Long.valueOf(genderId)).build();
        Users user = new Users().builder().userName(userName).password(password).build();
        Person person = new Person().builder().firstName(firstName).user(user).surname(surname).nationalCode(Integer.parseInt(nationalCode)).gender(gender).build();
        Staff staff = new Staff().builder().person(person).build();
        return staff;
    }



}
