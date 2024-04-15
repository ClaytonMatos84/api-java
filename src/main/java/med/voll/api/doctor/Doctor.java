package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

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
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Doctor(DoctorDTO doctorDTO) {
        this.name = doctorDTO.name();
        this.email = doctorDTO.email();
        this.telephone = doctorDTO.telephone();
        this.crm = doctorDTO.crm();
        this.specialty = doctorDTO.specialty();
        this.address = new Address(doctorDTO.address());
    }

    public void partiallyUpdate(DoctorUpdateDTO doctorUpdateDTO) {
        if (doctorUpdateDTO.name() != null) this.name = doctorUpdateDTO.name();
        if (doctorUpdateDTO.telephone() != null) this.telephone = doctorUpdateDTO.telephone();
        if (doctorUpdateDTO.address() != null) this.address.partiallyUpdate(doctorUpdateDTO.address());
    }
}
