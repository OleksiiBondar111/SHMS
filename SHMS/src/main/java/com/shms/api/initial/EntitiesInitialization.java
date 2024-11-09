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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class EntitiesInitialization {

    private static final List<String> personsFirstNames = Arrays.asList("Messi", "Suarez", "Muller", "Kroos");
    private static final List<String> personsSecondNames = Arrays.asList("Leo", "Andy", "Thomas", "Tony");
    private static final List<String> insuranceCompanies = Arrays.asList("Allianz", "IKK");
    private static final List<String> addresses = Arrays.asList("Weyergrafweg 11", "Am Pfad 3", "Hochegrabenweg 82");
    private static final List<String> specialities = Arrays.asList("Surgeon", "Therapist", "Otolaryngologist");
    private static final List<String> reasons = Arrays.asList("Head Ache", "Cold", "Flu");
    private static final List<String> diagnosis = Arrays.asList("Flu", "COVID", "Broken Arm");
    private static final Random generator = new Random();


    protected InsuranceDTO getRandomInsurance() {
        InsuranceDTO insuranceDTO = new InsuranceDTO();
        insuranceDTO.setCoverageDetails(RandomStringUtils.random(15, true, true));
        insuranceDTO.setPolicyNumber(RandomStringUtils.random(15, true, true));
        insuranceDTO.setProviderName(insuranceCompanies.get(generator.nextInt(insuranceCompanies.size())));
        return insuranceDTO;
    }

    protected PatientDTO getRandomPatient(InsuranceDTO insuranceDTO) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName(personsFirstNames.get(generator.nextInt(personsFirstNames.size())));
        patientDTO.setLastName(personsSecondNames.get(generator.nextInt(personsSecondNames.size())));
        patientDTO.setEmail(personsSecondNames.get(generator.nextInt(personsSecondNames.size())).concat("@shms.com"));
        patientDTO.setAddress(addresses.get(generator.nextInt(addresses.size())));
        patientDTO.setGender(Gender.F);
        patientDTO.setInsurance(insuranceDTO);
        patientDTO.setDob(new Date(System.currentTimeMillis() - generator.nextInt()));
        return patientDTO;
    }

    protected DoctorDTO getRandomDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setAvailability(RandomStringUtils.random(15, true, true));
        doctorDTO.setFirstName(personsFirstNames.get(generator.nextInt(personsFirstNames.size())));
        doctorDTO.setLastName(personsSecondNames.get(generator.nextInt(personsSecondNames.size())));
        doctorDTO.setEmail(personsSecondNames.get(generator.nextInt(personsSecondNames.size())).concat("@shms.com"));
        doctorDTO.setAvailability(RandomStringUtils.random(15, true, true));
        doctorDTO.setSpecialty(specialities.get(generator.nextInt(specialities.size())));
        doctorDTO.setOfficeAddress(addresses.get(generator.nextInt(addresses.size())));
        return doctorDTO;
    }

    protected AppointmentDTO getRandomAppointment(DoctorDTO doctorDTO, PatientDTO patientDTO) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoctor(doctorDTO);
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setAppointmentDate(new Date(System.currentTimeMillis() + generator.nextInt()));
        appointmentDTO.setReason(reasons.get(generator.nextInt(reasons.size())));
        return appointmentDTO;
    }

    protected MedicalRecordDTO getRandomMedicalRecord(DoctorDTO doctorDTO, PatientDTO patientDTO) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setDoctor(doctorDTO);
        medicalRecordDTO.setPatient(patientDTO);
        medicalRecordDTO.setDiagnosis(diagnosis.get(generator.nextInt(diagnosis.size())));
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
