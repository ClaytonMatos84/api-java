package med.voll.api.model.doctor.dto;

import med.voll.api.model.doctor.entity.Doctor;
import med.voll.api.model.doctor.entity.Specialty;

public record DoctorOutputDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorOutputDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
