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
    Page<Doctor> findAllByActiveTrue(Pageable page);

    Optional<Doctor> findByIdAndActiveTrue(Long id);

    @Query("""
            select m from Doctor m
            where m.active = true
            and m.specialty = :specialty
            and m.id not in(
                select a.doctor.id from Appointment a
                where a.date = :date
            )
            order by rand()
            limit 1
            """)
    Doctor findBySpecialtyAndDate(Specialty specialty, LocalDateTime date);

    boolean existsByIdAndActiveTrue(Long idDoctor);
}
