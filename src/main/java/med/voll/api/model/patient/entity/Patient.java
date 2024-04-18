package med.voll.api.model.patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.address.entity.Address;
import med.voll.api.model.patient.dto.PatientDTO;
import med.voll.api.model.patient.dto.PatientUpdateDTO;

@Table(name = "patients")
@Entity(name = "patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean active;

    public Patient(PatientDTO patientDTO) {
        this.name = patientDTO.name();
        this.email = patientDTO.email();
        this.telephone = patientDTO.telephone();
        this.cpf = patientDTO.cpf();
        this.address = new Address(patientDTO.address());
        this.active = true;
    }

    public static Patient parsePatient(PatientUpdateDTO patientUpdateDTO) {
        Patient patient = new Patient();
        if (patientUpdateDTO.name() != null) patient.name = patientUpdateDTO.name();
        if (patientUpdateDTO.telephone() != null) patient.telephone = patient.getTelephone();
        if (patientUpdateDTO.address() != null) patient.address = new Address(patientUpdateDTO.address());

        return patient;
    }

    public void partiallyUpdate(Patient patient) {
        if (patient.getName() != null) this.name = patient.getName();
        if (patient.getTelephone() != null) this.telephone = patient.getTelephone();
        if (patient.getAddress() != null) this.address.partiallyUpdate(patient.getAddress());
    }

}
