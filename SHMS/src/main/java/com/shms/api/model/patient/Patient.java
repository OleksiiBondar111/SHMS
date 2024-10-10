package com.shms.api.model.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.enums.Gender;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.insurance.Insurance;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
public class Patient extends TrackedEntity {

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(nullable = false, name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, name = "phone")
    private String phone;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "image_id")
    private String imageId;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    public Patient(PatientDTO patientDTO) {
        this.id = patientDTO.getId();
        this.firstName = patientDTO.getFirstName();
        this.lastName = patientDTO.getLastName();
        this.dob = patientDTO.getDob();
        this.gender = patientDTO.getGender();
        this.phone = patientDTO.getPhone();
        this.email = patientDTO.getEmail();
        this.address = patientDTO.getAddress();
        this.status = patientDTO.getStatus();
        this.imageId = patientDTO.getImageId();
        if (patientDTO.getInsurance() != null) {
            this.insurance = new Insurance(patientDTO.getInsurance());
        }
    }

}
