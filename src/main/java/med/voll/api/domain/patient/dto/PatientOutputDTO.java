package med.voll.api.domain.patient.dto;

import med.voll.api.domain.patient.entity.Patient;

public record PatientOutputDTO(Long id, String name, String email, String cpf) {

    public PatientOutputDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
