package com.shms.api.initial;


import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.mapper.*;
import com.shms.api.model.appointemnt.Appointment;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.model.insurance.Insurance;
import com.shms.api.model.invoice.Invoice;
import com.shms.api.model.medicalRecord.MedicalRecord;
import com.shms.api.model.patient.Patient;
import com.shms.api.model.payment.Payment;
import com.shms.api.model.testResult.TestResult;
import com.shms.api.service.EntityService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "data.seed.enabled", havingValue = "true")
public class InitialSchemaDataSetup extends EntitiesInitialization {

    private final EntityService<Insurance, InsuranceDTO> insuranceService;
    private final InsuranceMapper insuranceMapper;
    private final EntityService<Doctor, DoctorDTO> doctorService;
    private final DoctorMapper doctorMapper;
    private final EntityService<Patient, PatientDTO> patientService;
    private final PatientMapper patientMapper;
    private final EntityService<Appointment, AppointmentDTO> appointmentService;
    private final AppointmentMapper appointmentMapper;
    private final EntityService<MedicalRecord, MedicalRecordDTO> medicalRecordService;
    private final MedicalRecordMapper medicalRecordMapper;
    private final EntityService<TestResult, TestResultDTO> testResultService;
    private final TestResultMapper testResultMapper;
    private final EntityService<Payment, PaymentDTO> paymentService;
    private final PaymentMapper paymentMapper;
    private final EntityService<Invoice, InvoiceDTO> invoiceService;
    private final InvoiceMapper invoiceMapper;

    @Transactional
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
    
        log.info("From Application Ready Event");
        for (int i = 0; i < 100; i++) {
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insuranceService.create(getRandomInsurance()));
        PatientDTO patientDTO = patientMapper.toDto(patientService.create(getRandomPatient(insuranceDTO)));
        DoctorDTO doctorDTO = doctorMapper.toDto(doctorService.create(getRandomDoctor()));
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointmentService.create(getRandomAppointment(doctorDTO, patientDTO)));
        MedicalRecordDTO medicalRecordDTO = medicalRecordMapper.toDto(medicalRecordService.create(getRandomMedicalRecord(doctorDTO, patientDTO)));
        testResultMapper.toDto(testResultService.create(generateTestResult(medicalRecordDTO)));
        InvoiceDTO invoiceDTO = invoiceMapper.toDto(invoiceService.create(generateInvoiceDTO(patientDTO, appointmentDTO)));
        paymentMapper.toDto(paymentService.create(generatePaymentDTO(invoiceDTO)));            
        }
    }


}
