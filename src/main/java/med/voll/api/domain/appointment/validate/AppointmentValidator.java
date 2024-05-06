package med.voll.api.domain.appointment.validate;

import lombok.AllArgsConstructor;
import med.voll.api.domain.appointment.entity.Appointment;
import med.voll.api.domain.appointment.repository.AppointmentRepository;
import med.voll.api.domain.doctor.entity.Doctor;
import med.voll.api.domain.patient.entity.Patient;
import med.voll.api.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class AppointmentValidator {

    private final AppointmentRepository appointmentRepository;

    public void validator(Appointment appointment) {
        validateDate(appointment.getDate());
        validateHourScheduled(appointment.getDate());
        validateActiveDoctor(appointment.getDoctor());
        validateActivePatient(appointment.getPatient());
        validateDoctorWithOtherAppointmentInSameHour(appointment.getDoctor().getId(), appointment.getDate());
        validatePatientWithOtherAppointmentInSameDay(appointment.getPatient().getId(), appointment.getDate());
    }

    private void validateDate(LocalDateTime date) {
        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                date.getHour() < 7 ||
                date.getHour() > 18) {
            throw new ApiException("Medical appointment outside opening hours");
        }
    }

    private void validateHourScheduled(LocalDateTime date) {
        if (Duration.between(LocalDateTime.now(), date).toMinutes() < 30) {
            throw new ApiException("Medical appointment must be scheduled at least 30 minutes in advance");
        }
    }

    private void validateActiveDoctor(Doctor doctor) {
        if (!doctor.getActive()) {
            throw new ApiException("Doctor have be active.");
        }
    }

    private void validateActivePatient(Patient patient) {
        if (!patient.getActive()) {
            throw new ApiException("Patient have be active.");
        }
    }

    private void validateDoctorWithOtherAppointmentInSameHour(Long idDoctor, LocalDateTime date) {
        if (appointmentRepository.existsByDoctorIdAndDateAndCancelReasonIsNull(idDoctor, date)) {
            throw new ApiException("Doctor already has an appointment at that time.");
        }
    }

    private void validatePatientWithOtherAppointmentInSameDay(Long idPatient, LocalDateTime date) {
        LocalDateTime firstHour = date.withHour(7);
        LocalDateTime lastHour = date.withHour(18);
        if (appointmentRepository.existsByPatientIdAndDateBetween(idPatient, firstHour, lastHour)) {
            throw new ApiException("Patient already has an appointment at day.");
        }
    }

}
