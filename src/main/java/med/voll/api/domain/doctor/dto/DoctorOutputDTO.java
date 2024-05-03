package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.doctor.entity.Doctor;
import med.voll.api.domain.doctor.entity.Specialty;

public record DoctorOutputDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorOutputDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
