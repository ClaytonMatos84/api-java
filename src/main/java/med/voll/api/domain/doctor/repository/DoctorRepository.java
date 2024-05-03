package med.voll.api.domain.doctor.repository;

import med.voll.api.domain.doctor.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable page);

    Optional<Doctor> findByIdAndActiveTrue(Long id);
}