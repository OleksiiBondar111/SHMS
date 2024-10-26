package com.shms.api.initial;

import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.enums.Gender;
import com.shms.api.enums.InvoiceStatus;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class EntitiesInitialization {
    
    

    protected InsuranceDTO getRandomInsurance() {
        InsuranceDTO insuranceDTO = new InsuranceDTO();
        insuranceDTO.setCoverageDetails(RandomStringUtils.random(15, true, true));
        insuranceDTO.setPolicyNumber(RandomStringUtils.random(15, true, true));
        insuranceDTO.setProviderName(RandomStringUtils.random(15, true, true));
        return insuranceDTO;
    }

    protected PatientDTO getRandomPatient(InsuranceDTO insuranceDTO) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress(RandomStringUtils.random(15, true, true));
        patientDTO.setEmail(RandomStringUtils.random(15, true, true).concat("@shms.com"));
        patientDTO.setFirstName(RandomStringUtils.random(15, true, true));
        patientDTO.setLastName(RandomStringUtils.random(15, true, true));
        patientDTO.setGender(Gender.F);
        patientDTO.setInsurance(insuranceDTO);
        patientDTO.setDob(new Date());
        return patientDTO;
    }

    protected DoctorDTO getRandomDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setAvailability(RandomStringUtils.random(15, true, true));
        doctorDTO.setFirstName(RandomStringUtils.random(15, true, true));
        doctorDTO.setLastName(RandomStringUtils.random(15, true, true));
        doctorDTO.setEmail(RandomStringUtils.random(15, true, true).concat("@shms.com"));
        doctorDTO.setAvailability(RandomStringUtils.random(15, true, true));
        doctorDTO.setSpecialty(RandomStringUtils.random(15, true, true));
        doctorDTO.setOfficeAddress(RandomStringUtils.random(15, true, true));
        return doctorDTO;
    }

    protected AppointmentDTO getRandomAppointment(DoctorDTO doctorDTO, PatientDTO patientDTO) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoctor(doctorDTO);
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setAppointmentDate(new Date());
        appointmentDTO.setReason(RandomStringUtils.random(15, true, true));
        return appointmentDTO;
    }

    protected MedicalRecordDTO getRandomMedicalRecord(DoctorDTO doctorDTO, PatientDTO patientDTO) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setDoctor(doctorDTO);
        medicalRecordDTO.setPatient(patientDTO);
        medicalRecordDTO.setDiagnosis(RandomStringUtils.random(15, true, true));
        medicalRecordDTO.setPrescription(RandomStringUtils.random(15, true, true));
        return medicalRecordDTO;

    }

    protected TestResultDTO generateTestResult(MedicalRecordDTO medicalRecordDTO) {
        TestResultDTO testResultDTO = new TestResultDTO();
        testResultDTO.setTestName(RandomStringUtils.random(15, true, true));
        testResultDTO.setTestResult(RandomStringUtils.random(15, true, true));
        testResultDTO.setTestDate(new Date());
        testResultDTO.setMedicalRecord(medicalRecordDTO);
        return testResultDTO;

    }

    protected InvoiceDTO generateInvoiceDTO(PatientDTO patientDTO, AppointmentDTO appointmentDTO) {
       InvoiceDTO invoiceDTO = new InvoiceDTO();
       invoiceDTO.setPatient(patientDTO);
       invoiceDTO.setStatus(InvoiceStatus.Paid);
       invoiceDTO.setAppointment(appointmentDTO);
       invoiceDTO.setTotalAmount(BigDecimal.valueOf(new Random().nextDouble()));
       invoiceDTO.setDueDate(new Date());
       invoiceDTO.setIssueDate(new Date());
       return invoiceDTO;
    }

    protected PaymentDTO generatePaymentDTO(InvoiceDTO invoiceDTO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentAmount(BigDecimal.valueOf(new Random().nextDouble()));
        paymentDTO.setPaymentDate(new Date());
        paymentDTO.setPaymentMethod(RandomStringUtils.random(15, true, true));
        paymentDTO.setInvoice(invoiceDTO);
        return paymentDTO;
    }

}
