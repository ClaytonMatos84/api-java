package med.voll.api.domain.appointment.repository;

import med.voll.api.domain.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
