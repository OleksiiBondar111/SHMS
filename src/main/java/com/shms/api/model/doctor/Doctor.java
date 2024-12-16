package com.shms.api.model.doctor;

import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.model.TrackedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
public class Doctor extends TrackedEntity {

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "phone")
    private String phone;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(name = "office_address")
    private String officeAddress;

    @Column(name = "status")
    private String status;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "image_id")
    private String imageId;

    @Column(name = "availability")
    private String availability;

    public Doctor(DoctorDTO doctorDTO) {
        this.id = doctorDTO.getId();
        this.firstName = doctorDTO.getFirstName();
        this.lastName = doctorDTO.getLastName();
        this.phone = doctorDTO.getPhone();
        this.email = doctorDTO.getEmail();
        this.officeAddress = doctorDTO.getOfficeAddress();
        this.status = doctorDTO.getStatus();
        this.imageId = doctorDTO.getImageId();
        this.availability = doctorDTO.getAvailability();
        this.specialty = doctorDTO.getSpecialty();
    }

}
