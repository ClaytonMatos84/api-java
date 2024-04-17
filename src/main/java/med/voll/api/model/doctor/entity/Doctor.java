package med.voll.api.model.doctor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.address.entity.Address;
import med.voll.api.model.doctor.dto.DoctorDTO;
import med.voll.api.model.doctor.dto.DoctorUpdateDTO;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Doctor(DoctorDTO doctorDTO) {
        this.name = doctorDTO.name();
        this.email = doctorDTO.email();
        this.telephone = doctorDTO.telephone();
        this.crm = doctorDTO.crm();
        this.active = true;
        this.specialty = doctorDTO.specialty();
        this.address = new Address(doctorDTO.address());
    }

    public Doctor(DoctorUpdateDTO doctorUpdateDTO) {
        this.name = doctorUpdateDTO.name();
        this.telephone = doctorUpdateDTO.telephone();
        this.address = new Address(doctorUpdateDTO.address());
    }

    public static Doctor parseDoctor(DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = new Doctor();
        if (doctorUpdateDTO.name() != null) doctor.name = doctorUpdateDTO.name();
        if (doctorUpdateDTO.telephone() != null) doctor.telephone = doctorUpdateDTO.telephone();
        if (doctorUpdateDTO.address() != null) doctor.address = new Address(doctorUpdateDTO.address());

        return doctor;
    }

    public void partiallyUpdate(Doctor doctor) {
        if (doctor.getName() != null) this.name = doctor.getName();
        if (doctor.getTelephone() != null) this.telephone = doctor.getTelephone();
        if (doctor.getAddress() != null) this.address.partiallyUpdate(doctor.getAddress());
    }

    public void remove() {
        this.active = false;
    }
}
