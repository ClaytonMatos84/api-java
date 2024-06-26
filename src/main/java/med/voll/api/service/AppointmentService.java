package med.voll.api.service;

import lombok.AllArgsConstructor;
import med.voll.api.domain.appointment.entity.Appointment;
import med.voll.api.domain.appointment.entity.CancelReason;
import med.voll.api.domain.appointment.repository.AppointmentRepository;
import med.voll.api.domain.appointment.validate.AppointmentCancelValidator;
import med.voll.api.domain.appointment.validate.AppointmentValidator;
import med.voll.api.domain.doctor.entity.Doctor;
import med.voll.api.domain.doctor.entity.Specialty;
import med.voll.api.domain.doctor.repository.DoctorRepository;
import med.voll.api.domain.patient.entity.Patient;
import med.voll.api.domain.patient.repository.PatientRepository;
import med.voll.api.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentValidator appointmentValidator;
    private final AppointmentCancelValidator appointmentCancelValidator;

    @Transactional
    public Appointment scheduler(Long idDoctor, Long idPatient, LocalDateTime date, Specialty specialty) {
        if (idDoctor != null && !doctorRepository.existsByIdAndActiveTrue(idDoctor)) {
            throw new ApiException("Doctor not exists.");
        }

        if (!patientRepository.existsById(idPatient)) {
            throw new ApiException("Patient not exists.");
        }

        Doctor doctor = choiceDoctor(idDoctor, specialty, date);
        if (doctor == null) {
            throw new ApiException("Doctor not available on this date.");
        }
        Patient patient = patientRepository.getReferenceById(idPatient);

        Appointment appointment = new Appointment(null, doctor, patient, date, null);
        appointmentValidator.validator(appointment);

        return appointmentRepository.save(appointment);
    }

    private Doctor choiceDoctor(Long idDoctor, Specialty specialty, LocalDateTime date) {
        if (idDoctor != null) {
            return doctorRepository.getReferenceById(idDoctor);
        }

        if (specialty == null) {
            throw new ApiException("Specialty required if idDoctor not exists.");
        }

        return doctorRepository.findBySpecialtyAndDate(specialty, date);
    }

    @Transactional
    public void cancel(Long idAppointment, CancelReason cancelReason) {
        if (!appointmentRepository.existsById(idAppointment)) {
            throw new ApiException("Appointment not exists.");
        }

        Appointment appointment = appointmentRepository.getReferenceById(idAppointment);
        appointmentCancelValidator.validator(appointment);

        appointment.cancel(cancelReason);
    }

}
