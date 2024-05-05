package med.voll.api.domain.appointment.dto;

import med.voll.api.domain.doctor.entity.Specialty;

import java.time.LocalDateTime;

public record AppointmentSchedullingOutputDTO(Long idDoctor,
                                              Long idPatient,
                                              LocalDateTime date,
                                              Specialty specialty) {
}
