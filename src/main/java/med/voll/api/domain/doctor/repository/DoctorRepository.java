package med.voll.api.domain.doctor.repository;

import med.voll.api.domain.doctor.entity.Doctor;
import med.voll.api.domain.doctor.entity.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("""
            select d from Doctor d
            where d.active = true
            and d.specialty = :specialty
            and d.id not in(
                select a.doctor.id from Appointment a
                where a.date = :date
                and a.cancelReason is null
            )
            order by rand()
            limit 1
            """)
    Doctor findBySpecialtyAndDate(Specialty specialty, LocalDateTime date);

    Page<Doctor> findAllByActiveTrue(Pageable page);

    Optional<Doctor> findByIdAndActiveTrue(Long id);

    boolean existsByIdAndActiveTrue(Long idDoctor);
}
