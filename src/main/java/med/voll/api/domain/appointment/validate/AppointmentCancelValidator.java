package med.voll.api.domain.appointment.validate;

import med.voll.api.domain.appointment.entity.Appointment;
import med.voll.api.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentCancelValidator {

    public void validator(Appointment appointment) {
        validateHourCancel(appointment.getDate());
    }

    private void validateHourCancel(LocalDateTime date) {
        if (Duration.between(LocalDateTime.now(), date).toHours() < 24) {
            throw new ApiException("Medical appointment must be cancel at least 24 hours in advance");
        }
    }

}
