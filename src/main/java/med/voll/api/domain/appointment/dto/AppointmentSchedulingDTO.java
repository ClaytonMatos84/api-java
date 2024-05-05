package med.voll.api.domain.appointment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.entity.Specialty;

import java.time.LocalDateTime;

public record AppointmentSchedulingDTO(Long idDoctor,
                                       @NotNull Long idPatient,
                                       @NotNull @Future LocalDateTime date,
                                       Specialty specialty) {
}
