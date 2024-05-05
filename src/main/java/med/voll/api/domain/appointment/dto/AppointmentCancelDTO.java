package med.voll.api.domain.appointment.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.appointment.entity.CancelReason;

public record AppointmentCancelDTO(@NotNull Long idAppointment,
                                   @NotNull CancelReason reason) {
}
